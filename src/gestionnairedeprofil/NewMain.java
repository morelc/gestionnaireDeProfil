/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil;

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
