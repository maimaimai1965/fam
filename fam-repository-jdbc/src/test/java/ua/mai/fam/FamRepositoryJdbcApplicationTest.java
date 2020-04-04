package ua.mai.fam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryJdbc.class)
//@ActiveProfiles({Profiles.DB_H2, Profiles.DA_DATA_REST})
class FamRepositoryJdbcApplicationTest {

    @Test
    void contextLoads() {
    }

}
