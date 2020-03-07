package mai.ua.fam.repository.jdbc;

import mai.ua.profile.Profiles;
import org.springframework.test.context.ActiveProfilesResolver;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ProfileResolver4RepositoryJdbc implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[] { Profiles.DB_IMPLEMENTATION, Profiles.DA_JDBC};
    }
}