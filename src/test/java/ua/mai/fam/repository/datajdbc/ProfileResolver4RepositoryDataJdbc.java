package ua.mai.fam.repository.datajdbc;

import org.springframework.test.context.ActiveProfilesResolver;
import ua.mai.profile.TestProfiles;

/**
 * Возвращает список профилей для тестирования DATA_JDBC репозитария.
 */
//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ProfileResolver4RepositoryDataJdbc implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[] { TestProfiles.DB_IMPLEMENTATION,
                              TestProfiles.DA_DATA_JDBC};
    }
}