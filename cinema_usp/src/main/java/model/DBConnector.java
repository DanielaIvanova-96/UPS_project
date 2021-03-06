package model;

import java.sql.*;

final public class DBConnector {

    private static Connection con;

    private static final String url = "jdbc:mysql://localhost:3306/uspProject";
    private static final String user = "root";
    private static final String password = "";

    /**
     * Sets up the url , username and password for the database connection
     */
    public static void setUpConncetion() {

        String query = "SELECT VERSION()";

        try {
            con = DriverManager.getConnection(url, user, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * If connection is set returns the connection
     * if connection is not set, sets it and returns the connection
     *
     * @return connection to the database
     */
    public static Connection getConnection() {
        if (con == null) {
            setUpConncetion();
        }
        return con;
    }

    /**
     * Closes the connection to the database
     */
    public static void Disconnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
