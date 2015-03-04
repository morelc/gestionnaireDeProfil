/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.donnees.structures.DAO;

import gestionnairedeprofil.donnees.structures.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Fakri
 */
public class MachineDAO {

    public static ArrayList getAll() {
        ArrayList<Machine> ArrayListMachine = null;
        Connexion connexion = new Connexion("Database.db");
        Statement stmt = null;
        String query = "SELECT * FROM machines";
        connexion.connect();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int id = rs.getInt("id");
                String name = rs.getString("nom");
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        connexion.close();
        return ArrayListMachine;
    }
}
