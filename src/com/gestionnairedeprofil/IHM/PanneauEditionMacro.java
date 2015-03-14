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

import com.gestionnairedeprofil.configuration.Langue;
import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Panneau d'édition de macro (utilisé dans la fenêtre d'édition
 * d'associations).
 *
 * Pane used for an macro association (used in the association edition window)
 *
 * @author MOREL Charles
 */
public class PanneauEditionMacro extends AnchorPane implements InterfaceEditionAssociation
{

    private final ArrayList<PanneauCombinaisonPourMacro> combinaisons;
    private int panneauCourrant;
    private double dim;
    private ArrayList<ToucheMachine> touchesAffichables;
    private final Text texteEditionMacro;

    public PanneauEditionMacro(double i, ArrayList<ToucheMachine> touchesDisponibles, AssociationsDansProfil associationsDeBase)
    {
        // Configuration des dépendances
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setMinSize(301.5 * i, 248.5 * i);
        this.setMaxSize(301.5 * i, 248.5 * i);
        this.setPrefSize(301.5 * i, 248.5 * i);
        this.dim = i;
        this.touchesAffichables = touchesDisponibles;
        this.panneauCourrant = 0;
        Collections.sort(associationsDeBase, new Comparator<Association>()
        {
            @Override
            public int compare(Association o1, Association o2)
            {
                return o1.getTimer() - o2.getTimer();
            }

        });

        // Configuration des noeuds dynamiques
        this.texteEditionMacro = new Text();
        this.texteEditionMacro.setLayoutX(45 * i);
        this.texteEditionMacro.setLayoutY(30 * i);
        this.texteEditionMacro.setFont(new Font(15 * i));
        this.texteEditionMacro.setFill(Color.web("#696969", 1.0));
        this.texteEditionMacro.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(this.texteEditionMacro);

        this.combinaisons = new ArrayList();

        if (associationsDeBase.getAssocType() == AssociationsDansProfil.TYPE_VIDE) {
            ajouterUnPanneauDeCombinaison(new Association());
        }
        else {
            for (Association AssociationAAjouter : associationsDeBase) {
                ajouterUnPanneauDeCombinaison(AssociationAAjouter);
            }
        }
    }

    public final void ajouterUnPanneauDeCombinaison(Association associationAAjouter)
    {
        PanneauCombinaisonPourMacro nouvelleCombinaisonTocuhe = new PanneauCombinaisonPourMacro(this.dim, this, this.touchesAffichables, associationAAjouter);
        nouvelleCombinaisonTocuhe.setLayoutY(35 * this.dim);
        if (this.combinaisons.isEmpty()) {
            nouvelleCombinaisonTocuhe.setLayoutX(0);
        }
        else {
            nouvelleCombinaisonTocuhe.setLayoutX(300 * this.dim);
        }
        combinaisons.add(nouvelleCombinaisonTocuhe);
        this.getChildren().add(nouvelleCombinaisonTocuhe);
        verifierPossibiliteSuppressionPanneau();
        reorganiserListeAssociations();
    }

    private void reorganiserListeAssociations()
    {
        for (int i = 0; i < this.combinaisons.size(); i++) {
            replacerElement(this.combinaisons.get(i), i);
        }
        redimensionnerPourNAssociations(this.combinaisons.get(panneauCourrant).getTailleDeLaCombinaison());
        this.texteEditionMacro.setText(Langue.getLangue().getString("PanneauEditionMacro_texteEditionMacro") + " (" + (this.panneauCourrant + 1) + "/" + this.combinaisons.size() + ")");
    }

