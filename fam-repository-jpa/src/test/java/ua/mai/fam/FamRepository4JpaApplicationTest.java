package ua.mai.fam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(resolver = ProfileResolver4RepositoryJpa.class)
class FamRepository4JpaApplicationTest {

    @Test
    void contextLoads() {
    }

}
