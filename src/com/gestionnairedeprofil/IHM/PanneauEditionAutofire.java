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

import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
public class PanneauEditionAutofire extends AnchorPane implements InterfaceEditionAssociation, InterfacePanneauTypeCombinaison
{

    public final static String MESSAGE_INVALIDITE = "au moins une touche doit être\nselectionnée et le laps de répétition\nsuperieur ou égal\nà 10 ms";
    private final ArrayList<PanneauToucheMachinePourCombinaison> combinaison;
    private final ArrayList<ToucheMachine> touchesDisponiblesAffichables;
    private final Button btnAjouterCmb;
    private final double dim;
    private final TextField textFieldTimer;


    public PanneauEditionAutofire(double i, ArrayList<ToucheMachine> touchesDisponibles, AssociationsDansProfil associationsDeBase)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setMinSize(301.5 * i, 248.5 * i);
        this.touchesDisponiblesAffichables = touchesDisponibles;
        this.dim = i;

        // Configuration des noeuds statiques
        Text texeTimer = new Text();
        texeTimer.setLayoutX(77 * i);
        texeTimer.setLayoutY(50 * i);
        texeTimer.setFont(new Font(15 * i));
        texeTimer.setFill(Color.web("#696969", 1.0));
        texeTimer.setTextAlignment(TextAlignment.CENTER);
        texeTimer.setText("Répéter toutes les :");
        this.getChildren().add(texeTimer);

        Text texeTimerBis = new Text();
        texeTimerBis.setLayoutX(160 * i);
        texeTimerBis.setLayoutY(75 * i);
        texeTimerBis.setFont(new Font(15 * i));
        texeTimerBis.setFill(Color.web("#696969", 1.0));
        texeTimerBis.setTextAlignment(TextAlignment.CENTER);
        texeTimerBis.setText("ms");
        this.getChildren().add(texeTimerBis);

        Text texteCombinaison = new Text();
        texteCombinaison.setLayoutX(85 * i);
        texteCombinaison.setLayoutY(105 * i);
        texteCombinaison.setFont(new Font(15 * i));
        texteCombinaison.setFill(Color.web("#696969", 1.0));
        texteCombinaison.setTextAlignment(TextAlignment.CENTER);
        texteCombinaison.setText("L'appui sur la\nou les touches:");
        this.getChildren().add(texteCombinaison);

        // Configuration des contrôles
        this.textFieldTimer = new TextField();
        this.textFieldTimer.setLayoutX(100 * i);
        this.textFieldTimer.setLayoutY(60 * i);
        this.textFieldTimer.setPrefSize(50 * i, 20 * i);
        this.textFieldTimer.setMaxSize(50 * i, 20 * i);
        this.textFieldTimer.setMinSize(50 * i, 20 * i);
        this.textFieldTimer.setStyle("-fx-font-size: " + Double.toString(9 * i));
        this.textFieldTimer.setText(Integer.toString(associationsDeBase.get(0).getTimer()));
        this.getChildren().add(this.textFieldTimer);

        this.combinaison = new ArrayList();

