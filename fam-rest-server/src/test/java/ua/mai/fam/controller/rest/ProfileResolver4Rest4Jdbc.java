package ua.mai.fam.controller.rest;

import org.springframework.test.context.ActiveProfilesResolver;
import ua.mai.profile.TestProfiles;

/**
 * Возвращает список профилей для тестирования JDBC репозитария.
 */
//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ProfileResolver4Rest4Jdbc implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[] {
                              TestProfiles.DB_IMPLEMENTATION,

                              TestProfiles.DA_JDBC,

                              TestProfiles.AC_REST};
    }
}