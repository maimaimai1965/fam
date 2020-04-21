package ua.mai.fam.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.mai.fam.model.Person;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.util.CollectionUtil;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.ResponceStatusExceptionWithCode;
import ua.mai.fam.util.exception.RestErrorCodes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ua.mai.fam.FamApplication.APPLICATION_REST_PATH;

@RestController
@RequestMapping(PersonRestController.REST_PERSON_PATH)
@Profile("ac-rest")
public class PersonRestController {

    public static final String REST_PERSON_PATH = APPLICATION_REST_PATH  + "/persons";

    private PersonRepository personRepository;

    @Autowired
    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @PostMapping()
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Не используется!", response = String.class),
//        @ApiResponse(code = 201, response = Payment.class),
//        @ApiResponse(code = 400, message = "Ошибка.", response = ErrorDetails.class)})
    public ResponseEntity<Person> add(@RequestBody Person person) {
        try {
            person = personRepository.insert(person);
            URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();
            return ResponseEntity.created(uri).body(person); //, HttpStatus.CREATED/*201*/);
        }
        catch (FoundException e) {
            throw new ResponceStatusExceptionWithCode(HttpStatus.CONFLICT, e, RestErrorCodes.EXISTS_ID_CODE);
        }
        catch (Exception e) {
            throw new ResponceStatusExceptionWithCode(HttpStatus.BAD_REQUEST, e, RestErrorCodes.BAD_REQUEST_CODE);
        }
//        return new ResponseEntity<Person>(person, HttpStatus.CREATED/*201*/);
//        //https://stackoverflow.com/questions/42546141/add-location-header-to-spring-mvcs-post-response#52024684
//        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Person> save(@RequestBody(required=true) Person person,
                                       @PathVariable(required=true) Long id) {
        if (!id.equals(person.getId())) {
            throw new ResponceStatusExceptionWithCode(HttpStatus.BAD_REQUEST,
                "id in path (" + id + ") is not equal in Entity (" + person.getId() + ")",
                RestErrorCodes.NOT_EQUAL_IDS_WHEN_UPDATE_CODE);
        }
        person = personRepository.save(person);
        return new ResponseEntity<Person>(person, HttpStatus.NO_CONTENT/*204*/);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable(required=true) Long id) {
        personRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT/*204*/);
    }

    @GetMapping()
    public @ResponseBody List<Person> findAll(/*HttpServletResponse response*/) {
        return CollectionUtil.makeList(personRepository.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable(required=true) Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND/*404*/);
        }
        return new ResponseEntity<Person>(person.get(), HttpStatus.OK/*200*/);
    }

}
