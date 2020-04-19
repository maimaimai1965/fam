package ua.mai.fam.restclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.restclient.client.PersonClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FamClient {

    private RestTemplate restTemplate;
    private String baseUri;

    public static final String PERSON_PATH = "/persons";


    public FamClient(String baseUri) {
        if ((baseUri == null) || (baseUri.isEmpty())) {
            throw new IllegalArgumentException("baseUri not defined!");
        }
        setBaseUri(baseUri);
        Log log = LogFactory.getLog(getClass());
        ClientHttpRequestInterceptor interceptor =
            (HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
                log.info(String.format("Request to URI %s with HTTP verb '%s'",
                    request.getURI(), request.getMethod().toString()));
                return execution.execute(request, body);
            };
        restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
    }

    public FamClient(String baseUri, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        setBaseUri(baseUri);
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
//        personUri = this.baseUri + "/persons";
    }
    public String getBaseUri() {
        return baseUri;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

//    public Person findPerson(Long id) {
//        Person person = (Person)restTemplate.getForObject(personUri + "/" + id, Person.class);
//        return person;
//    }
//
//    public List<Person> findAllPersons() {
//        //https://www.baeldung.com/spring-rest-template-list#1-using-arrays
//        ResponseEntity<Person[]> response = restTemplate.getForEntity(personUri, Person[].class);
//        Person[] personArr = response.getBody();
//        //https://stackoverflow.com/questions/53940628/convert-an-array-to-list-with-specific-range-in-java-8#answer-53940664
//        List<Person> personList = Arrays.stream(personArr, 1, personArr.length).collect(Collectors.toList());
//        return personList;
//    }

    public PersonClient createPersonClient() {
        return PersonClient.create(this, PERSON_PATH);
    }

}
