package ua.mai.profile;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfilesResolver;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
@Component
public class AllActiveProfileResolver implements ActiveProfilesResolver {

//    public AllActiveProfileResolver() {
//        super();
//    }
//
//    @Autowired
//    public void setTestDbImplementation(
//        @Value("${application.test.db-implementation}") String testDbImplementation) {
//        this.testDbImplementation = testDbImplementation;
//    }
//
//    @Autowired
//    public void setTestRepositoryImplementation(
//        @Value("${application.test.repository-implementation}") String testRepositoryImplementation) {
//        this.testRepositoryImplementation = testRepositoryImplementation;
//    }
//
//    private String testDbImplementation;
//    private String testRepositoryImplementation;

    @Override
    public String[] resolve(Class<?> aClass) {
//        if (testDbImplementation != null) {
//        }
//        if (testRepositoryImplementation != null) {
//        }
        return new String[]{TestProfiles.DA_IMPLEMENTATION, TestProfiles.DB_IMPLEMENTATION};
//        return new String[]{Profiles.REPOSITORY_IMPLEMENTATION, Profiles.getActiveDbProfile()};
    }
}