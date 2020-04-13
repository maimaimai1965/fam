package ua.mai.fam;

import ua.mai.fam.util.Profiles;
import ua.mai.profile.TestProfiles;
import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Возвращает список профилей для тестирования JDBC репозитария.
 */
//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ProfileResolver4RepositoryJdbc implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[] {
                              Profiles.DB_H2,
//                              Profiles.DB_POSTGRES,
//                              Profiles.DB_IMPLEMENTATION,

                              Profiles.DA_JDBC
                            };
    }
}