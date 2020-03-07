package mai.ua.fam.model.person;

import mai.ua.fam.model.datajdbc.Person4DataJdbc;

import java.time.LocalDate;

public class PersonBuilder {

    private Long id;
    private String surname;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String gender;

    public PersonBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonBuilder setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonBuilder setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    public PersonBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Person4DataJdbc createPerson4DataJdbc() {
        return new Person4DataJdbc(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

    public PersonTo createPersonTo() {
        return new PersonTo(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

}