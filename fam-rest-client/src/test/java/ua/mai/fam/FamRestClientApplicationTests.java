package ua.mai.fam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.restclient.FamClient;

import java.util.List;

class FamRestClientApplicationTests {

    private static String baseUri = "http://localhost:8555/fam/api/v1";

    private static FamClient famClient;

    @BeforeAll
    static void beforeAll() {
        famClient = new FamClient(baseUri);
    }

    @Test
    void contextLoads() {
        Person person = famClient.findPerson(50_000L);
        System.out.println("findPerson(): " + person);

        List<Person> persons = famClient.findAllPersons();
        System.out.println("findAllPersons(): " + persons);

    }

}
