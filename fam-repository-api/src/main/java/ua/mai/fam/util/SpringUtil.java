package ua.mai.fam.util;

import org.springframework.aop.framework.Advised;

public class SpringUtil {

    public SpringUtil() {}

    public static <T> T unproxyBean(T possiblyProxiedObject) {
        if (possiblyProxiedObject instanceof Advised) {
            try {
                return (T) ((Advised) possiblyProxiedObject).getTargetSource().getTarget();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return possiblyProxiedObject;
    }

    /**
     * Проверяет соответствие используемого в DataSource драйвера профилю dbProfile.<br>
     * Если драйвер не соответствует, то выбрасывает {@link RuntimeException}.
     *
     * @param dbProfile
     * @param ds
     */
    public static void checkActiveDbProfile(String dbProfile, javax.sql.DataSource ds) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource)ds;
        String nedeedDbProfile = "";
        String driverClassName = dataSource.getDriverClassName();
        switch (driverClassName) {
            case "org.h2.Driver" :
                nedeedDbProfile = Profiles.DB_H2;
                break;
            case "org.postgresql.Driver" :
                nedeedDbProfile = Profiles.DB_POSTGRES;
                break;
            case "oracle.jdbc.driver.OracleDriver":
                nedeedDbProfile = Profiles.DB_ORACLE;
                break;
        }
        if (nedeedDbProfile.equals(dbProfile)) {
            System.out.println("** Loaded driver '" + driverClassName + "' for db profile '" + dbProfile + "'");
        } else {
            throw new RuntimeException("Unexpected driver '" + driverClassName + "' for db profile '" +
                nedeedDbProfile +"'!");
        }
    }

}
