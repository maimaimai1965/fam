package ua.mai.fam.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.model.Gender;
import ua.mai.fam.model.person.Person;
import ua.mai.fam.util.HasId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 */
@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//Для DATA JDBC
//https://stackoverflow.com/questions/53063266/how-to-map-entity-to-table-in-spring-data-jdbc#53077343
@org.springframework.data.relational.core.mapping.Table("PERSON")
public class PersonDto implements HasId<Long> {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    private Long id;

    @Basic
    @NotNull
    @Column(name = "surname", length = 50)
    private String surname;

    @Basic
    @Column(name = "first_name", length = 25)
    private String firstName;

    @Basic
    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "death_date")
    private LocalDate deathDate;

    @Basic
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private Gender gender;


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

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }


    /* */
    @Override
    public String getTableName() {
        return "PERSON";
    }


    public PersonDto() {}

    public PersonDto(Long id, String surname, String firstName, String middleName, LocalDate birthDate,
                     LocalDate deathDate, Gender gender) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.gender = gender;
    }

    public Person toEntity() {
        return new Person(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

    public static List<Person> toEntities(Collection<PersonDto> dtos)  {
        return dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        return "PersonDto {" +
            "id=" + id +
            ", surname='" + surname + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", gender='" + gender + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDto personDto = (PersonDto) o;

        if (!id.equals(personDto.id)) return false;
        if (!surname.equals(personDto.surname)) return false;
        if (firstName != null ? !firstName.equals(personDto.firstName) : personDto.firstName != null) return false;
        if (middleName != null ? !middleName.equals(personDto.middleName) : personDto.middleName != null) return false;
        if (birthDate != null ? !birthDate.equals(personDto.birthDate) : personDto.birthDate != null) return false;
        if (deathDate != null ? !deathDate.equals(personDto.deathDate) : personDto.deathDate != null) return false;
        return gender == personDto.gender;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (deathDate != null ? deathDate.hashCode() : 0);
        result = 31 * result + gender.hashCode();
        return result;
    }
}
