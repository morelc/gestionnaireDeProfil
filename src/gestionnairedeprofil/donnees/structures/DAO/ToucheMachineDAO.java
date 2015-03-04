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
public class ToucheMachineDAO{

    public static ArrayList<ToucheMachine> getAllByMachineId(int idMachine) {
        ArrayList<ToucheMachine> ArrayTouchesMachine = null;
        Connexion connexion = new Connexion("Database.db");
        Statement stmt = null;
        String query = "SELECT * FROM TouchesMachines WHERE idMachine = "+idMachine;
        connexion.connect();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int signal = rs.getInt("signal");
                ToucheMachine touche= new ToucheMachine(id, name,signal);
                /*ajout touche à ArrayListTouche*/
                ArrayTouchesMachine.add(touche);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        connexion.close();
        return ArrayTouchesMachine;
    }

    static void getToucheByAssociation(Association assoc,int idP,int idTIHM) {
        Connexion connexion = new Connexion("Database.db");
        Statement stmt = null;
        int timerAssoc = assoc.getTimer();
        String query = "select id,nom,signal from TouchesMachines where id in (select idToucheMachine from Associations where Associations.timer="+timerAssoc+" and Associations.toucheNumIHM="+idTIHM+" and Associations.idProfil="+idP+")";
        connexion.connect();
                try{
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nom");
                int signal = rs.getInt("signal");
                ToucheMachine touche = new ToucheMachine(id,name,signal);
                assoc.ajouterTouche(touche);
            }
        }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        connexion.close();
    }
}
