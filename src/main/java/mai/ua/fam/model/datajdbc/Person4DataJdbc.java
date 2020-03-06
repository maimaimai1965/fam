package mai.ua.fam.model.datajdbc;
import mai.ua.fam.model.Person;

import java.time.LocalDateTime;

/**
 */
public class Person4DataJdbc implements Person {

//    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @org.springframework.data.annotation.Id
    private Long id;

    private String surname;
    private String firstName;
    private String middleName;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private char gender;

    public Person4DataJdbc(Long id, String surname, String firstName, String middleName, LocalDateTime birthDate,
                           LocalDateTime deathDate, char gender) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.gender = gender;
    }
}
