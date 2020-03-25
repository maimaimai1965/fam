/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.mai.fam.repository.datajdbc;

import ua.mai.fam.util.HasId;
import ua.mai.fam.util.exception.FoundException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

/**
 * Реализация интерфейса с дополнительными методами, который можно добавлять к Data JDBC репозиторию.
 */
public class WithOperationsImpl<T> implements WithOperations<T> {

    private final JdbcAggregateTemplate template;

    public WithOperationsImpl(JdbcAggregateTemplate template) {
        this.template = template;
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public T insert(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Entity can't be null in insert.");
        }
        Object id;
        try {
            id = ((HasId) t).getId();
        } catch (ClassCastException e) {
            throw new TypeNotPresentException("Entity must implements interface HasId.", e);
        }
        if (id != null) {
            throw new FoundException("Exists id=" + id + " in new inserted object.");
        }
        return template.insert(t);
    }

//    @Override
//    public T updateT(T t) {
//        return template.update(t);
//    }
}
