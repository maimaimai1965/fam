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

public class FamClient {

    String baseUri;

    private RestTemplate restTemplate;

    public FamClient(String baseUri) {
        if ((baseUri == null) || (baseUri.isEmpty())) {
            throw new IllegalArgumentException("baseUri not defined!");
        }
        this.baseUri = baseUri;
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
        this.restTemplate = restTemplate;
    }

    public Person find(Long id) {
        Person person = (Person)restTemplate.getForObject(baseUri + "/" + id, Person.class);
        return person;
    }

}
