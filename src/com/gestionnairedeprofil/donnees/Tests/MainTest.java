/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionnairedeprofil.donnees.Tests;

import com.gestionnairedeprofil.donnees.BD.AssociationDAO;
import com.gestionnairedeprofil.donnees.BD.MachineDAO;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import com.gestionnairedeprofil.donnees.structures.Machine;
import com.gestionnairedeprofil.donnees.structures.Profil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Fakri
 */
public class MainTest {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
       ArrayList<Machine> test = MachineDAO.getAll();
       while(true);
    }

    static void Type(int id) {
        String query = "Requete";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            /*traitement*/
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");

    }
}
