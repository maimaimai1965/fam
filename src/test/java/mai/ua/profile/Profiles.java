package mai.ua.profile;

public class Profiles {
    public static final String
           DB_H2       = "db-h2",
           DB_H2_FILE  = "db-h2-file",
           DB_ORACLE   = "db-oracle",
           DB_POSTGRES = "db-postgres",
           HEROKU = "heroku";

    public static final String DB_IMPLEMENTATION = DB_POSTGRES;

    public static final String
           DA_JDBC      = "da-jdbc",
           DA_DATA_JDBC = "da-data-jdbc",
           DA_DATA_JPA  = "da-data-jpa",
           DA_DATA_REST = "da-data-rest";

    public static final String REPOSITORY_IMPLEMENTATION = DA_DATA_REST;

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
                return Profiles.DB_H2;
            }
            catch (ClassNotFoundException e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    return Profiles.DB_ORACLE;
                }
                catch (ClassNotFoundException exs) {
                    throw new IllegalStateException("Could not find DB driver");
                }
            }
        }
    }

}
