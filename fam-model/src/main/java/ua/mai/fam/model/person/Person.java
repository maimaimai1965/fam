package ua.mai.fam.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.util.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

/**
 */
@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@javax.persistence.Entity
@javax.persistence.Table(name = "person")
public class Person implements HasId<Long> {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for Data JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name="seq_person", sequenceName="SEQ_PERSON", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Basic
    @Column(name = "first_name", nullable = true, length = 25)
    private String firstName;

    @Basic
    @Column(name = "middle_name", nullable = true, length = 30)
    private String middleName;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "death_date", nullable = true)
    private LocalDate deathDate;

    @Basic
    @Column(name = "gender", nullable = true, length = -1)
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
