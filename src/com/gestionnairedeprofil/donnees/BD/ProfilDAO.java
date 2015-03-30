/* Gestionnaire de profil
 * Programme used to manage all profils from a database and send them
 * to a SD Card
 * Copyright (C) 2014-2015 TIS Fakri
 * See COPYING for Details
 * 
 * This file is part of Gestionnaire de profil.
 *
 * Gestionnaire de profil is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gestionnaire de profil is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gestionnairedeprofil.donnees.BD;

import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import com.gestionnairedeprofil.donnees.structures.Profil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class ProfilDAO
{

    public static void saveProfil(Profil profil, int idMachine)
    {
        deleteProfilById(profil.getId());
        int max = addProfilInDatabase(profil.getNom(), idMachine);
        profil.setId(max);
        AddAssociationInDatabase(profil, max);
    }

    public static ArrayList<Profil> getAllProfilByMachineId(int id)
    {
        ArrayList<Profil> ArrayListProfils = new ArrayList();
        String query = "SELECT * "
                + "FROM Profils "
                + "WHERE machineId=" + id;
        try {
            Statement stmt = Connexion.getNewStatement();
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
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ArrayListProfils;
    }

    public static void deleteProfilById(int id)
    {
        String query = "DELETE FROM Profils WHERE id=" + id;
        try {
            Statement stmt = Connexion.getNewStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        AssociationDAO.deleteOnCascade(id);
    }

    private static int getMaxProfil()
    {
        int max = 0;
        String query = "SELECT MAX(id) as max FROM Profils";
        try {
            Statement stmt = Connexion.getNewStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            max = rs.getInt("max");
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return max;
    }

    private static int addProfilInDatabase(String nomProfil, int idMachine)
    {
        String query = "INSERT INTO Profils (nom,machineId) VALUES ('" + nomProfil + "'," + idMachine + ")";
        try {
            Statement stmt = Connexion.getNewStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return getMaxProfil();
    }

    private static void AddAssociationInDatabase(Profil profil, int max)
    {
        for (int numAssociationsDansProfil = 0; numAssociationsDansProfil < 12; numAssociationsDansProfil++) {
            AssociationsDansProfil adpTraite = profil.getAssociationsAt(numAssociationsDansProfil);

            for (int numAssociationTraite = 0; numAssociationTraite < adpTraite.size(); numAssociationTraite++) {
                int nbrTouche = profil.getAssociationsAt(numAssociationsDansProfil).get(numAssociationTraite).getTouches().size();

                for (int numToucheTraite = 0; numToucheTraite < nbrTouche; numToucheTraite++) {
                    boolean estAutoFire = profil.getAssociationsAt(numAssociationsDansProfil).get(numAssociationTraite).isEstAutofire();
                    int estAutoFireInt = 0;
                    if (estAutoFire) {
                        estAutoFireInt = 1;
                    }
                    int timer = profil.getAssociationsAt(numAssociationsDansProfil).get(numAssociationTraite).getTimer();
                    int idTouche = profil.getAssociationsAt(numAssociationsDansProfil).get(numAssociationTraite).getTouches().get(numToucheTraite).getId();
                    String query = "INSERT INTO Associations (estAutoFire,timer,toucheNumIHM,idProfil,idToucheMachine) "
                            + "VALUES ('" + estAutoFireInt + "'," + timer + "," + numAssociationsDansProfil + "," + max + "," + idTouche + ")";
                    try {
                        Statement stmt = Connexion.getNewStatement();
                        stmt.executeUpdate(query);
                        stmt.close();
                    }
                    catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": AddAssociationInDatabase " + e.getMessage());
                        System.exit(0);
                    }
                }

            }
        }
    }

}
