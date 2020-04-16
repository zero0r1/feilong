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
package com.feilong.formatter;

import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.formatter.FormatterUtil.formatToSimpleTable;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.json.jsonlib.JsonUtil;
import com.feilong.store.member.Address;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.8.2
 */
public class FormatterUtilTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatterUtilTest.class);

    @Test
    @SuppressWarnings("static-method")
    public final void testFormatToSimpleTable2(){
        List<Address> list = toList(
                        new Address("china", "shanghai", "216000", "wenshui wanrong.lu 888"),
                        new Address("china", "beijing", "216001", "wenshui wanrong.lu 666"),
                        new Address("china", "nantong", "216002", "wenshui wanrong.lu 222"),
                        new Address("china", "tianjing", "216600", "wenshui wanrong.lu 999"));

        //---------------------------------------------------------------

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug(JsonUtil.format(list));
        }

        //---------------------------------------------------------------

        LOGGER.debug(formatToSimpleTable(list));
    }
}