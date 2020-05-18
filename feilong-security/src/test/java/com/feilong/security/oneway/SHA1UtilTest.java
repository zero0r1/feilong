/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.security.oneway;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.feilong.lib.lang3.SystemUtils;
import com.feilong.security.AbstractSecurityTest;

/**
 * The Class SHA1UtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @version 1.0 2011-2-7 上午01:12:36
 */
public class SHA1UtilTest extends AbstractSecurityTest{

    /**
     * Encode file.
     * 
     * @throws IOException
     */
    @Test
    public void encodeFile() throws IOException{
        String filepath = SystemUtils.USER_HOME + "/.gitconfig";
        String encodeFile = SHA1Util.encodeFile(filepath);

        LOGGER.debug(debugSecurityValue(encodeFile));//9163e830c3a3e74bc70d9744f33642cb46f951ba

        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);

        assertEquals(encodeFile, DigestUtils.sha1Hex(fileInputStream));
    }

    @Test
    public void encode121(){
        assertEquals(DigestUtils.sha1Hex("2284208963"), SHA1Util.encode("2284208963"));
    }

    @Test
    public void encode12(){
        LOGGER.debug(debugSecurityValue(SHA1Util.encode("2284208963")));
    }

    /**
     * Encode1.
     */
    @Test
    public void encode1(){
        LOGGER.debug(debugSecurityValue(SHA1Util.encode("521000")));
        String origin = "sdadadadadaadasasdasdadas" + "dasdadasdadadasdasdasdadasdasdadadadadasdadadadad"
                        + "aaadasdasdasdasdadadadadaadasasdasdadasdasda" + "dasdadadasdasdasdadas"
                        + "dasdadadadadasdadadadadaaadasdasdasdasasda" + "sdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaa"
                        + "dasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdada"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "dadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdasdasdasdadasaddssadsaadsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadasaddssadsaadasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasasdasdasdasdasdasdasdasdadasaddssadsaadsasdsdadadadadaadasasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasdadadadadaad"
                        + "asasdasdadasdasdadasdadadasdasdasdadasdasdadadadadasdadadadadaaadasdasdasdasa"
                        + "sdasdasdasdasdasdasdasdadasaddssadsaadsasdasdasdasdasdasdasdasdadas"
                        + "addssadsaadasdasdasdasaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdaaddssadsaadasdasdasdasdasdasdasdadasaddssadsaadasdasdasdadasdasdasdadasaddssadsaadasdasdasda"
                        + "sdasdasdadasaddssadsaad";
        LOGGER.debug(debugSecurityValue(SHA1Util.encode(origin)));
    }

}
