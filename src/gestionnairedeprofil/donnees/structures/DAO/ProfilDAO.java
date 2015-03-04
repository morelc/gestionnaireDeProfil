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
public class ProfilDAO{
    
    public static void getAll(){
        
    }
        
    public static void delete(int id){
        
    }
     public static void update(int id){
        
    }
    public static void insert(){
        
    }

    static ArrayList<Profil> getAllByMachineId(int id) {
        ArrayList<Profil> ArrayListProfils = null;
        Connexion connexion = new Connexion("Database.db");
        Statement stmt = null;
        String query = "SELECT * FROM Profils WHERE id="+id+"";
        connexion.connect();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int idP = rs.getInt("id");
                String name = rs.getString("nom");
                Profil profil = new Profil(idP,name);
                
                /*creation et association de l'ArrayListAssociationDansProfil à l'objet Profil*/
                for(int i=0;i<12;i++){
                    AssociationsDansProfil associationDansProfil= new AssociationsDansProfil();
                    associationDansProfil = AssociationDAO.getAssociationByIdProfilAndIdAssociation(idP,i);
                    profil.setAssociationsAt(i,associationDansProfil);
                }
                /*ajout machine à ArrayListMachine*/
                ArrayListProfils.add(profil);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        connexion.close();
        return ArrayListProfils;
    }
}
