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
package com.gestionnairedeprofil.IHM;

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
 *
 * @author MOREL Charles
 */
public class StageConfirmationAnnulationEditionProfil extends Stage
{

    public StageConfirmationAnnulationEditionProfil(final StageEditionProfil profilConcerne)
    {
        // configuration des dépendances
        this.setTitle("Annuler les changements");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png")));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(profilConcerne);
        this.setResizable(false);
        double i = profilConcerne.getDim();

        // Configuration des noeuds statiques

        Text texteDanger = new Text();
        texteDanger.setLayoutX(100 * i);
        texteDanger.setLayoutY(25 * i);
        texteDanger.setFont(new Font(15 * i));
        texteDanger.setFill(Color.web("#696969", 1.0));
        texteDanger.setTextAlignment(TextAlignment.CENTER);
        texteDanger.setText("Attention, vous avez effectué des"
                + "\nchangements sur ce profil."
                + "\nTout changement non sauvgardé est perdu."
                + "\nSouhaitez-vous vraiment quitter l'édition ?");

        ImageView fond_Danger = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/warning.png")));
        fond_Danger.setFitHeight(185 * i);
        fond_Danger.setPreserveRatio(true);
        fond_Danger.setLayoutX(-15 * i);
        fond_Danger.setLayoutY(-10 * i);

        // Configuration des contrôles

        Button btnOk = new Button("Oui", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(170 * i);
        btnOk.setLayoutY(100 * i);
        btnOk.setPrefSize(50 * i, 25 * i);
        btnOk.setMaxSize(50 * i, 25 * i);
        btnOk.setMinSize(50 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));

        Button btnNon = new Button("Non", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnNon.setLayoutX(250 * i);
        btnNon.setLayoutY(100 * i);
        btnNon.setPrefSize(50 * i, 25 * i);
        btnNon.setMaxSize(50 * i, 25 * i);
        btnNon.setMinSize(50 * i, 25 * i);
        btnNon.setFont(new Font(7 * i));


        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fond_Danger);
        root.getChildren().add(texteDanger);
        root.getChildren().add(btnOk);
        root.getChildren().add(btnNon);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                profilConcerne.close();
            }
        });

        btnNon.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageConfirmationAnnulationEditionProfil.this.close();
            }
        });

        this.show();
    }

}
