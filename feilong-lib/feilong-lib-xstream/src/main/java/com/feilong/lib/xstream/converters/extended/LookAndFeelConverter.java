/*
 * Copyright (C) 2007, 2008, 2013, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 08.12.2007 by Joerg Schaible
 */
package com.feilong.lib.xstream.converters.extended;

import java.io.NotSerializableException;

import javax.swing.LookAndFeel;

import com.feilong.lib.xstream.converters.reflection.ReflectionConverter;
import com.feilong.lib.xstream.converters.reflection.ReflectionProvider;
import com.feilong.lib.xstream.mapper.Mapper;

/**
 * A converter for Swing LookAndFeel implementations. The JDK's implementations are serializable
 * for historical reasons but will throw a {@link NotSerializableException} in their writeObject
 * method. Therefore XStream will use an implementation based on the ReflectionConverter.
 * 
 * @author J&ouml;rg Schaible
 * @since 1.3
 */
public class LookAndFeelConverter extends ReflectionConverter{

    /**
     * Constructs a LookAndFeelConverter.
     * 
     * @param mapper
     *            the mapper
     * @param reflectionProvider
     *            the reflection provider
     * @since 1.3
     */
    public LookAndFeelConverter(Mapper mapper, ReflectionProvider reflectionProvider){
        super(mapper, reflectionProvider);
    }

    @Override
    public boolean canConvert(Class type){
        return type != null && LookAndFeel.class.isAssignableFrom(type) && canAccess(type);
    }
}
