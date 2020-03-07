package mai.ua.fam;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(AbstractTimingExtension.class)
public abstract class AbstractTimingTest {

//    @Autowired
//    private Environment env;
//
//    boolean isJpaBased() {
////        return Arrays.stream(env.getActiveProfiles()).noneMatch(Profiles.JDBC::equals);
//        return env.acceptsProfiles(org.springframework.core.env.Profiles.of(Profiles.JPA, Profiles.DATAJPA));
//    }

//    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
//    <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
//        assertThrows(exceptionClass, () -> {
//            try {
//                runnable.run();
//            } catch (Exception e) {
//                throw getRootCause(e);
//            }
//        });
//    }
}