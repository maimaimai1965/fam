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
package mai.ua.fam.repository.datajdbc;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

/**
 * Интерфейс с дополнительными методами, который можно добавлять к Data JDBC репозиторию.
 */
public interface WithOperations<T> {

    /**
     * Custom insert method.
     *
     * @param t
     * @return
     */
    T insert(T t);

//    @Modifying
//    @Query("UPDATE model set name = lower(name) WHERE name <> lower(name)")
//    T updateT(T t);

}
