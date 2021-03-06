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

/**
 * Fenêtre permettant l'affichage d'un message d'erreur.
 *
 * Window to show error
 *
 * @author MOREL Charles
 */
public class StageMessageErreur extends Stage
{

    public StageMessageErreur(double i, Stage stageParent, String messageComplementaireErreur)
    {
        // configuration des dépendances
        this.setTitle(Langue.getLangue().getString("ErrorWindow_title"));
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png")));
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques

        Text texteErreur = new Text();
        texteErreur.setLayoutX(125 * i);
        texteErreur.setLayoutY(30 * i);
        texteErreur.setFont(new Font(15 * i));
        texteErreur.setFill(Color.web("#696969", 1.0));
        texteErreur.setTextAlignment(TextAlignment.CENTER);
        texteErreur.setText(Langue.getLangue().getString("ErrorWindow_textError") + "\n"
                + messageComplementaireErreur);

        ImageView fondErreur = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/info.png")));
        fondErreur.setFitHeight(150 * i);
        fondErreur.setPreserveRatio(true);
        fondErreur.setLayoutX(-20 * i);
        fondErreur.setLayoutY(25 * i);

        // Configuration des contrôles

        Button btnOk = new Button(Langue.getLangue().getString("ErrorWindow_btnOk"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(325 * i);
        btnOk.setLayoutY(100 * i);
        btnOk.setPrefSize(50 * i, 25 * i);
        btnOk.setMaxSize(50 * i, 25 * i);
        btnOk.setMinSize(50 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));


        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fondErreur);
        root.getChildren().add(texteErreur);
        root.getChildren().add(btnOk);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageMessageErreur.this.close();
            }
        });

        this.show();
    }

}
