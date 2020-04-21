package ua.mai.fam.restclient.client;

import org.springframework.http.ResponseEntity;
import ua.mai.fam.model.Person;
import ua.mai.fam.restclient.FamClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonClient extends EntityClient {

    public static PersonClient create(FamClient appClient, String entityPath) {
        if (appClient == null) {
            throw new IllegalArgumentException("appClient can't be null!");
        }
        PersonClient client = new PersonClient(appClient, entityPath);
        return client;
    }

    private PersonClient(FamClient appClient, String entityPath) {
        super(appClient, entityPath);
    }


    public Person findPerson(Long id) {
        Person person = (Person)getRestTemplate().getForObject(getEntityUri() + "/" + id, Person.class);
        return person;
    }

    public List<Person> findAllPersons() {
        //https://www.baeldung.com/spring-rest-template-list#1-using-arrays
        ResponseEntity<Person[]> response = getRestTemplate().getForEntity(getEntityUri(), Person[].class);
        Person[] personArr = response.getBody();
        //https://stackoverflow.com/questions/53940628/convert-an-array-to-list-with-specific-range-in-java-8#answer-53940664
        List<Person> personList = Arrays.stream(personArr, 1, personArr.length).collect(Collectors.toList());
        return personList;
    }

}
