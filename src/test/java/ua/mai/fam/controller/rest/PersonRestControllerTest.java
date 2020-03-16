package ua.mai.fam.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.mai.fam.AbstractTimingTest;
import ua.mai.fam.controller.rest.PersonRestController;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.repository.datajdbc.PersonRepository4DataJdbc;

abstract class PersonRestControllerTest extends AbstractTimingTest {

    abstract protected PersonRestController getPersonRestController();
    abstract protected PersonRepository getPersonRepository();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void add() {

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void find() throws Exception {
    }

}