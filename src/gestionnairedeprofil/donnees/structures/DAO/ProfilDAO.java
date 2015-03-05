package gestionnairedeprofil.donnees.structures.DAO;

import java.sql.*;
import gestionnairedeprofil.donnees.structures.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class ProfilDAO {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static ArrayList<Profil> getAllByMachineId(int id) {
        ArrayList<Profil> ArrayListProfils = null;
        String query = "SELECT * "
                + "FROM Profils "
                + "WHERE id=" + id;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int idP = rs.getInt("id");
                String name = rs.getString("nom");
                Profil profil = new Profil(idP, name);

                /*creation et association de l'ArrayListAssociationDansProfil à l'objet Profil*/
                for (int i = 0; i < 12; i++) {
                    AssociationsDansProfil associationDansProfil = new AssociationsDansProfil();
                    associationDansProfil = AssociationDAO.getAssociationByIdProfilAndIdAssociation(idP, i);
                    profil.setAssociationsAt(i, associationDansProfil);
                }
                /*ajout machine à ArrayListMachine*/
                ArrayListProfils.add(profil);
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return ArrayListProfils;
    }
}
