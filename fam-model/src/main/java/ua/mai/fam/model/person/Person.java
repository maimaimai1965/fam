package ua.mai.fam.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import ua.mai.fam.dto.PersonDto;
import ua.mai.fam.model.Gender;
import ua.mai.fam.model.ParentChild;
import ua.mai.fam.util.HasId;
import ua.mai.fam.util.ToDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 */
//--For JPA
@javax.persistence.Entity
@javax.persistence.Table(name = "PERSON")
public class Person implements HasId<Long>, ToDto<PersonDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name="seq_person", sequenceName="SEQ_PERSON", allocationSize = 20)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Basic
    @NotNull
    @Column(name = "SURNAME", length = 50)
    private String surname;

    @Basic
    @Column(name = "FIRST_NAME", length = 25)
    private String firstName;

    @Basic
    @Column(name = "MIDDLE_NAME", length = 30)
    private String middleName;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DEATH_DATE")
    private LocalDate deathDate;

    @Basic
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 1)
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

    @Override
    public PersonDto toDto() {
        return new PersonDto(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

    public static List<PersonDto> toDtos(Collection<Person> entities)  {
        return (List<PersonDto>)ToDto.toDtos(entities);
//        return entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getId().equals(person.getId())) return false;
        if (!getSurname().equals(person.getSurname())) return false;
        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(person.getMiddleName()) : person.getMiddleName() != null)
            return false;
        if (getBirthDate() != null ? !getBirthDate().equals(person.getBirthDate()) : person.getBirthDate() != null)
            return false;
        if (getDeathDate() != null ? !getDeathDate().equals(person.getDeathDate()) : person.getDeathDate() != null)
            return false;
        return getGender() == person.getGender();
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSurname().hashCode();
        return result;
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
