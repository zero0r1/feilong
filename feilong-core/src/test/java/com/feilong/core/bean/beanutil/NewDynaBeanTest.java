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
package com.feilong.core.bean.beanutil;

import static com.feilong.core.bean.ConvertUtil.toMap;
import static com.feilong.core.bean.ConvertUtil.toMapUseEntrys;
import static com.feilong.core.util.MapUtil.newHashMap;
import static com.feilong.lib.lang3.tuple.Pair.of;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.junit.Test;

import com.feilong.core.bean.BeanUtil;

/**
 * The Class BeanUtilNewDynaBeanTest.
 *
 * @author <a href="https://github.com/ifeilong/feilong">feilong</a>
 */
public class NewDynaBeanTest{

    @Test
    public void testNewDynaBean(){
        Map<String, Object> map = newHashMap();

        DynaBean newDynaBean = BeanUtil.newDynaBean(
                        toMapUseEntrys(//
                                        of("address", (Object) map),
                                        of("firstName", (Object) "Fred"),
                                        of("lastName", (Object) "Flintstone")));

        assertEquals(map, newDynaBean.get("address"));
        assertEquals("Fred", newDynaBean.get("firstName"));
        assertEquals("Flintstone", newDynaBean.get("lastName"));
    }

    @Test
    public void testNewDynaBean1(){
        Map<Integer, Object> map = newHashMap();

        DynaBean newDynaBean = BeanUtil.newDynaBean(
                        toMapUseEntrys(//
                                        of(1, (Object) map),
                                        of(2, (Object) "Fred"),
                                        of(3, (Object) "Flintstone")));

        assertEquals(map, newDynaBean.get("1"));

        assertEquals("Fred", newDynaBean.get("2"));
        assertEquals("Flintstone", newDynaBean.get("3"));
    }

    //---------------------------------------------------------------

    /**
     * Test new dyna bean null map.
     */
    @Test(expected = NullPointerException.class)
    
    public void testNewDynaBeanNullMap(){
        BeanUtil.newDynaBean(null);
    }

    /**
     * Test new dyna bean map with null key.
     */
    @Test(expected = NullPointerException.class)
    
    public void testNewDynaBeanMapWithNullKey(){
        BeanUtil.newDynaBean(toMap((String) null, "feilong"));
    }

    /**
     * Test new dyna bean map with empty key.
     */
    @Test(expected = IllegalArgumentException.class)
    
    public void testNewDynaBeanMapWithEmptyKey(){
        BeanUtil.newDynaBean(toMap("", "feilong"));
    }

    /**
     * Test new dyna bean map with blank key.
     */
    @Test(expected = IllegalArgumentException.class)
    
    public void testNewDynaBeanMapWithBlankKey(){
        BeanUtil.newDynaBean(toMap("", "feilong"));
    }
}
