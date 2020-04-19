package ua.mai.fam.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ua.mai.fam.config.JdbcConfig;
import ua.mai.fam.model.PersonTestData;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.PersonRepository;
//import ua.mai.fam.repository.datajdbc.ProfileResolver4RepositoryDataJdbc;
//import ua.mai.fam.repository.jdbc.PersonRepository4Jdbc;

//@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4Rest4Jdbc.class)
@AutoConfigureMockMvc
//@ContextConfiguration(classes = {JdbcConfig.class, PersonRestController.class, PersonRepository4Jdbc.class})
//@EnableJdbcRepositories(basePackages="ua.mai.fam.repository.datajdbc")
@WebMvcTest
class PersonRestController4JdbcTest extends PersonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    private PersonRestController personRestController;
//    @Autowired
//    private PersonRepository personRepository;

//    @Autowired
//    PersonRestController4JdbcTest(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//        personRestController = new PersonRestController(personRepository);
//    }

    @Override
    protected PersonRestController getPersonRestController() {
        return personRestController;
    }
/*
    @Override
    protected PersonRepository getPersonRepository() {
        return personRestController.getDtoRepository();
    }
*/

    @Test
    @Override
    void find() throws Exception {
        Person isertedPesron = null;
//            getPersonRepository().insert(PersonTestData.getNewPerson01());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/persons/" + isertedPesron.getId())
//            .with(user(TEST_USER_ID))
//            .with(csrf())
//            .content(birthday)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
            .andReturn();

        String resultDOW = result.getResponse().getContentAsString();
//        assertNotNull(resultDOW);
//        assertEquals(dow, resultDOW);
    }

}