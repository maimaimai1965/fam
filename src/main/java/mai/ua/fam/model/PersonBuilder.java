package mai.ua.fam.model;

import mai.ua.fam.model.datajdbc.Person4DataJdbc;

import java.time.LocalDateTime;

public class PersonBuilder {

    private Long id;
    private String surname;
    private String firstName;
    private String middleName;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private char gender;

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

    public PersonBuilder setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonBuilder setDeathDate(LocalDateTime deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    public PersonBuilder setGender(char gender) {
        this.gender = gender;
        return this;
    }

    public Person4DataJdbc createPerson4DataJdbc() {
        return new Person4DataJdbc(id, surname, firstName, middleName, birthDate, deathDate, gender);
    }

}