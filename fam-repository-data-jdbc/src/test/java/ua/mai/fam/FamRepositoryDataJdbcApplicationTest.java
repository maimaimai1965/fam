package ua.mai.fam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryDataJdbc.class)
class FamRepositoryDataJdbcApplicationTest {

    @Test
    void contextLoads() {
    }

}
