/*
 * Copyright (C) 2017 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 13. February 2017 by Joerg Schaible
 */
package com.feilong.lib.xstream.converters.time;

import java.time.Period;
import java.time.format.DateTimeParseException;

import com.feilong.lib.xstream.converters.ConversionException;
import com.feilong.lib.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Converts a {@link Period} instance to string.
 *
 * @author J&ouml;rg Schaible
 * @since 1.4.10
 */
public class PeriodConverter extends AbstractSingleValueConverter{

    @Override
    public boolean canConvert(@SuppressWarnings("rawtypes") final Class type){
        return Period.class == type;
    }

    @Override
    public Period fromString(final String str){
        try{
            return Period.parse(str);
        }catch (final DateTimeParseException ex){
            final ConversionException exception = new ConversionException("Cannot parse period value", ex);
            exception.add("period", str);
            throw exception;
        }
    }
}
