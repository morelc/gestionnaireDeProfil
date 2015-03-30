/* Gestionnaire de profil
 * Programme used to manage all profils from a database and send them
 * to a SD Card
 * Copyright (C) 2014-2015 TIS Fakri
 * (DB connection implementation by MOREL Charles)
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
 * along with Gestionnaire de profil.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gestionnairedeprofil.donnees.BD;

import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class ToucheMachineDAO
{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static ArrayList<ToucheMachine> getAllByMachineId(int idMachine)
    {
        ArrayList<ToucheMachine> ArrayTouchesMachine = new ArrayList();
        String query = "SELECT * "
                + "FROM TouchesMachines "
                + "WHERE idMachine = " + idMachine;
        try {
            Statement stmt = Connexion.getNewStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                /*récuperation de l'id et du nom de la machine*/
                int id = rs.getInt("id");
                String name = rs.getString("nom");
                int signal = rs.getInt("signal");
                ToucheMachine touche = new ToucheMachine(id, name, signal);
                /*ajout touche à ArrayListTouche*/
                ArrayTouchesMachine.add(touche);
            }
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return ArrayTouchesMachine;
    }

    public static void getToucheByAssociation(Association assoc, int idP, int idTIHM)
    {
        int timerAssoc = assoc.getTimer();
        String query = "SELECT id,nom,signal "
                + "FROM TouchesMachines "
                + "WHERE id "
                + "IN (SELECT idToucheMachine "
                + "FROM Associations "
                + "WHERE Associations.timer=" + timerAssoc
                + " AND Associations.toucheNumIHM=" + idTIHM
                + " AND Associations.idProfil=" + idP + ")";
        try {
            Statement stmt = Connexion.getNewStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nom");
                int signal = rs.getInt("signal");
                ToucheMachine touche = new ToucheMachine(id, name, signal);
                assoc.ajouterTouche(touche);
            }
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
