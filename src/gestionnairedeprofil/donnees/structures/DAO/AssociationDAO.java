package gestionnairedeprofil.donnees.structures.DAO;

import java.sql.*;
import gestionnairedeprofil.donnees.structures.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class AssociationDAO {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static AssociationsDansProfil getAssociationByIdProfilAndIdAssociation(int idP, int i) {
        AssociationsDansProfil adp = new AssociationsDansProfil();
        String query = "SELECT DISTINCT id,estAutoFire,timer,idToucheMachine "
                + "FROM Associations "
                + "WHERE idProfil=" + idP
                + " AND toucheNumIHM=" + i
                + " GROUP BY timer";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                Boolean estAutoFire = rs.getBoolean("estAutoFire");
                int timer = rs.getInt("timer");
                Association assoc = new Association(id, estAutoFire, timer);
                ToucheMachineDAO.getToucheByAssociation(assoc, idP, i);
                adp.add(assoc);
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return adp;
    }
}
