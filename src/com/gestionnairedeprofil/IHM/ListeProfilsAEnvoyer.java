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

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Panneau pour la liste des profils à envoyer. Pane used for the profils to
 * send
 *
 * @author MOREL Charles
 */
public class ListeProfilsAEnvoyer extends ScrollPane
{

    private final AnchorPane panneauDesProfilsAEncoyer;
    private final ArrayList<PanneauProfilAEnvoyer> profilsAEnvoyer;
    private final double espacementEntreProfils;
    private final Accordion listeDesProfilsDispo;

    public ListeProfilsAEnvoyer(double i, Accordion listeDesProfilsDisponibles)
    {
        this.listeDesProfilsDispo = listeDesProfilsDisponibles;
        this.profilsAEnvoyer = new ArrayList();
        this.espacementEntreProfils = i * 17;
        this.panneauDesProfilsAEncoyer = new AnchorPane();
        this.setContent(this.panneauDesProfilsAEncoyer);
        this.setPrefWidth(200 * i);
        this.setMinWidth(200 * i);
        this.setMaxWidth(200 * i);
        this.setStyle("-fx-background-color: #F4F4F4;"
                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 1, 0.0 , 0 , 1 );");
    }

    public void ajouterProfilAEnvoyer(PanneauProfilAEnvoyer panneauAAjouter)
    {
        panneauAAjouter.setLayoutX(0);
        panneauAAjouter.setLayoutY(this.profilsAEnvoyer.size() * this.espacementEntreProfils);
        panneauAAjouter.setOpacity(0);
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(150), new KeyValue(panneauAAjouter.opacityProperty(), 1)));
        this.panneauDesProfilsAEncoyer.getChildren().add(panneauAAjouter);
        this.profilsAEnvoyer.add(panneauAAjouter);
        timeline.play();
        reorganiserListeProfils();
    }

    public void enleverProfilAEnvoyer(final PanneauProfilAEnvoyer panneauAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                ListeProfilsAEnvoyer.this.panneauDesProfilsAEncoyer.getChildren().remove(panneauAEnlever);
                ListeProfilsAEnvoyer.this.profilsAEnvoyer.remove(panneauAEnlever);
                reorganiserListeProfils();
                for (Node machineDisponibleATraiter : ListeProfilsAEnvoyer.this.listeDesProfilsDispo.getPanes()) {
                    ListeProfilsDisponiblesMachine panneauAtraiter = (ListeProfilsDisponiblesMachine) machineDisponibleATraiter;
                    for (PanneauProfilDisponible panneauProfilAtraiter : panneauAtraiter.getProfilsDisponibles()) {
                        if (panneauProfilAtraiter.getProfil() == panneauAEnlever.getProfil()) {
                            panneauProfilAtraiter.definirEnvoyable();
                        }
                    }
                }
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(panneauAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    public void leverUnProfilDansLaListe(PanneauProfilAEnvoyer paneauALever)
    {
        int numLignePanneau = this.profilsAEnvoyer.indexOf(paneauALever);
        if (numLignePanneau != 0) {
            PanneauProfilAEnvoyer panneauDuDessus = this.profilsAEnvoyer.get(numLignePanneau - 1);
            this.profilsAEnvoyer.set(numLignePanneau, panneauDuDessus);
            this.profilsAEnvoyer.set(numLignePanneau - 1, paneauALever);
            reorganiserListeProfils();
        }
    }

    public void dessendreUnProfilDansLaListe(PanneauProfilAEnvoyer paneauADessendre)
    {
        int numLignePanneau = this.profilsAEnvoyer.indexOf(paneauADessendre);
        if (numLignePanneau != this.profilsAEnvoyer.size() - 1) {
            PanneauProfilAEnvoyer panneauDuDessous = this.profilsAEnvoyer.get(numLignePanneau + 1);
            this.profilsAEnvoyer.set(numLignePanneau, panneauDuDessous);
            this.profilsAEnvoyer.set(numLignePanneau + 1, paneauADessendre);
            reorganiserListeProfils();
        }
    }

    private void reorganiserListeProfils()
    {
        for (int i = 0; i < this.profilsAEnvoyer.size(); i++) {
            replacerElement(this.profilsAEnvoyer.get(i), i);
        }
    }

    private void replacerElement(PanneauProfilAEnvoyer panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementYLogique = this.espacementEntreProfils * idEmplacementDansCollection;
        double emplacementYActuel = panneauAReplacer.getLayoutY();
        if (emplacementYLogique != emplacementYActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(panneauAReplacer.layoutYProperty(), emplacementYLogique)));
            timeline.play();
        }
        if (idEmplacementDansCollection == 0) {
            panneauAReplacer.getBtnLever().setDisable(true);
        }
        else {
            panneauAReplacer.getBtnLever().setDisable(false);
        }
        if (idEmplacementDansCollection == this.profilsAEnvoyer.size() - 1) {
            panneauAReplacer.getBtnDessendre().setDisable(true);
        }
        else {
            panneauAReplacer.getBtnDessendre().setDisable(false);
        }
    }

    public ArrayList obtenirListeDesProfilsAEnvoyer()
    {
        ArrayList listeARetourner = new ArrayList();
        for (PanneauProfilAEnvoyer panneauCourrant : this.profilsAEnvoyer) {
            listeARetourner.add(panneauCourrant.getProfil());
        }
        return listeARetourner;
    }

}
