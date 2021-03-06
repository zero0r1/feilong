/*
 * Copyright (C) 2009, 2013, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 17. April 2009 by Joerg Schaible
 */
package com.feilong.lib.xstream.converters.extended;

import java.lang.reflect.Field;

import com.feilong.lib.xstream.converters.ConversionException;
import com.feilong.lib.xstream.converters.Converter;
import com.feilong.lib.xstream.converters.MarshallingContext;
import com.feilong.lib.xstream.converters.SingleValueConverter;
import com.feilong.lib.xstream.converters.UnmarshallingContext;
import com.feilong.lib.xstream.core.ClassLoaderReference;
import com.feilong.lib.xstream.io.HierarchicalStreamReader;
import com.feilong.lib.xstream.io.HierarchicalStreamWriter;
import com.feilong.lib.xstream.mapper.DefaultMapper;
import com.feilong.lib.xstream.mapper.Mapper;

/**
 * Converts a java.lang.reflect.Field to XML.
 * 
 * @author J&ouml;rg Schaible
 */
public class JavaFieldConverter implements Converter{

    private final SingleValueConverter javaClassConverter;

    private final Mapper               mapper;

    /**
     * Construct a JavaFieldConverter.
     * 
     * @param classLoaderReference
     *            the reference to the {@link ClassLoader} of the XStream instance
     * @since 1.4.5
     */
    public JavaFieldConverter(ClassLoaderReference classLoaderReference){
        this(new JavaClassConverter(classLoaderReference), new DefaultMapper(classLoaderReference));
    }

    /**
     * @deprecated As of 1.4.5 use {@link #JavaFieldConverter(ClassLoaderReference)}
     */
    @Deprecated
    public JavaFieldConverter(ClassLoader classLoader){
        this(new ClassLoaderReference(classLoader));
    }

    /**
     * Construct a JavaFieldConverter. Depending on the mapper chain the converter will also respect aliases.
     * 
     * @param javaClassConverter
     *            the converter to use
     * @param mapper
     *            to use
     * @since 1.4.5
     */
    protected JavaFieldConverter(SingleValueConverter javaClassConverter, Mapper mapper){
        this.javaClassConverter = javaClassConverter;
        this.mapper = mapper;
    }

    @Override
    public boolean canConvert(Class type){
        return type == Field.class;
    }

    @Override
    public void marshal(Object source,HierarchicalStreamWriter writer,MarshallingContext context){
        Field field = (Field) source;
        Class type = field.getDeclaringClass();

        writer.startNode("name");
        writer.setValue(mapper.serializedMember(type, field.getName()));
        writer.endNode();

        writer.startNode("clazz");
        writer.setValue(javaClassConverter.toString(type));
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context){
        String methodName = null;
        String declaringClassName = null;

        while ((methodName == null || declaringClassName == null) && reader.hasMoreChildren()){
            reader.moveDown();

            if (reader.getNodeName().equals("name")){
                methodName = reader.getValue();
            }else if (reader.getNodeName().equals("clazz")){
                declaringClassName = reader.getValue();
            }
            reader.moveUp();
        }

        Class declaringClass = (Class) javaClassConverter.fromString(declaringClassName);
        try{
            return declaringClass.getDeclaredField(mapper.realMember(declaringClass, methodName));
        }catch (NoSuchFieldException e){
            throw new ConversionException(e);
        }
    }
}
