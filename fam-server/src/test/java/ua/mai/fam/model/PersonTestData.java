package ua.mai.fam.model;

public class PersonTestData {

    public static final String surname01 = "Иванов_TEST";
    public static final String firstName01 = "Сергей";
    public static final String middleName01 = "Игнатьевич";

    public static final String surname02 = "Борисов_TEST";
    public static final String firstName02 = "Владимир";
    public static final String middleName02 = "Анатольевич";

    public static final String surname03 = "Стрельцова_TEST";
    public static final String firstName03 = "Елена";
    public static final String middleName03 = "Яковлевна";

    public static Person getNewPersons01() {
        return new PersonBuilder().setSurname(surname01).setFirstName(firstName01).setMiddleName(middleName01)
                                  .createPerson();
    }

    public static Person getNewPersons02() {
        return new PersonBuilder().setSurname(surname02).setFirstName(firstName02).setMiddleName(middleName02)
                                  .createPerson();
    }

    public static Person getNewPersons03() {
        return new PersonBuilder().setSurname(surname03).setFirstName(firstName03).setMiddleName(middleName03)
            .createPerson();
    }
}
