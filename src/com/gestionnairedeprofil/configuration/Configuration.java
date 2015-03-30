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

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Classe représentant la configuration de l'application.
 *
 * Class who represents the application configuration
 *
 * @author MOREL Charles
 */
public class Configuration
{

    public final static String DOSSIER_DES_DEPENDANCES = "Dependencies";
    public final static String DOSSIER_DES_IMAGES_DE_PAD = "ControllerPictures";
    private final static String NOM_FICHIER_DE_CONFIGURATION = "config.properties";
    private final static String LANGUAGE_PAR_DEFAUT = "NOT_SET";

    private static Properties proprietesApplication = null;

    public static Boolean testerProrpietesDisponibles()
    {
        File fichierDeConf = new File(Configuration.DOSSIER_DES_DEPENDANCES + File.separator + Configuration.NOM_FICHIER_DE_CONFIGURATION);
        return fichierDeConf.exists();
    }

    public static void ecrireConfig(String languageUtilise)
    {
        try {
            DataOutputStream teteDeLecture = new DataOutputStream(new FileOutputStream(new File(Configuration.DOSSIER_DES_DEPENDANCES + File.separator + Configuration.NOM_FICHIER_DE_CONFIGURATION)));
            String fichierAEcrire = "app.lang=" + languageUtilise;
            for (int x = 0; x < fichierAEcrire.length(); x++) {
                teteDeLecture.write(fichierAEcrire.charAt(x));
            }
            teteDeLecture.close();
            Configuration.redemarrerApplication();
        }
        catch (IOException | URISyntaxException e) {
        }

    }

    private static void chargerProprietes()
    {
        Configuration.proprietesApplication = new Properties();
        try {
            Configuration.proprietesApplication.load(new FileInputStream(Configuration.DOSSIER_DES_DEPENDANCES + File.separator + Configuration.NOM_FICHIER_DE_CONFIGURATION));
        }
        catch (IOException e) {
            proprietesApplication.setProperty("app.lang", Configuration.LANGUAGE_PAR_DEFAUT);
            System.err.println("Properties file not found");
        }
    }

    public static String getLanguageApplication()
    {
        if (Configuration.proprietesApplication == null) {
            Configuration.chargerProprietes();
        }
        return Configuration.proprietesApplication.getProperty("app.lang", Configuration.LANGUAGE_PAR_DEFAUT);
    }

    /**
     * Restart application. Thanks to Veger
     *
     * @see
     * http://stackoverflow.com/questions/4159802/how-can-i-restart-a-java-application
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void redemarrerApplication() throws URISyntaxException, IOException
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        /* is it a jar file? */
        if (!currentJar.getName().endsWith(".jar")) {
            return;
        }

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }
}
