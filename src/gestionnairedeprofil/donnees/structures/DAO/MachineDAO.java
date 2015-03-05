package gestionnairedeprofil.donnees.structures.DAO;

import java.sql.*;
import gestionnairedeprofil.donnees.structures.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class MachineDAO {

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
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int id = rs.getInt("id");
                String name = rs.getString("nom");
                String chemin = rs.getString("chemin");
                Machine machine = new Machine(id, name);

                /*creation et association de l'ArrayListProfilAssocies à la machine*/
                ArrayList<Profil> ArrayListProfilsAssocies = null;
                ArrayListProfilsAssocies = ProfilDAO.getAllByMachineId(id);
                machine.setProfils(ArrayListProfilsAssocies);

                /*creation et association de l'ArrayListToucheMachineAssocies à la machine*/
                ArrayList<ToucheMachine> ArrayListTouchesMachineAssocies = null;
                ArrayListTouchesMachineAssocies = ToucheMachineDAO.getAllByMachineId(id);
                machine.setTouches(ArrayListTouchesMachineAssocies);
                /*ajout machine à ArrayListMachine*/
                ArrayListMachine.add(machine);
            }
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
