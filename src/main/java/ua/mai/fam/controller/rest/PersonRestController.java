package ua.mai.fam.controller.rest;

import org.springframework.context.annotation.Profile;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ua.mai.fam.FamApplication.APPLICATION_REST_PATH;

@RestController
@RequestMapping("/api")
@Profile("ac-rest")
public class PersonRestController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static final String FAM_PERSON_PATH = APPLICATION_REST_PATH  + "/persons";

    private PersonRepository personRepository;

    @Autowired
    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping()
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Не используется!", response = String.class),
//        @ApiResponse(code = 201, message = "Платеж клиента PBX из системы eKassir добавлен в систему T-IC.", response = Payment.class),
//        @ApiResponse(code = 400, message = "Ошибка.", response = ErrorDetails.class)})
    public ResponseEntity<Person> add(
               @RequestBody
//               @ApiParam(value = "Информация о платеже клиента PBX.", required = true)
               Person person) {
        LOG.debug("PaymentController. Add Payment: transaction_id={}, invoice_num={}, payment_summa={}");
//                payment.getTransaction_id(), payment.getInvoice_num(), payment.getPayment_summa());

        person = personRepository.insert(person);
        return new ResponseEntity<Person>(person, HttpStatus.CREATED/*201*/);
    }

}
