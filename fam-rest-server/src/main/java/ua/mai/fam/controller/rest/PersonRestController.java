package ua.mai.fam.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.mai.fam.controller.BaseController;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.model.Person;
import ua.mai.fam.repository.PersonDtoRepository;
import ua.mai.fam.repository.PersonRepositoryMarker;
import ua.mai.fam.service.PersonDtoService;
import ua.mai.fam.service.PersonService;
import ua.mai.fam.service.PersonServiceMarker;
import ua.mai.fam.util.CollectionUtil;
import ua.mai.fam.util.SpringUtil;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.ResponceStatusExceptionWithCode;
import ua.mai.fam.util.exception.RestErrorCodes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ua.mai.fam.FamRestServerApplication.APPLICATION_REST_PATH;

@Controller
@RequestMapping(PersonRestController.REST_PERSON_PATH)
public class PersonRestController implements BaseController {

    public static final String REST_PERSON_PATH = APPLICATION_REST_PATH  + "/persons";

    private boolean jdbcImplementation;
    private PersonService service;
    private PersonDtoRepository dtoRepository;

    @Autowired
    public PersonRestController(PersonServiceMarker service, PersonRepositoryMarker repository) {
        if (service instanceof PersonDtoService) {
            jdbcImplementation = true;
            this.dtoRepository = (PersonDtoRepository)repository;
            System.out.println("** Use PersonDtoRepository");
        }
        else {
            jdbcImplementation = false;
            this.service = (PersonService)service;
            System.out.println("** Use " + SpringUtil.unproxyBean(service).getClass().getSimpleName());
        }
    }


    @Override
    public boolean isJdbcImplementation() {
        return jdbcImplementation;
    }
    @Override
    public PersonService getService() {
        if (!jdbcImplementation) {
            return service;
        }
        else {
            throw new RuntimeException("Controller " + this.getClass().getSimpleName() +
                " that used DTO(JDBC) repository cannot use service JPA! ");
        }
    }
    @Override
    public PersonDtoRepository getDtoRepository() {
        if (jdbcImplementation) {
            return dtoRepository;
        }
        else {
            throw new RuntimeException("Controller " + this.getClass().getSimpleName() +
                " that used JPA service cannot used DTO repository! ");
        }
    }


    @PostMapping()
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Не используется!", response = String.class),
//        @ApiResponse(code = 201, response = Payment.class),
//        @ApiResponse(code = 400, message = "Ошибка.", response = ErrorDetails.class)})
    public ResponseEntity<PersonDto> add(@RequestBody PersonDto personDto) {
        try {
            if (isJdbcImplementation()) {
                personDto = dtoRepository.insert(personDto);
            }
            else {
                Person person = personDto.toEntity();
                person = service.insert(person);
                personDto = person.toDto();
            }
            URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(personDto.getId())
                .toUri();
            return ResponseEntity.created(uri).body(personDto); //, HttpStatus.CREATED/*201*/);
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
    public ResponseEntity<PersonDto> save(@RequestBody(required=true) PersonDto personDto,
                                          @PathVariable(required=true) Long id) {
        if (!id.equals(personDto.getId())) {
            throw new ResponceStatusExceptionWithCode(HttpStatus.BAD_REQUEST,
                "id in path (" + id + ") is not equal in Entity (" + personDto.getId() + ")",
                RestErrorCodes.NOT_EQUAL_IDS_WHEN_UPDATE_CODE);
        }
        if (isJdbcImplementation()) {
            personDto = dtoRepository.save(personDto);
        }
        else {
            Person person = personDto.toEntity();
            person = service.save(person);
            personDto = person.toDto();
        }
        return new ResponseEntity<PersonDto>(personDto, HttpStatus.NO_CONTENT/*204*/);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable(required=true) Long id) {
        if (isJdbcImplementation()) {
            dtoRepository.deleteById(id);
        }
        else {
            service.deleteById(id);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT/*204*/);
    }

    @GetMapping()
    public @ResponseBody List<PersonDto> findAll(/*HttpServletResponse response*/) {
        Iterable<PersonDto> iter;
        if (isJdbcImplementation()) {
            return CollectionUtil.makeList(dtoRepository.findAll());
        }
        else {
            return Person.toDtos(CollectionUtil.makeList(service.findAll()));
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable(required=true) Long id) {
        Optional<PersonDto> personDto = Optional.empty();
        if (isJdbcImplementation()) {
            personDto = dtoRepository.findById(id);
        }
        else {
            Optional<Person> person = service.findById(id);
            if (person.isPresent()) {
                personDto = Optional.of(person.get().toDto());
            }
        }

        if (!personDto.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND/*404*/);
        }
        return new ResponseEntity<PersonDto>(personDto.get(), HttpStatus.OK/*200*/);
    }

}
