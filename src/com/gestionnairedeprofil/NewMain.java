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
package com.gestionnairedeprofil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author MOREL Charles
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        List<File> files = Arrays.asList(File.listRoots());
        for (File f : files) {
            String s = f.getAbsolutePath().toString();
            //String s = FileSystemView.getFileSystemView().getSystemTypeDescription(f) + " " + FileSystemView.getFileSystemView().getSystemDisplayName(f);
            System.out.println("*" + s);
        }
    }

}
