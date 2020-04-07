package ua.mai.fam.model.person;

import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.util.HasId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 */
//--For Data JPA
@javax.persistence.Entity
public class FamilyPerson extends Person {


    private List<Person> relatives;

}