        this.btnAjouterCmb = new Button("Ajouter...", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/add.png"))));
        this.btnAjouterCmb.setLayoutX(90 * i);
        this.btnAjouterCmb.setLayoutY(164 * i);
        this.btnAjouterCmb.setMaxSize(105 * i, 20 * i);
        this.btnAjouterCmb.setMinSize(105 * i, 20 * i);
        this.btnAjouterCmb.setPrefSize(105 * i, 20 * i);
        Tooltip infobulleBtnAjouterCmb = new Tooltip();
        infobulleBtnAjouterCmb.setText("Ajouter une nouvelle touche à l'autofie");
        this.btnAjouterCmb.setTooltip(infobulleBtnAjouterCmb);
        this.getChildren().add(this.btnAjouterCmb);

        if (associationsDeBase.getAssocType() == AssociationsDansProfil.TYPE_VIDE) {
            ajouterUnPanneauDeCombinaison(new ToucheMachine());
        }
        else {
            for (ToucheMachine toucheAAjouter : associationsDeBase.get(0).getTouches()) {
                ajouterUnPanneauDeCombinaison(toucheAAjouter);
            }
        }


        this.btnAjouterCmb.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                ajouterUnPanneauDeCombinaison(new ToucheMachine());
            }
        });

    }

    public final void ajouterUnPanneauDeCombinaison(ToucheMachine toucheAAjouter)
    {
        PanneauToucheMachinePourCombinaison nouvelleCombinaisonTocuhe = new PanneauToucheMachinePourCombinaison(dim, this, toucheAAjouter);
        nouvelleCombinaisonTocuhe.setLayoutX(90 * dim);
        nouvelleCombinaisonTocuhe.setLayoutY(141 * dim + (23 * dim * this.combinaison.size()));
        nouvelleCombinaisonTocuhe.setOpacity(0);
        combinaison.add(nouvelleCombinaisonTocuhe);
        reorganiserListeAssociations();
        Timeline timelinePanneauAAjoyter = new Timeline();
        timelinePanneauAAjoyter.setAutoReverse(false);
        timelinePanneauAAjoyter.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(nouvelleCombinaisonTocuhe.opacityProperty(), 1)));
        timelinePanneauAAjoyter.play();
        this.getChildren().add(nouvelleCombinaisonTocuhe);
    }

    @Override
    public void enleverUneAssociation(final PanneauToucheMachinePourCombinaison assocAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                PanneauEditionAutofire.this.getChildren().remove(assocAEnlever);
                PanneauEditionAutofire.this.combinaison.remove(assocAEnlever);
                reorganiserListeAssociations();
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(assocAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    private void reorganiserListeAssociations()
    {
        for (int i = 0; i < this.combinaison.size(); i++) {
            replacerElement(this.combinaison.get(i), i);
        }
        Timeline timelineBtnAjouter = new Timeline();
        timelineBtnAjouter.setAutoReverse(false);
        timelineBtnAjouter.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(this.btnAjouterCmb.layoutYProperty(), 141 * dim + (23 * dim * this.combinaison.size()))));
        timelineBtnAjouter.play();
        if (this.combinaison.size() >= 4) {
            this.setMinWidth(293.5 * this.dim);
            this.setMaxWidth(293.5 * this.dim);
            this.setPrefWidth(293.5 * this.dim);
            Timeline timelineForcerHauteur = new Timeline();
            timelineForcerHauteur.setAutoReverse(false);
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(180), new KeyValue(this.minHeightProperty(), 171 * this.dim + (23 * this.dim * this.combinaison.size()))));
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(180), new KeyValue(this.minHeightProperty(), 171 * this.dim + (23 * this.dim * this.combinaison.size()))));
            timelineForcerHauteur.getKeyFrames().add(new KeyFrame(Duration.millis(180), new KeyValue(this.maxHeightProperty(), 171 * this.dim + (23 * this.dim * this.combinaison.size()))));
            timelineForcerHauteur.play();
        }
        else {
            this.setMinSize(301.5 * this.dim, 248.5 * this.dim);
            this.setPrefSize(301.5 * this.dim, 248.5 * this.dim);
            this.setMaxSize(301.5 * this.dim, 248.5 * this.dim);

        }
    }

    private void replacerElement(PanneauToucheMachinePourCombinaison panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementYLogique = 141 * dim + (23 * dim * idEmplacementDansCollection);
        double emplacementYActuel = panneauAReplacer.getLayoutY();
        if (emplacementYLogique != emplacementYActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(panneauAReplacer.layoutYProperty(), emplacementYLogique)));
            timeline.play();
        }
        if (!uneAssociationPeutEtreEnlevee()) {
            panneauAReplacer.desactiverBtnSupprimer();
        }
        else {
            panneauAReplacer.activerBtnSupprimer();
        }
    }

    private boolean uneAssociationPeutEtreEnlevee()
    {
        return (this.combinaison.size() > 1);
    }

    /**
     * @return the touchesDisponiblesAffichables
     */
    @Override
    public ArrayList<ToucheMachine> getTouchesDisponiblesAffichables()
    {
        return touchesDisponiblesAffichables;
    }

    @Override
    public AssociationsDansProfil getAssociations()
    {
        AssociationsDansProfil associationsARetourner = new AssociationsDansProfil();
        Association associationARajouter = new Association(Association.ID_NON_ENREGISTREE_DANS_BD, true, Integer.parseInt(this.textFieldTimer.getText()));
        for (PanneauToucheMachinePourCombinaison toucheSelectionnee : this.combinaison) {
            if (!associationARajouter.getTouches().contains(toucheSelectionnee.getToucheCourrante()) && toucheSelectionnee.getToucheCourrante().getId() != ToucheMachine.ID_TOUCHE_NON_ASSOCIEE) {
                associationARajouter.ajouterTouche(toucheSelectionnee.getToucheCourrante());
            }
        }
        associationsARetourner.add(associationARajouter);
        return associationsARetourner;
    }

    @Override
    public boolean associationValide()
    {
        ArrayList idTouchesAppuyees = new ArrayList();
        for (PanneauToucheMachinePourCombinaison toucheSelectionnee : this.combinaison) {
            if (!idTouchesAppuyees.contains(toucheSelectionnee.getToucheCourrante().getId()) && toucheSelectionnee.getToucheCourrante().getId() != ToucheMachine.ID_TOUCHE_NON_ASSOCIEE) {
                idTouchesAppuyees.add(toucheSelectionnee.getToucheCourrante().getId());
            }
        }
        if (idTouchesAppuyees.size() < 1
                || this.textFieldTimer.getText().length() == 0) {
            return false;
        }
        try {
            if (Integer.parseInt(this.textFieldTimer.getText()) < 10) {
                return false;
            }
        }
        catch (NumberFormatException erreur) {
            return false;
        }
        return true;
    }


    @Override
    public String getMessageDInvalidite()
    {
        return PanneauEditionAutofire.MESSAGE_INVALIDITE;
    }

}
