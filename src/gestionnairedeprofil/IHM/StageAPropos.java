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
package gestionnairedeprofil.IHM;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MOREL Charles
 */
class StageAPropos extends Stage
{

    StageAPropos(double i, Stage stageParent)
    {
        // configuration des dépendances
        this.setTitle("À propos...");
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UTILITY);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques
        Text texteTitreLogiciel = new Text();
        texteTitreLogiciel.setX(30 * i);
        texteTitreLogiciel.setY(20 * i);
        texteTitreLogiciel.setFont(new Font(20 * i));
        texteTitreLogiciel.setFill(Color.web("#696969", 1.0));
        texteTitreLogiciel.setTextAlignment(TextAlignment.CENTER);
        texteTitreLogiciel.setText("Gestionnaire de profil V1.0");

        Text texteRealisePar = new Text();
        texteRealisePar.setX(60 * i);
        texteRealisePar.setY(45 * i);
        texteRealisePar.setFont(new Font(16 * i));
        texteRealisePar.setFill(Color.web("#696969", 1.0));
        texteRealisePar.setTextAlignment(TextAlignment.CENTER);
        texteRealisePar.setText("Application réalisée par...");

        ImageView iconeRealisation = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/Logo_Team.png")));
        iconeRealisation.setFitHeight(80 * i);
        iconeRealisation.setPreserveRatio(true);
        iconeRealisation.setLayoutX(30 * i);
        iconeRealisation.setLayoutY(70 * i);

        Text texteNoms = new Text();
        texteNoms.setX(100 * i);
        texteNoms.setY(80 * i);
        texteNoms.setFont(new Font(14 * i));
        texteNoms.setFill(Color.web("#696969", 1.0));
        texteNoms.setTextAlignment(TextAlignment.CENTER);
        texteNoms.setText("MOREL Charles"
                + "\nIHM & Envoi des données"
                + "\n\nTIS Fakri"
                + "\nBase de données SQLite");

        Text texteLicense = new Text();
        texteLicense.setX(15 * i);
        texteLicense.setY(180 * i);
        texteLicense.setFont(new Font(12 * i));
        texteLicense.setFill(Color.web("#696969", 1.0));
        texteLicense.setTextAlignment(TextAlignment.CENTER);
        texteLicense.setText("Ce programme est libre, vous pouvez le redistribuer"
                + "\net/ou le modifer selon les termes de la License"
                + "\nPublique Générale GNU (V2 ou ultérieure)"
                + "\n\nIl est distribué car potentiellement utile,"
                + "\nmais sans AUCUNE GARANTIE."
                + "\n\nPour plus de détails: www.gnu.org");

        Button btnOk = new Button("Ok", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(125 * i);
        btnOk.setLayoutY(310 * i);
        btnOk.setPrefSize(50 * i, 25 * i);
        btnOk.setMaxSize(50 * i, 25 * i);
        btnOk.setMinSize(50 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(texteTitreLogiciel);
        root.getChildren().add(texteRealisePar);
        root.getChildren().add(iconeRealisation);
        root.getChildren().add(texteNoms);
        root.getChildren().add(texteLicense);
        root.getChildren().add(btnOk);

        this.setScene(new Scene(root, 300 * i, 350 * i, Color.gray(0.85)));


        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageAPropos.this.close();
                try {
                    URI u = new URI("test.fr");
                    java.awt.Desktop.getDesktop().browse(u);
                }
                catch (Exception e) {
                    e.getMessage();
                }
            }
        });

        this.show();
    }

}
