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

import com.gestionnairedeprofil.donnees.structures.Machine;
import com.gestionnairedeprofil.donnees.structures.Profil;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
public class PanneauProfilDisponible extends Pane
{

    private final ListeProfilsDisponiblesMachine listeDesProfilsDeLaMachine;
    private final Profil profilPanneau;
    private Label nomProfilAAfficher;
    private final Button btnAjouterALaListeDEnvoi;

    public PanneauProfilDisponible(final double dim, final ListeProfilsDisponiblesMachine listeOuAjouterLeProfil, final ListeProfilsAEnvoyer listeDesProfilsAEnvoyer, final Profil profilConcernee, final Machine machineConcernee)
    {
        this.listeDesProfilsDeLaMachine = listeOuAjouterLeProfil;
        this.profilPanneau = profilConcernee;

        this.nomProfilAAfficher = new Label(profilConcernee.getNom());
        this.nomProfilAAfficher.setLayoutX(5 * dim);
        this.nomProfilAAfficher.setLayoutY(3 * dim);
        this.nomProfilAAfficher.setPrefWidth(105 * dim);
        this.nomProfilAAfficher.setMinWidth(105 * dim);
        this.nomProfilAAfficher.setMaxWidth(105 * dim);
        this.nomProfilAAfficher.setFont(new Font(7 * dim));
        this.getChildren().add(this.nomProfilAAfficher);

        final Button btnEditer = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/edit.png"))));
        btnEditer.setLayoutX(109 * dim);
        btnEditer.setLayoutY(1 * dim);
        btnEditer.setMaxSize(25 * dim, 15 * dim);
        btnEditer.setMinSize(25 * dim, 15 * dim);
        btnEditer.setPrefSize(25 * dim, 15 * dim);
        btnEditer.setOpacity(0);
        Tooltip infobulleBtnEditer = new Tooltip();
        infobulleBtnEditer.setText("Éditer le profil");
        btnEditer.setTooltip(infobulleBtnEditer);
        this.getChildren().add(btnEditer);

        final Button btnSupprimer = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png"))));
        btnSupprimer.setLayoutX(137 * dim);
        btnSupprimer.setLayoutY(1 * dim);
        btnSupprimer.setMaxSize(25 * dim, 15 * dim);
        btnSupprimer.setMinSize(25 * dim, 15 * dim);
        btnSupprimer.setPrefSize(25 * dim, 15 * dim);
        btnSupprimer.setOpacity(0);
        Tooltip infobulleBtnSupprimer = new Tooltip();
        infobulleBtnSupprimer.setText("Supprimer définitivement le profil du PC");
        btnSupprimer.setTooltip(infobulleBtnSupprimer);
        this.getChildren().add(btnSupprimer);

        this.btnAjouterALaListeDEnvoi = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/right.png"))));
        this.btnAjouterALaListeDEnvoi.setLayoutX(165 * dim);
        this.btnAjouterALaListeDEnvoi.setLayoutY(1 * dim);
        this.btnAjouterALaListeDEnvoi.setMaxSize(25 * dim, 15 * dim);
        this.btnAjouterALaListeDEnvoi.setMinSize(25 * dim, 15 * dim);
        this.btnAjouterALaListeDEnvoi.setPrefSize(25 * dim, 15 * dim);
        this.btnAjouterALaListeDEnvoi.setOpacity(0);
        Tooltip infobulleBtnAjouterALaListeDEnvoi = new Tooltip();
        infobulleBtnAjouterALaListeDEnvoi.setText("Ajouter le profil à la liste d'envoi");
        this.btnAjouterALaListeDEnvoi.setTooltip(infobulleBtnAjouterALaListeDEnvoi);
        this.getChildren().add(this.btnAjouterALaListeDEnvoi);

        this.setOnMouseEntered(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent t)
            {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(false);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(100), new KeyValue(btnEditer.opacityProperty(), 1)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnSupprimer.opacityProperty(), 1)),
                        new KeyFrame(Duration.millis(100), new KeyValue(PanneauProfilDisponible.this.btnAjouterALaListeDEnvoi.opacityProperty(), 1))
                );
                timeline.play();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent t)
            {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(false);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(100), new KeyValue(btnEditer.opacityProperty(), 0)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnSupprimer.opacityProperty(), 0)),
                        new KeyFrame(Duration.millis(100), new KeyValue(PanneauProfilDisponible.this.btnAjouterALaListeDEnvoi.opacityProperty(), 0))
                );
                timeline.play();
            }
        });
        btnEditer.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageEditionProfil(dim, PanneauProfilDisponible.this, listeOuAjouterLeProfil.getStageDeLApplication(), profilConcernee, machineConcernee);
            }
        });

        btnSupprimer.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageConfirmationSuppression(dim, PanneauProfilDisponible.this, listeOuAjouterLeProfil.getStageDeLApplication(), profilConcernee.getNom(), listeOuAjouterLeProfil.getText());
            }
        });
        this.btnAjouterALaListeDEnvoi.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new PanneauProfilAEnvoyer(dim, listeDesProfilsAEnvoyer, PanneauProfilDisponible.this.nomProfilAAfficher.textProperty(), profilConcernee);
                PanneauProfilDisponible.this.btnAjouterALaListeDEnvoi.setDisable(true);
            }
        });

        listeOuAjouterLeProfil.ajouterProfilAEnvoyer(this);
    }

    public void supprimerProfil()
    {
        this.listeDesProfilsDeLaMachine.enleverProfilAEnvoyer(this);
        this.nomProfilAAfficher.setText("          ");
        System.err.println("ATTENTION: L'ACTION N'EST PAS ENCORE DEFINIE!");
    }

    public void rafraichirNomProfil()
    {
        this.nomProfilAAfficher.setText(this.profilPanneau.getNom());
    }

    public void definirEnvoyable()
    {
        this.btnAjouterALaListeDEnvoi.setDisable(false);
    }

    /**
     * @return the profilPanneau
     */
    public Profil getProfil()
    {
        return this.profilPanneau;
    }
}
