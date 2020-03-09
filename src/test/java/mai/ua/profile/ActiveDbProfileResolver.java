package mai.ua.profile;

import org.springframework.test.context.ActiveProfilesResolver;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{TestProfiles.DB_IMPLEMENTATION};
//        return new String[]{Profiles.getActiveDbProfile()};
    }

}