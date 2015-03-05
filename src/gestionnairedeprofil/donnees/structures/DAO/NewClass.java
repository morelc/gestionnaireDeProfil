package gestionnairedeprofil.donnees.structures.DAO;

import java.sql.*;
import gestionnairedeprofil.donnees.structures.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class NewClass {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static ArrayList getAll() {
        ArrayList<Machine> ArrayListMachine = null;
        String query = "SELECT *"
                + "FROM machines";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            /**
             * traitement
             */
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return ArrayListMachine;
    }
}
