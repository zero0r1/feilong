/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.feilong.lib.compress.archivers.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.feilong.lib.compress.utils.FileNameUtils;

/**
 * Used internally by {@link ZipArchiveOutputStream} when creating a split archive.
 *
 * @since 1.20
 */
class ZipSplitOutputStream extends OutputStream{

    private OutputStream      outputStream;

    private File              zipFile;

    private final long        splitSize;

    private int               currentSplitSegmentIndex        = 0;

    private long              currentSplitSegmentBytesWritten = 0;

    private boolean           finished                        = false;

    private final byte[]      singleByte                      = new byte[1];

    /**
     * 8.5.1 Capacities for split archives are as follows:
     * <p>
     * Maximum number of segments = 4,294,967,295 - 1
     * Maximum .ZIP segment size = 4,294,967,295 bytes (refer to section 8.5.6)
     * Minimum segment size = 64K
     * Maximum PKSFX segment size = 2,147,483,647 bytes
     */
    private final static long ZIP_SEGMENT_MIN_SIZE            = 64 * 1024L;

    private final static long ZIP_SEGMENT_MAX_SIZE            = 4294967295L;

    /**
     * Create a split zip. If the zip file is smaller than the split size,
     * then there will only be one split zip, and its suffix is .zip,
     * otherwise the split segments should be like .z01, .z02, ... .z(N-1), .zip
     *
     * @param zipFile
     *            the zip file to write to
     * @param splitSize
     *            the split size
     */
    public ZipSplitOutputStream(final File zipFile, final long splitSize) throws IllegalArgumentException,IOException{
        if (splitSize < ZIP_SEGMENT_MIN_SIZE || splitSize > ZIP_SEGMENT_MAX_SIZE){
            throw new IllegalArgumentException("zip split segment size should between 64K and 4,294,967,295");
        }

        this.zipFile = zipFile;
        this.splitSize = splitSize;

        this.outputStream = new FileOutputStream(zipFile);
        // write the zip split signature 0x08074B50 to the zip file
        writeZipSplitSignature();
    }

    /**
     * Some data can not be written to different split segments, for example:
     * <p>
     * 4.4.1.5 The end of central directory record and the Zip64 end
     * of central directory locator record MUST reside on the same
     * disk when splitting or spanning an archive.
     *
     * @param unsplittableContentSize
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public void prepareToWriteUnsplittableContent(long unsplittableContentSize) throws IllegalArgumentException,IOException{
        if (unsplittableContentSize > this.splitSize){
            throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
        }

        long bytesRemainingInThisSegment = this.splitSize - this.currentSplitSegmentBytesWritten;
        if (bytesRemainingInThisSegment < unsplittableContentSize){
            openNewSplitSegment();
        }
    }

    @Override
    public void write(int i) throws IOException{
        singleByte[0] = (byte) (i & 0xff);
        write(singleByte);
    }

    @Override
    public void write(byte[] b) throws IOException{
        write(b, 0, b.length);
    }

    /**
     * Write the data to zip split segments, if the remaining space of current split segment
     * is not enough, then a new split segment should be created
     *
     * @param b
     *            data to write
     * @param off
     *            offset of the start of data in param b
     * @param len
     *            the length of data to write
     * @throws IOException
     */
    @Override
    public void write(byte[] b,int off,int len) throws IOException{
        if (len <= 0){
            return;
        }

        if (currentSplitSegmentBytesWritten >= splitSize){
            openNewSplitSegment();
            write(b, off, len);
        }else if (currentSplitSegmentBytesWritten + len > splitSize){
            int bytesToWriteForThisSegment = (int) splitSize - (int) currentSplitSegmentBytesWritten;
            write(b, off, bytesToWriteForThisSegment);
            openNewSplitSegment();
            write(b, off + bytesToWriteForThisSegment, len - bytesToWriteForThisSegment);
        }else{
            outputStream.write(b, off, len);
            currentSplitSegmentBytesWritten += len;
        }
    }

    @Override
    public void close() throws IOException{
        if (!finished){
            finish();
        }
    }

    /**
     * The last zip split segment's suffix should be .zip
     *
     * @throws IOException
     */
    private void finish() throws IOException{
        if (finished){
            throw new IOException("This archive has already been finished");
        }

        String zipFileBaseName = FileNameUtils.getBaseName(zipFile.getName());
        File lastZipSplitSegmentFile = new File(zipFile.getParentFile(), zipFileBaseName + ".zip");
        outputStream.close();
        if (!zipFile.renameTo(lastZipSplitSegmentFile)){
            throw new IOException("Failed to rename " + zipFile + " to " + lastZipSplitSegmentFile);
        }
        finished = true;
    }

    /**
     * Create a new zip split segment and prepare to write to the new segment
     *
     * @return
     * @throws IOException
     */
    private OutputStream openNewSplitSegment() throws IOException{
        File newFile;
        if (currentSplitSegmentIndex == 0){
            outputStream.close();
            newFile = createNewSplitSegmentFile(1);
            if (!zipFile.renameTo(newFile)){
                throw new IOException("Failed to rename " + zipFile + " to " + newFile);
            }
        }

        newFile = createNewSplitSegmentFile(null);

        outputStream.close();
        outputStream = new FileOutputStream(newFile);
        currentSplitSegmentBytesWritten = 0;
        zipFile = newFile;
        currentSplitSegmentIndex++;

        return outputStream;
    }

    /**
     * Write the zip split signature (0x08074B50) to the head of the first zip split segment
     *
     * @throws IOException
     */
    private void writeZipSplitSignature() throws IOException{
        outputStream.write(ZipArchiveOutputStream.DD_SIG);
        currentSplitSegmentBytesWritten += ZipArchiveOutputStream.DD_SIG.length;
    }

    /**
     * Create the new zip split segment, the last zip segment should be .zip, and the zip split segments' suffix should be
     * like .z01, .z02, .z03, ... .z99, .z100, ..., .z(N-1), .zip
     * <p>
     * 8.3.3 Split ZIP files are typically written to the same location
     * and are subject to name collisions if the spanned name
     * format is used since each segment will reside on the same
     * drive. To avoid name collisions, split archives are named
     * as follows.
     * <p>
     * Segment 1 = filename.z01
     * Segment n-1 = filename.z(n-1)
     * Segment n = filename.zip
     * <p>
     * NOTE:
     * The zip split segment begin from 1,2,3,... , and we're creating a new segment,
     * so the new segment suffix should be (currentSplitSegmentIndex + 2)
     *
     * @param zipSplitSegmentSuffixIndex
     * @return
     * @throws IOException
     */
    private File createNewSplitSegmentFile(Integer zipSplitSegmentSuffixIndex) throws IOException{
        int newZipSplitSegmentSuffixIndex = zipSplitSegmentSuffixIndex == null ? (currentSplitSegmentIndex + 2)
                        : zipSplitSegmentSuffixIndex;
        String baseName = FileNameUtils.getBaseName(zipFile.getName());
        String extension = ".z";
        if (newZipSplitSegmentSuffixIndex <= 9){
            extension += "0" + newZipSplitSegmentSuffixIndex;
        }else{
            extension += newZipSplitSegmentSuffixIndex;
        }

        File newFile = new File(zipFile.getParent(), baseName + extension);

        if (newFile.exists()){
            throw new IOException("split zip segment " + baseName + extension + " already exists");
        }
        return newFile;
    }

    public int getCurrentSplitSegmentIndex(){
        return currentSplitSegmentIndex;
    }

    public long getCurrentSplitSegmentBytesWritten(){
        return currentSplitSegmentBytesWritten;
    }
}
