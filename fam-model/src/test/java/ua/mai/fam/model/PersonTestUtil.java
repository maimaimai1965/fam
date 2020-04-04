package ua.mai.fam.model;

import ua.mai.fam.model.person.Person;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTestUtil {

    public static void assertMatch(Person actual, Person expected) {
        assertThat(actual)//.isEqualToIgnoringGivenFields(expected, "registered", "roles");
                          .isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Person> actual, Person... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Person> actual, Iterable<Person> expected) {
        assertThat(actual)//.usingElementComparatorIgnoringFields("registered", "roles")
                          .isEqualTo(expected);
    }

}
