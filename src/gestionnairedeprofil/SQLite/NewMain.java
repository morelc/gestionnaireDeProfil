/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.SQLite;

import java.sql.SQLException;

/**
 *
 * @author Fakri
 */
public class NewMain {
    private static Object statement;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connexion connexion = new Connexion("Database.db");
        connexion.connect();
        connexion.close();
    }
    
}
