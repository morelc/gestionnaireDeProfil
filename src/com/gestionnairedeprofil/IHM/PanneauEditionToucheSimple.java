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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Panneau d'édition d'association touche simple (utilisé dans la fenêtre
 * d'édition d'associations). Pane used for an simple association (used in the
 * association edition window)
 *
 * @author MOREL Charles
 */
public class PanneauEditionToucheSimple extends AnchorPane implements InterfaceEditionAssociation
{

    public final static String MESSAGE_INVALIDITE = " au moins une touche doit être\nselectionnée dans la liste!";
    ComboBox cbTocuheSimpleAppuyee;

    public PanneauEditionToucheSimple(double i, ArrayList<ToucheMachine> touchesDisponibles, AssociationsDansProfil associationsDeBase)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setPrefSize(301.5 * i, 248.5 * i);
        this.setMinSize(301.5 * i, 248.5 * i);
        this.setMaxSize(301.5 * i, 248.5 * i);

        // Configuration des noeuds statiques
        Text texteAppuiSimple = new Text();
        texteAppuiSimple.setLayoutX(105 * i);
        texteAppuiSimple.setLayoutY(75 * i);
        texteAppuiSimple.setFont(new Font(15 * i));
        texteAppuiSimple.setFill(Color.web("#696969", 1.0));
        texteAppuiSimple.setTextAlignment(TextAlignment.CENTER);
        texteAppuiSimple.setText("Appui simple\nsur la touche:");
        this.getChildren().add(texteAppuiSimple);

        // Configuration des contrôles
        this.cbTocuheSimpleAppuyee = new ComboBox();
        this.cbTocuheSimpleAppuyee.setLayoutX(110 * i);
        this.cbTocuheSimpleAppuyee.setLayoutY(110 * i);
        this.cbTocuheSimpleAppuyee.setPrefSize(80 * i, 20 * i);
        this.cbTocuheSimpleAppuyee.setMaxSize(80 * i, 20 * i);
        this.cbTocuheSimpleAppuyee.setMinSize(80 * i, 20 * i);
        for (ToucheMachine toucheAAfficher : touchesDisponibles) {
            this.cbTocuheSimpleAppuyee.getItems().add(toucheAAfficher);
        }
        this.cbTocuheSimpleAppuyee.setValue(associationsDeBase.get(0).getTouches().get(0));
        this.getChildren().add(this.cbTocuheSimpleAppuyee);
    }

    @Override
    public AssociationsDansProfil getAssociations()
    {
        Association assocAAjouterDansLeRetour = new Association(Association.ID_NON_ENREGISTREE_DANS_BD, false, 0);
        AssociationsDansProfil associationsARetourner = new AssociationsDansProfil();
        assocAAjouterDansLeRetour.ajouterTouche((ToucheMachine) this.cbTocuheSimpleAppuyee.getValue());
        associationsARetourner.add(assocAAjouterDansLeRetour);
        return associationsARetourner;
    }

    @Override
    public boolean associationValide()
    {
        ToucheMachine valeurDeLaTocue = (ToucheMachine) this.cbTocuheSimpleAppuyee.getValue();
        return valeurDeLaTocue.getId() != ToucheMachine.ID_TOUCHE_NON_ASSOCIEE;
    }

    @Override
    public String getMessageDInvalidite()
    {
        return PanneauEditionToucheSimple.MESSAGE_INVALIDITE;
    }

}
