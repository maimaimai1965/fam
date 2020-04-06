package ua.mai.fam.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.util.HasId;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

/**
 */
@JsonInclude(JsonInclude.Include.NON_NULL)  //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
public class Person implements HasId<Long> {

//    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//    @org.springframework.data.annotation.Id
    //Data JDBC
    @org.springframework.data.annotation.Id
    //JDBC
    @javax.persistence.Id
    private Long id;

    @NotEmpty
    private String surname;

    private String firstName;

    private String middleName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deathDate;

    private String gender;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person() {}

    public Person(Long id, String surname, String firstName, String middleName, LocalDate birthDate,
                  LocalDate deathDate, String gender) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person personTo = (Person) o;
        return Objects.equals(id, personTo.id) &&
            surname.equals(personTo.surname) &&
            Objects.equals(firstName, personTo.firstName) &&
            Objects.equals(middleName, personTo.middleName) &&
            Objects.equals(birthDate, personTo.birthDate) &&
            Objects.equals(deathDate, personTo.deathDate) &&
            Objects.equals(gender, personTo.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", surname='" + surname + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", gender='" + gender + '\'' +
            '}';
    }

}
