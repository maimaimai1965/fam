package ua.mai.fam;

import org.springframework.test.context.ActiveProfilesResolver;
import ua.mai.profile.TestProfiles;

/**
 * Возвращает список профилей для тестирования DATA_JDBC репозитария.
 */
//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ProfileResolver4RepositoryJpa implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[] {
                              TestProfiles.DB_H2,
//                              TestProfiles.DB_POSTGRES,
//                              TestProfiles.DB_IMPLEMENTATION,

                              TestProfiles.DA_DATA_JDBC
                            };
    }
}