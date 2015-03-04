/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.donnees.structures.DAO;

import gestionnairedeprofil.donnees.structures.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Fakri
 */
public class AssociationDAO {
    static AssociationsDansProfil getAssociationByIdProfilAndIdAssociation(int idP, int i) {
        AssociationsDansProfil adp = new AssociationsDansProfil();
        Connexion connexion = new Connexion("Database.db");
        Statement stmt = null;
        String query = "select distinct id,estAutoFire,timer,idToucheMachine from Associations where idProfil="+idP+" and toucheNumIHM="+i+" group by timer";
        connexion.connect();
        try{
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                Boolean estAutoFire = rs.getBoolean("estAutoFire");
                int timer = rs.getInt("timer");
                Association assoc = new Association(id,estAutoFire,timer);
                ToucheMachineDAO.getToucheByAssociation(assoc,idP,i);
                adp.add(assoc);
            }
        }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        connexion.close();
        return adp;
        
    }
}
