package mai.ua.fam.model;
import java.time.LocalDateTime;

/**
 */
public class Person {
    private @org.springframework.data.annotation.Id Long id;
    private String surname;
    private String firstName;
    private String middleName;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private char gender;
}
