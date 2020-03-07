package mai.ua.fam.model;

import mai.ua.fam.model.person.PersonTo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTo4TestUtil {

    public static void assertMatch(PersonTo actual, PersonTo expected) {
        assertThat(actual)//.isEqualToIgnoringGivenFields(expected, "registered", "roles");
                          .isEqualTo(expected);
    }

    public static void assertMatch(Iterable<PersonTo> actual, PersonTo... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<PersonTo> actual, Iterable<PersonTo> expected) {
        assertThat(actual)//.usingElementComparatorIgnoringFields("registered", "roles")
                          .isEqualTo(expected);
    }

}
