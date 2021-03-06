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
package com.feilong.json.format;

import static com.feilong.core.bean.ConvertUtil.toList;

import org.junit.Test;

import com.feilong.json.AbstractJsonTest;
import com.feilong.json.JsonUtil;
import com.feilong.store.system.Menu;

public class FormatTest extends AbstractJsonTest{

    @Test
    public void testJsonMenu(){
        Menu menu = new Menu(4L);
        menu.setChildren(toList(new Menu(5L)));

        LOGGER.debug(JsonUtil.format(menu));
    }

    @Test
    public void testJsonString(){
        LOGGER.debug("{}--->{}", USER_JSON_STRING, JsonUtil.format(USER_JSON_STRING));
    }
}
