package ua.mai.fam.dto;

import ua.mai.fam.model.Gender;
import ua.mai.fam.dto.PersonDto;

import java.time.LocalDate;

public class PersonDtoBuilder {

    private Long id;
    private Long version;
    private String surname;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Gender gender = Gender.U;

    public PersonDtoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonDtoBuilder setVersion(Long version) {
        this.version = version;
        return this;
    }

    public PersonDtoBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonDtoBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonDtoBuilder setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonDtoBuilder setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonDtoBuilder setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    public PersonDtoBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PersonDto createPersonDto() {
        return new PersonDto(id, version, surname, firstName, middleName, birthDate, deathDate, gender);
    }

}