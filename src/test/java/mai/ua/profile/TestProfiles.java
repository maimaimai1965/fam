package mai.ua.profile;

public class TestProfiles {

    //---------------
    public static final String DB_H2       = "db-h2";
    public static final String DB_H2_FILE  = "db-h2-file";
    public static final String DB_POSTGRES = "db-postgres";
    public static final String DB_ORACLE   = "db-oracle";
    public static final String HEROKU      = "heroku";

    //---------------
    public static final String DA_JDBC      = "da-jdbc";
    public static final String DA_DATA_JDBC = "da-data-jdbc";
    public static final String DA_DATA_JPA  = "da-data-jpa";

    public static final String REPOSITORY_IMPLEMENTATION = DA_JDBC;

    public static final String DA_DATA_REST = "da-data-rest";

    // Текущие тестируемые профили.
    public static final String DB_IMPLEMENTATION =
        DB_H2;
//        DB_POSTGRES;
    public static final String REST_IMPLEMENTATION =
        DA_JDBC;
//        DA_DATA_JPA;

    /**
     * Get DB profile depending of DB driver in classpath
     *
     * @return  DB profile
     */
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return DB_POSTGRES;
        }
        catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.h2.Driver");
                return TestProfiles.DB_H2;
            }
            catch (ClassNotFoundException e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    return TestProfiles.DB_ORACLE;
                }
                catch (ClassNotFoundException exs) {
                    throw new IllegalStateException("Could not find DB driver");
                }
            }
        }
    }

}
