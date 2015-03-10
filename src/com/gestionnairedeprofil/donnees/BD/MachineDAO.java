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

import com.gestionnairedeprofil.donnees.structures.Machine;
import com.gestionnairedeprofil.donnees.structures.Profil;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Fakri
 */
public class MachineDAO
{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static ArrayList getAll()
    {
        ArrayList<Machine> ArrayListMachine = new ArrayList();
        String query = "SELECT * "
                + "FROM machine";
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
                String chemin = rs.getString("cheminImage");
                Machine machine = new Machine(id, name, chemin);

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
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully1");
        return ArrayListMachine;
    }
}
