package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/studentdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ria@123";  // change this

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // THIS METHOD CREATES TABLE AUTOMATICALLY
    public static void createTableIfNotExists() throws Exception {

        Connection con = getConnection();
        Statement stmt = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS student ("
                + "id SERIAL PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "email VARCHAR(100) NOT NULL, "
                + "age INTEGER CHECK (age > 0), "
                + "mobile VARCHAR(10) CHECK (mobile ~ '^[0-9]{10}$')"
                + ")";

        stmt.executeUpdate(sql);

        stmt.close();
        con.close();

        System.out.println("Table checked/created successfully.");
    }
}