/*
 * Copyright (C) 2006, 2007, 2016, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 07. July 2006 by Mauro Talevi
 */
package com.feilong.lib.xstream.converters.extended;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.feilong.lib.xstream.converters.ConversionException;
import com.feilong.lib.xstream.converters.basic.AbstractSingleValueConverter;
import com.feilong.lib.xstream.converters.reflection.ObjectAccessException;

/**
 * Convenient converter for classes with natural string representation.
 * 
 * Converter for classes that adopt the following convention:
 * - a constructor that takes a single string parameter
 * - a toString() that is overloaded to issue a string that is meaningful
 *
 * @author Paul Hammant
 */
public class ToStringConverter extends AbstractSingleValueConverter{

    private static final Class[] STRING_PARAMETER = { String.class };

    private final Class          clazz;

    private final Constructor    ctor;

    public ToStringConverter(Class clazz) throws NoSuchMethodException{
        this.clazz = clazz;
        ctor = clazz.getConstructor(STRING_PARAMETER);
    }

    @Override
    public boolean canConvert(Class type){
        return type == clazz;
    }

    @Override
    public String toString(Object obj){
        return obj == null ? null : obj.toString();
    }

    @Override
    public Object fromString(String str){
        try{
            return ctor.newInstance(new Object[] { str });
        }catch (InstantiationException e){
            throw new ConversionException("Unable to instantiate single String param constructor", e);
        }catch (IllegalAccessException e){
            throw new ObjectAccessException("Unable to access single String param constructor", e);
        }catch (InvocationTargetException e){
            throw new ConversionException("Unable to target single String param constructor", e.getTargetException());
        }
    }
}
