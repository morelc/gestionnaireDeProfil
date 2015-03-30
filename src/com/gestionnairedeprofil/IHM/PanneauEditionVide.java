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
import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Panneau d'édition d'association vide (utilisé dans la fenêtre d'édition
 * d'associations).
 *
 * Pane used for an empty association (used in the association edition window)
 *
 * @author MOREL Charles
 */
public class PanneauEditionVide extends AnchorPane implements InterfaceEditionAssociation
{

    public PanneauEditionVide(double i)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setPrefSize(301.5 * i, 248.5 * i);
        this.setMinSize(301.5 * i, 248.5 * i);
        this.setMaxSize(301.5 * i, 248.5 * i);

        // Configuration des noeuds statiques
        ImageView fondRegarderIci = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/lookHere.png")));
        fondRegarderIci.setFitWidth(100 * i);
        fondRegarderIci.setPreserveRatio(true);
        fondRegarderIci.setLayoutX(195 * i);
        fondRegarderIci.setLayoutY(5 * i);
        this.getChildren().add(fondRegarderIci);


        Text texteAppuiSimple = new Text();
        texteAppuiSimple.setLayoutX(15 * i);
        texteAppuiSimple.setLayoutY(95 * i);
        texteAppuiSimple.setFont(new Font(15 * i));
        texteAppuiSimple.setFill(Color.web("#696969", 1.0));
        texteAppuiSimple.setTextAlignment(TextAlignment.CENTER);
        texteAppuiSimple.setText(Langue.getLangue().getString("EmptyAssociationEditionPane_text"));
        this.getChildren().add(texteAppuiSimple);
    }

    @Override
    public AssociationsDansProfil getAssociations()
    {
        AssociationsDansProfil assocARetourner = new AssociationsDansProfil();
        assocARetourner.add(new Association());
        return assocARetourner;
    }

    @Override
    public boolean associationValide()
    {
        return true;
    }

    @Override
    public String getMessageDInvalidite()
    {
        return "";
    }


}
