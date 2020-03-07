/*
 * Copyright 2017-2018 the original author or authors.
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

import mai.ua.fam.model.datajdbc.Person4DataJdbc;
import mai.ua.fam.model.person.PersonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

/**
 *
 */
@SpringBootTest
@AutoConfigureMockMvc

//@SpringBootTest(classes = {DataJdbcConfig.class, JdbcConfig.class})
//@AutoConfigureJdbc
@ActiveProfiles({"db-h2", "da-jdbc"})
public class PersonRepository4DataJdbcTest {

    @Autowired
    PersonRepository4DataJdbc repository;

//    @Test
//    public void exerciseRepositoryForSimpleEntity() {
//
//        // create some categories
//        Person4DataJdbc cars = repository.save(new Person4DataJdbc("Cars", "Anything that has approximately 4 wheels.", AgeGroup._3to8));
//
//        Category buildings = repository.save(new Category("Buildings", null, AgeGroup._12andOlder));
//
//        // save categories
//        Output.list(repository.findAll(), "`Cars` and `Buildings` got saved");
//
//        assertThat(cars.getId()).isNotNull();
//        assertThat(buildings.getId()).isNotNull();
//
//        // update one
//        buildings.setDescription("Famous and impressive buildings incl. the 'bike shed'.");
//        repository.save(buildings);
//        Output.list(repository.findAll(), "`Buildings` has a description");
//
//        // delete stuff again
//        repository.delete(cars);
//        Output.list(repository.findAll(), "`Cars` is gone.");
//    }

    @Test
    public void saveAndFind(){
        Long id = 1L;
        String surname = "Иванов";
        String firstName = "Сергей";
        String middleName = "Игнатьевич";

        Person4DataJdbc person = new PersonBuilder().setId(id).setSurname(surname).setFirstName(firstName)
            .setMiddleName(middleName).createPerson4DataJdbc();
        repository.save(person);

        Optional<Person4DataJdbc> findedPerson = repository.findById(id);

        id = 0L;
    }
}
