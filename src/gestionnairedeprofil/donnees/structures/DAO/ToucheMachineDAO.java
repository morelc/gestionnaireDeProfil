package gestionnairedeprofil.donnees.structures.DAO;

import java.sql.*;
import gestionnairedeprofil.donnees.structures.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class ToucheMachineDAO {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static ArrayList<ToucheMachine> getAllByMachineId(int idMachine) {
        ArrayList<ToucheMachine> ArrayTouchesMachine = null;
        String query = "SELECT * "
                + "FROM TouchesMachines "
                + "WHERE idMachine = " + idMachine;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int signal = rs.getInt("signal");
                ToucheMachine touche = new ToucheMachine(id, name, signal);
                /*ajout touche à ArrayListTouche*/
                ArrayTouchesMachine.add(touche);
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return ArrayTouchesMachine;
    }

    public static void getToucheByAssociation(Association assoc, int idP, int idTIHM) {
        int timerAssoc = assoc.getTimer();
        String query = "SELECT id,nom,signal "
                + "FROM TouchesMachines "
                + "WHERE id "
                + "IN (SELECT idToucheMachine "
                    + "FROM Associations "
                    + "WHERE Associations.timer=" + timerAssoc
                    + " AND Associations.toucheNumIHM=" + idTIHM
                    + " AND Associations.idProfil=" + idP + ")";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nom");
                int signal = rs.getInt("signal");
                ToucheMachine touche = new ToucheMachine(id, name, signal);
                assoc.ajouterTouche(touche);
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
