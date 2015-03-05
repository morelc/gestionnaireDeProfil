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

import gestionnairedeprofil.donnees.structures.Machine;
import gestionnairedeprofil.donnees.structures.Profil;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
class ListeProfilsDisponiblesMachine extends TitledPane
{

    private ArrayList<PanneauProfilDisponible> profilsDisponibles;
    private final AnchorPane panneauDesProfilsDisponibles;
    private final double espacementEntreProfils;
    private final Stage stageDeLApplication;

    private final double dim;
    private final Machine machineLieeAuProfil;
    private final ListeProfilsAEnvoyer listeDesProfils;

    public ListeProfilsDisponiblesMachine(double i, Machine machineConcernee, Stage stagePrincipal, ListeProfilsAEnvoyer listeDesProfilsAEnvoyer)
    {
        // Configuration des dépendances
        this.setText(machineConcernee.getNom());
        this.setStyle("-fx-background-color: #E9E9E9; -fx-font-size:" + Double.toString(7 * i) + "; -fx-text-fill: black;");

        this.dim = i;
        this.machineLieeAuProfil = machineConcernee;
        this.listeDesProfils = listeDesProfilsAEnvoyer;

        this.stageDeLApplication = stagePrincipal;
        this.profilsDisponibles = new ArrayList();
        this.espacementEntreProfils = i * 17;
        this.panneauDesProfilsDisponibles = new AnchorPane();

        ScrollPane panneauAScrolling = new ScrollPane();
        panneauAScrolling.setContent(this.panneauDesProfilsDisponibles);
        this.setContent(panneauAScrolling);

        for (Profil profilAAjouter : machineConcernee.getProfils()) {
            ajouterProfil(profilAAjouter);
            //new PanneauProfilDisponible(i, this, listeDesProfilsAEnvoyer, profilAAjouter, machineConcernee);
        }
    }

    public final void ajouterProfil(Profil profilAAjouter)
    {
        new PanneauProfilDisponible(this.dim, this, this.listeDesProfils, profilAAjouter, this.machineLieeAuProfil);
    }

    public void ajouterProfilAEnvoyer(PanneauProfilDisponible panneauAAjouter)
    {
        panneauAAjouter.setLayoutX(0);
        panneauAAjouter.setLayoutY(this.getProfilsDisponibles().size() * this.espacementEntreProfils);
        panneauAAjouter.setOpacity(0);
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(150), new KeyValue(panneauAAjouter.opacityProperty(), 1)));
        this.panneauDesProfilsDisponibles.getChildren().add(panneauAAjouter);
        this.getProfilsDisponibles().add(panneauAAjouter);
        timeline.play();
    }

    public void enleverProfilAEnvoyer(final PanneauProfilDisponible panneauAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                ListeProfilsDisponiblesMachine.this.panneauDesProfilsDisponibles.getChildren().remove(panneauAEnlever);
                ListeProfilsDisponiblesMachine.this.getProfilsDisponibles().remove(panneauAEnlever);
                reorganiserListeProfils();
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(panneauAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    private void reorganiserListeProfils()
    {
        for (int i = 0; i < this.getProfilsDisponibles().size(); i++) {
            replacerElement(this.getProfilsDisponibles().get(i), i);
        }
    }

    private void replacerElement(PanneauProfilDisponible panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementYLogique = this.espacementEntreProfils * idEmplacementDansCollection;
        double emplacementYActuel = panneauAReplacer.getLayoutY();
        if (emplacementYLogique != emplacementYActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(panneauAReplacer.layoutYProperty(), emplacementYLogique)));
            timeline.play();
        }
    }

    /**
     * @return the stageDeLApplication
     */
    public Stage getStageDeLApplication()
    {
        return stageDeLApplication;
    }

    /**
     * @return the profilsDisponibles
     */
    public ArrayList<PanneauProfilDisponible> getProfilsDisponibles()
    {
        return profilsDisponibles;
    }
}
