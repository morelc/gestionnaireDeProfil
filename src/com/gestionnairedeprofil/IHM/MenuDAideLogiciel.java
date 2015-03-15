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
package com.gestionnairedeprofil.IHM;

import com.gestionnairedeprofil.configuration.Langue;
import com.gestionnairedeprofil.configuration.StageDeConfiguration;
import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Menu d'aide du logiciel.
 *
 * Application help menu
 *
 * @author MOREL Charles
 */
public class MenuDAideLogiciel extends Group
{

    public MenuDAideLogiciel(final double i, final Stage stageParent, final Application applicationParente)
    {

        // Configuration des contrôles
        final ContextMenu menuContextuel = new ContextMenu();
        Menu menuAide = new Menu(Langue.getLangue().getString("SoftwareHelpMenu_helpMenu"));
        menuContextuel.getItems().add(menuAide);
        MenuItem sousMenuAide1 = new MenuItem(Langue.getLangue().getString("SoftwareHelpMenu_subMenuHelp1"));
        menuAide.getItems().add(sousMenuAide1);
        MenuItem menuChangerLangue = new MenuItem(Langue.getLangue().getString("SoftwareHelpMenu_menuChangeLanguage"));
        menuContextuel.getItems().add(menuChangerLangue);
        MenuItem menuAPropos = new MenuItem(Langue.getLangue().getString("SoftwareHelpMenu_menuAbout"));
        menuContextuel.getItems().add(menuAPropos);

        // Configuration des noeuds statiques
        final Circle cerclePointDInterrogation = new Circle();
        cerclePointDInterrogation.setCenterX(0 * i);
        cerclePointDInterrogation.setCenterY(0 * i);
        cerclePointDInterrogation.setRadius(10 * i);
        cerclePointDInterrogation.setFill(Color.web("#696969", 1.0));
        this.getChildren().add(cerclePointDInterrogation);

        final Text textePointDInterrogation = new Text();
        textePointDInterrogation.setLayoutX(-3.5 * i);
        textePointDInterrogation.setLayoutY(6 * i);
        textePointDInterrogation.setFont(new Font(18 * i));
        textePointDInterrogation.setFill(Color.WHITE);
        textePointDInterrogation.setTextAlignment(TextAlignment.CENTER);
        textePointDInterrogation.setText("?");
        this.getChildren().add(textePointDInterrogation);


        cerclePointDInterrogation.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if (e.getButton() == MouseButton.PRIMARY) {
                    menuContextuel.show(cerclePointDInterrogation, e.getScreenX(), e.getScreenY());
                }
            }
        });

        textePointDInterrogation.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if (e.getButton() == MouseButton.PRIMARY) {
                    menuContextuel.show(textePointDInterrogation, e.getScreenX(), e.getScreenY());
                }
            }
        });

        menuChangerLangue.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageDeConfiguration(i, stageParent);
            }
        });

        menuAPropos.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageAPropos(i, stageParent);
            }
        });

        sousMenuAide1.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                stageParent.toBack();
                try {
                    Desktop.getDesktop().browse(new URI("http://www.google.com"));
                }
                catch (Exception ex) {
                    Logger.getLogger(MenuDAideLogiciel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
