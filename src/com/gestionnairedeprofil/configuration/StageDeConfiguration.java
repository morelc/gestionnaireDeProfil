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

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Fenêtre de configuration de la langue de l'application.
 *
 * Window used to set app language
 *
 * @author MOREL Charles
 */
public class StageDeConfiguration extends Stage
{

    public static void lancerConfiguration(double i, Stage stageAppPrincipal)
    {
        if (!Configuration.testerProrpietesDisponibles()) {
            new StageDeConfiguration(i, stageAppPrincipal);
        }
        else {
            stageAppPrincipal.show();
        }
    }

    public StageDeConfiguration(double i, final Stage stageAppPrincipal)
    {
        // configuration des dépendances
        this.setTitle(Langue.getLangue().getString("ConfigWindow_title"));
        this.initModality(Modality.WINDOW_MODAL);
        this.initStyle(StageStyle.UTILITY);
        this.initOwner(stageAppPrincipal);
        this.setResizable(false);

        // Configuration des noeuds statiques
        Text texteSelection = new Text();
        texteSelection.setLayoutX(30 * i);
        texteSelection.setLayoutY(25 * i);
        texteSelection.setFont(new Font(15 * i));
        texteSelection.setFill(Color.web("#696969", 1.0));
        texteSelection.setTextAlignment(TextAlignment.CENTER);
        texteSelection.setText(Langue.getLangue().getString("ConfigWindow_textLanguageSelection"));

        Text texteRedemarrage = new Text();
        texteRedemarrage.setLayoutX(25 * i);
        texteRedemarrage.setLayoutY(115 * i);
        texteRedemarrage.setFont(new Font(10 * i));
        texteRedemarrage.setFill(Color.web("#696969", 1.0));
        texteRedemarrage.setTextAlignment(TextAlignment.CENTER);
        texteRedemarrage.setText(Langue.getLangue().getString("ConfigWindow_textReboot"));

        // Configuration des contrôles
        final ComboBox cbSelectionMachine = new ComboBox();
        cbSelectionMachine.setLayoutX(95 * i);
        cbSelectionMachine.setLayoutY(45 * i);
        cbSelectionMachine.setPrefSize(110 * i, 20 * i);
        cbSelectionMachine.setMaxSize(110 * i, 20 * i);
        cbSelectionMachine.setMinSize(110 * i, 20 * i);
        try {
            for (Object langueAAjouter : Langue.getToutsLesLanguages()) {
                cbSelectionMachine.getItems().add(langueAAjouter);
            }
        }
        catch (IOException ex) {
            cbSelectionMachine.getItems().add(new Langue("English", "en-US"));
        }
        cbSelectionMachine.setValue(cbSelectionMachine.getItems().get(0));

        Button btnOk = new Button("Ok");
        btnOk.setLayoutX(130 * i);
        btnOk.setLayoutY(80 * i);
        btnOk.setPrefSize(40 * i, 20 * i);
        btnOk.setMaxSize(40 * i, 20 * i);
        btnOk.setMinSize(40 * i, 20 * i);
        btnOk.setFont(new Font(7 * i));

        // Configuration du noeud racine Root et de Scene

        Group root = new Group();
        root.getChildren().add(texteSelection);
        root.getChildren().add(texteRedemarrage);
        root.getChildren().add(cbSelectionMachine);
        root.getChildren().add(btnOk);

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                Langue langueSelectionnee = (Langue) cbSelectionMachine.getValue();
                Configuration.ecrireConfig(langueSelectionnee.getConfigLangue());
                stageAppPrincipal.close();
            }
        });

        this.setScene(new Scene(root, 300 * i, 130 * i, Color.gray(0.85)));
        this.show();
    }


}
