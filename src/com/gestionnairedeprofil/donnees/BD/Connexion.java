/* Gestionnaire de profil
 * Programme used to manage all profils from a database and send them
 * to a SD Card
 * Copyright (C) 2014-2015 MOREL Charles
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton vers la connexion à la BD
 *
 * Singleton used to connect to DB
 *
 * @author MOREL Charles
 */
public class Connexion
{
    
    private static Connection CONNEXION_COURRANTE;
    private final static String JDBC_DRIVER = "org.sqlite.JDBC";
    
    public static Statement getNewStatement() throws ClassNotFoundException, SQLException
    {
        if(Connexion.CONNEXION_COURRANTE == null)
        {
            Class.forName(JDBC_DRIVER);
            Connexion.CONNEXION_COURRANTE = DriverManager.getConnection("jdbc:sqlite:dbsqlite");
        }
        return Connexion.CONNEXION_COURRANTE.createStatement();
    }
}
