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
package com.feilong.component.upload;

import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.core.util.CollectionsUtil.size;

import java.util.List;
import java.util.Map;

import com.feilong.context.DataHandler;

/**
 * The Class AbstractSimpleListDataHandler.
 *
 * @author <a href="https://github.com/ifeilong/feilong">feilong</a>
 * @param <T>
 *            the generic type
 * @since 3.0.0
 */
public abstract class AbstractSimpleListDataHandler<T> implements DataHandler{

    @Override
    public void handle(Map<String, Object> data){
        int size = size(data);
        if (size != 1){
            throw new IllegalArgumentException("data size:[" + size + "] != 1");
        }

        //---------------------------------------------------------------

        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) (toList(data.values()).get(0));
        handle(list);

    }

    /**
     * Handle.
     *
     * @param list
     *            the list
     */
    protected abstract void handle(List<T> list);
}
