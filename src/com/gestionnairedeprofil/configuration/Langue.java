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
 * along with Gestionnaire de profil.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gestionnairedeprofil.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Classe utilisée pour représenter les paramètres de langue de l'application.
 *
 * Class used to represent language parameters in app.
 *
 * @author MOREL Charles
 */
public class Langue
{

    private final static String DOSSIER_FICHIERS_DE_LANGUES = "Languages";
    private final static String PREFIXE_FICHIER_LANGUE = "lang_";
    private final static String EXTENSION_FICHIER_LANGUE = ".properties";
    private final static String NOM_FICHIER_DEFINITION_LANGUES = "languagesAvailables.txt";

    private static ResourceBundle ressourcesLangue = null;
    private final String label;
    private final String configuration;

    public Langue(String labelLangue, String configurationLangue)
    {
        this.label = labelLangue;
        this.configuration = configurationLangue;
    }

    @Override
    public String toString()
    {
        return this.label;
    }

    public String getConfigLangue()
    {
        return this.configuration;
    }

    public static ArrayList getToutsLesLanguages() throws IOException
    {
        ArrayList<Langue> languesARetourner = new ArrayList();
        BufferedReader bufferLecture = new BufferedReader(new FileReader(Configuration.DOSSIER_DES_DEPENDANCES + File.separator + Langue.NOM_FICHIER_DEFINITION_LANGUES));
        String ligneLue;
        while ((ligneLue = bufferLecture.readLine()) != null) {
            languesARetourner.add(new Langue(ligneLue.split(":")[0], ligneLue.split(":")[1]));
        }
        bufferLecture.close();
        return languesARetourner;
    }

    public static ResourceBundle getLangue()
    {
        if (Langue.ressourcesLangue == null) {
            try {
                FileInputStream fis = new FileInputStream(Configuration.DOSSIER_DES_DEPENDANCES + File.separator
                        + Langue.DOSSIER_FICHIERS_DE_LANGUES + File.separator
                        + Langue.PREFIXE_FICHIER_LANGUE + Configuration.getLanguageApplication() + Langue.EXTENSION_FICHIER_LANGUE);
                Langue.ressourcesLangue = new PropertyResourceBundle(fis);
            }
            catch (IOException e) {
                System.err.println("Loading default language...");
                Langue.ressourcesLangue = ResourceBundle.getBundle("com.gestionnairedeprofil.configuration.langueParDefaut", Locale.getDefault());
            }
        }
        return Langue.ressourcesLangue;
    }

}
