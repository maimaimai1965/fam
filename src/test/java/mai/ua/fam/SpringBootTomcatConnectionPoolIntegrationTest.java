package mai.ua.fam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(SpringExtension.class)
//@TestPropertySource(properties = {
//    "spring.datasource.tomcat.initial-size=10",
//    "spring.datasource.tomcat.max-wait=2000",
//    "spring.datasource.tomcat.max-active=5",
//    "spring.datasource.tomcat.max-idle=5",
//    "spring.datasource.tomcat.min-idle=4",
//    "spring.datasource.tomcat.default-auto-commit=true"
//})
@SpringBootTest
@ActiveProfiles({"db-postgres", "da-jdbc"})
public class SpringBootTomcatConnectionPoolIntegrationTest {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.tomcat.initial-size:-1}")
    private int initialSize;

    @Value("${spring.datasource.tomcat.max-wait:-1}")
    private int maxWait;

    @Value("${spring.datasource.tomcat.max-active:-1}")
    private int maxActive;

    @Value("${spring.datasource.tomcat.max-idle:-1}")
    private int maxIdle;

    @Value("${spring.datasource.tomcat.min-idle:-1}")
    private int minIdle;

    @Value("${spring.datasource.tomcat.default-auto-commit:false}")
    private boolean defaultAutoCommit;

    @Test
    public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
        String dataSourceName = dataSource.getClass().getName();
        assertThat(dataSourceName)
            .isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
        DataSource tomcatDataSource = dataSource;
        if (initialSize != -1) {
            assertThat(tomcatDataSource.getInitialSize()).isEqualTo(initialSize);
        }
        if (initialSize != -1) {
            assertThat(tomcatDataSource.getMaxWait()).isEqualTo(maxWait);
        }
        if (initialSize != -1) {
            assertThat(tomcatDataSource.getMaxActive()).isEqualTo(maxActive);
        }
        if (initialSize != -1) {
            assertThat(tomcatDataSource.getMaxIdle()).isEqualTo(maxIdle);
        }
        if (initialSize != -1) {
            assertThat(tomcatDataSource.getMinIdle()).isEqualTo(minIdle);
        }
        if (defaultAutoCommit) {
            assertThat(tomcatDataSource.isDefaultAutoCommit()).isEqualTo(defaultAutoCommit);
        }
    }

}