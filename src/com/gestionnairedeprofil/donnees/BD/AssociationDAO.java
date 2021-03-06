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
 * along with Gestionnaire de profil.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gestionnairedeprofil.donnees.BD;

import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Fakri
 */
public class AssociationDAO
{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static AssociationsDansProfil getAssociationByIdProfilAndIdAssociation(int idP, int i)
    {
        AssociationsDansProfil adp = new AssociationsDansProfil();
        String query = "SELECT DISTINCT id,estAutoFire,timer,idToucheMachine "
                + "FROM Associations "
                + "WHERE idProfil=" + idP
                + " AND toucheNumIHM=" + i
                + " GROUP BY timer";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                Boolean estAutoFire = rs.getBoolean("estAutoFire");
                int timer = rs.getInt("timer");
                Association assoc = new Association(id, estAutoFire, timer);
                ToucheMachineDAO.getToucheByAssociation(assoc, idP, i);
                adp.add(assoc);
            }
            stmt.close();
            c.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return adp;
    }
}
