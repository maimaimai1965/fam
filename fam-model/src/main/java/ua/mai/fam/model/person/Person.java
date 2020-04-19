package ua.mai.fam.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.model.Gender;
import ua.mai.fam.model.ParentChild;
import ua.mai.fam.util.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 */
@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@javax.persistence.Entity
@javax.persistence.Table(name = "PERSON")
public class Person implements HasId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name="seq_person", sequenceName="SEQ_PERSON", allocationSize = 20)
    @Column(name = "id", nullable = false)
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

    @OneToMany(mappedBy = "child", fetch = FetchType.LAZY)
    protected Set<ParentChild> parents = new HashSet<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    protected Set<ParentChild> children = new HashSet<>();


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

    public Set<ParentChild> getParents() {
        return parents;
    }

    public Set<ParentChild> getChildren() {
        return children;
    }

    /* */
    @Override
    public String getTableName() {
        return "PERSON";
    }


    public Person() {}

    public Person(Long id, String surname, String firstName, String middleName, LocalDate birthDate,
                  LocalDate deathDate, Gender gender) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.gender = gender;
    }

    public PersonDto toDto() {
        return new PersonDto(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

    public static List<PersonDto> toDtos(Collection<Person> entities)  {
        return entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
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
