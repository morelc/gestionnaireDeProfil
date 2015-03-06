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

import com.gestionnairedeprofil.donnees.structures.Profil;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class ProfilDAO
{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    static ArrayList<Profil> getAllByMachineId(int id)
    {
        ArrayList<Profil> ArrayListProfils = new ArrayList();
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
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return ArrayListProfils;
    }
}
