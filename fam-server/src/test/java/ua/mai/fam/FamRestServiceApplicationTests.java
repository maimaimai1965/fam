package ua.mai.fam;

import ua.mai.profile.AllActiveProfileResolver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(resolver = AllActiveProfileResolver.class)
//@ActiveProfiles({Profiles.DB_H2, Profiles.DA_DATA_REST})
class FamRestServiceApplicationTests {
    @Test
    void contextLoads() {
    }
}
