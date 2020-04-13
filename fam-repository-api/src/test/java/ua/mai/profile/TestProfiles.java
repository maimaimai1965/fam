package ua.mai.profile;

import ua.mai.fam.util.Profiles;

public class TestProfiles {

    //---- Текущие тестируемые профили. ------------------------
    // Data Base
    public static final String DB_IMPLEMENTATION =
//        Profiles.DB_H2;
        Profiles.DB_POSTGRES;

    // Data
    public static final String DA_IMPLEMENTATION =
        Profiles.DA_JDBC;
//        Profiles.DA_DATA_JDBC;
//        Profiles.DA_JPA;
//        Profiles.DA_DATA_JPA;

    // Access
    public static final String AC_IMPLEMENTATION =
        Profiles.AC_REST;
//        Profiles.AC_DATA_JPA;


    /**
     * Get DB profile depending of DB driver in classpath
     *
     * @return  DB profile
     */
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return Profiles.DB_POSTGRES;
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
