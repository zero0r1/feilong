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
package com.feilong.excel.销售数据;

import static com.feilong.core.bean.ConvertUtil.toBigDecimal;
import static com.feilong.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import com.feilong.excel.AbstractLoxiaWriteTest;

abstract class AbstractWriteTest extends AbstractLoxiaWriteTest{

    protected List<SalesData> buildList(){
        List<SalesData> list = newArrayList();

        for (int i = 1; i <= buildMonth(); ++i){
            AuditMember auditMember = new AuditMember(i % 2 == 0 ? "张飞" : "关羽");
            list.add(new SalesData(2020, i, toBigDecimal(i * 533033.88), auditMember));
        }
        return list;
    }

    protected int buildMonth(){
        return 12;
    }
}
