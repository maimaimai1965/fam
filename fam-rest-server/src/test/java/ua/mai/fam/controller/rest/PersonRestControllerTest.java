package ua.mai.fam.controller.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.mai.fam.repository.PersonRepository;

abstract class PersonRestControllerTest {

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