    private void replacerElement(PanneauCombinaisonPourMacro panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementXLogique = 0;
        if (idEmplacementDansCollection < this.panneauCourrant) {
            emplacementXLogique = -300 * this.dim;
        }
        if (idEmplacementDansCollection > this.panneauCourrant) {
            emplacementXLogique = 300 * this.dim;
        }
        double emplacementXActuel = panneauAReplacer.getLayoutX();
        if (emplacementXLogique != emplacementXActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(400), new KeyValue(panneauAReplacer.layoutXProperty(), emplacementXLogique)));
            timeline.play();
        }
        if (idEmplacementDansCollection == 0) {
            panneauAReplacer.definirPremiereCombinaison(true);
        }
        else {
            panneauAReplacer.definirPremiereCombinaison(false);
        }
        if (idEmplacementDansCollection == this.combinaisons.size() - 1) {
            panneauAReplacer.definirDerniereCombinaison(true);
        }
        else {
            panneauAReplacer.definirDerniereCombinaison(false);
        }
    }

    public void sePlacerAuPremierElement()
    {
        this.panneauCourrant = 0;
        reorganiserListeAssociations();
    }

    public void sePlacerALElementPrecedent()
    {
        if (this.panneauCourrant != 0) {
            this.panneauCourrant--;
        }
        reorganiserListeAssociations();
    }

    public void sePlacerALElementSuivant()
    {
        if (this.panneauCourrant != this.combinaisons.size() - 1) {
            this.panneauCourrant++;
        }
        reorganiserListeAssociations();
    }

    public void sePlacerAuDernierElement()
    {
        this.panneauCourrant = this.combinaisons.size() - 1;
        reorganiserListeAssociations();
    }

    public void enleverUnElement(final PanneauCombinaisonPourMacro panneauAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                PanneauEditionMacro.this.getChildren().remove(panneauAEnlever);
                PanneauEditionMacro.this.combinaisons.remove(panneauAEnlever);
                verifierPossibiliteSuppressionPanneau();
                if (PanneauEditionMacro.this.panneauCourrant != 0) {
                    PanneauEditionMacro.this.panneauCourrant--;
                }
                reorganiserListeAssociations();
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(panneauAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    private void verifierPossibiliteSuppressionPanneau()
    {
        this.combinaisons.get(0).desactiverSuppression(this.combinaisons.size() <= 1);
    }

    public void redimensionnerPourNAssociations(int nbAssociationsDansPanneauFils)
    {
        if (nbAssociationsDansPanneauFils >= 3) {
            this.setMinWidth(293.5 * this.dim);
            this.setMaxWidth(293.5 * this.dim);
            this.setPrefWidth(293.5 * this.dim);
            Timeline timelineForcerHauteur = new Timeline();
            timelineForcerHauteur.setAutoReverse(false);
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(170), new KeyValue(this.minHeightProperty(), 215 * this.dim + (23 * this.dim * nbAssociationsDansPanneauFils))));
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(170), new KeyValue(this.minHeightProperty(), 215 * this.dim + (23 * this.dim * nbAssociationsDansPanneauFils))));
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(170), new KeyValue(this.maxHeightProperty(), 215 * this.dim + (23 * this.dim * nbAssociationsDansPanneauFils))));
            timelineForcerHauteur.play();
        }
        else {
            this.setMinSize(301.5 * this.dim, 248.5 * this.dim);
            this.setPrefSize(301.5 * this.dim, 248.5 * this.dim);
            this.setMaxSize(301.5 * this.dim, 248.5 * this.dim);

        }
    }

    @Override
    public AssociationsDansProfil getAssociations()
    {
        AssociationsDansProfil associationsARetourner = new AssociationsDansProfil();
        for (PanneauCombinaisonPourMacro assocATraiter : this.combinaisons) {
            associationsARetourner.add(assocATraiter.getAssociation());
        }
        Collections.sort(associationsARetourner, new Comparator<Association>()
        {
            @Override
            public int compare(Association o1, Association o2)
            {
                return o1.getTimer() - o2.getTimer();
            }

        });
        return associationsARetourner;
    }

    @Override
    public boolean associationValide()
    {
        ArrayList<String> instants = new ArrayList();
        for (PanneauCombinaisonPourMacro assocATester : this.combinaisons) {
            if (!assocATester.associationValide()) {
                return false;
            }
            if (instants.contains(assocATester.getNumInstant())) {
                return false;
            }
            else {
                instants.add(assocATester.getNumInstant());
            }
        }
        return true;
    }

    @Override
    public String getMessageDInvalidite()
    {
        ArrayList<String> instants = new ArrayList();
        for (int x = 0; x < this.combinaisons.size(); x++) {
            PanneauCombinaisonPourMacro assocATester = this.combinaisons.get(x);
            if (!assocATester.associationValide()) {
                return Langue.getLangue().getString("PanneauEditionMacro_messageDInvalidite1") + " " + (x + 1);
            }
            if (instants.contains(assocATester.getNumInstant())) {
                return Langue.getLangue().getString("PanneauEditionMacro_messageDInvalidite2A") + " " + (x + 1) + Langue.getLangue().getString("PanneauEditionMacro_messageDInvalidite2B") + " " + (instants.indexOf(assocATester.getNumInstant()) + 1);
            }
            else {
                instants.add(assocATester.getNumInstant());
            }
        }
        return Langue.getLangue().getString("PanneauEditionMacro_messageDInvalidite3");
    }

}
