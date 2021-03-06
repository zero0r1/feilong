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
package com.feilong.excel;

import static com.feilong.core.bean.ConvertUtil.toArray;

import java.util.Map;

import com.feilong.coreextension.awt.DesktopUtil;
import com.feilong.test.AbstractTest;

public abstract class AbstractWriteTest extends AbstractTest{

    protected static void handle(String templateLocation,String sheetDefinitionLocation,Map<String, Object> data){
        handle(templateLocation, sheetDefinitionLocation, null, data);
    }

    protected static void handlePerSheet(String templateLocation,String sheetDefinitionLocation,String sheetName,Map<String, Object> data){
        handle(templateLocation, sheetDefinitionLocation, toArray(sheetName), data);
    }

    protected static void handle(String templateLocation,String sheetDefinitionLocation,String[] sheetNames,Map<String, Object> beans){
        String outputFileName = ExcelWriteUtil.write(templateLocation, sheetDefinitionLocation, sheetNames, beans, null);
        DesktopUtil.open(outputFileName);
    }

}
