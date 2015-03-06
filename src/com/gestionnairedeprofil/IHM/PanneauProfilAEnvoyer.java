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

import com.gestionnairedeprofil.donnees.structures.Profil;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author MOREL Charles
 */
public class PanneauProfilAEnvoyer extends Pane
{

    private final Profil numProfil;
    private final Button btnLever;
    private final Button btnDessendre;

    public PanneauProfilAEnvoyer(double dim, final ListeProfilsAEnvoyer listeOuAjouterLePanneau, StringProperty nomProfil, Profil profilRattache)
    {
        this.numProfil = profilRattache;

        final Label nomProfilAAfficher = new Label();
        nomProfilAAfficher.setLayoutX(5 * dim);
        nomProfilAAfficher.setLayoutY(3 * dim);
        nomProfilAAfficher.setPrefWidth(105 * dim);
        nomProfilAAfficher.setMinWidth(105 * dim);
        nomProfilAAfficher.setMaxWidth(105 * dim);
        nomProfilAAfficher.setFont(new Font(7 * dim));
        nomProfilAAfficher.textProperty().bind(nomProfil);
        this.getChildren().add(nomProfilAAfficher);

        this.btnLever = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/up.png"))));
        this.btnLever.setLayoutX(109 * dim);
        this.btnLever.setLayoutY(1 * dim);
        this.btnLever.setMaxSize(25 * dim, 15 * dim);
        this.btnLever.setMinSize(25 * dim, 15 * dim);
        this.btnLever.setPrefSize(25 * dim, 15 * dim);
        Tooltip infobulleBtnLever = new Tooltip();
        infobulleBtnLever.setText("Lever le profil dans la liste");
        this.btnLever.setTooltip(infobulleBtnLever);
        this.getChildren().add(this.btnLever);

        this.btnDessendre = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/down.png"))));
        this.btnDessendre.setLayoutX(137 * dim);
        this.btnDessendre.setLayoutY(1 * dim);
        this.btnDessendre.setMaxSize(25 * dim, 15 * dim);
        this.btnDessendre.setMinSize(25 * dim, 15 * dim);
        this.btnDessendre.setPrefSize(25 * dim, 15 * dim);
        Tooltip infobulleBtnDessendre = new Tooltip();
        infobulleBtnDessendre.setText("Dessendre le profil dans la liste");
        this.btnDessendre.setTooltip(infobulleBtnDessendre);
        this.getChildren().add(this.btnDessendre);

        Button btnEnleverDeLaListe = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/removeFromList.png"))));
        btnEnleverDeLaListe.setLayoutX(165 * dim);
        btnEnleverDeLaListe.setLayoutY(1 * dim);
        btnEnleverDeLaListe.setMaxSize(25 * dim, 15 * dim);
        btnEnleverDeLaListe.setMinSize(25 * dim, 15 * dim);
        btnEnleverDeLaListe.setPrefSize(25 * dim, 15 * dim);
        Tooltip infobulleBtnEnlever = new Tooltip();
        infobulleBtnEnlever.setText("Enlever le profil de la liste");
        btnEnleverDeLaListe.setTooltip(infobulleBtnEnlever);
        this.getChildren().add(btnEnleverDeLaListe);

        btnEnleverDeLaListe.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                listeOuAjouterLePanneau.enleverProfilAEnvoyer(PanneauProfilAEnvoyer.this);
            }
        });

        this.btnLever.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                listeOuAjouterLePanneau.leverUnProfilDansLaListe(PanneauProfilAEnvoyer.this);
            }
        });
        this.btnDessendre.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                listeOuAjouterLePanneau.dessendreUnProfilDansLaListe(PanneauProfilAEnvoyer.this);
            }
        });
        nomProfilAAfficher.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1)
            {
                if ("          ".equals(nomProfilAAfficher.getText())) {
                    listeOuAjouterLePanneau.enleverProfilAEnvoyer(PanneauProfilAEnvoyer.this);
                }
            }
        });

        listeOuAjouterLePanneau.ajouterProfilAEnvoyer(this);
    }

    /**
     * @return the numProfil
     */
    public Profil getProfil()
    {
        return this.numProfil;
    }

    /**
     * @return the btnLever
     */
    public Button getBtnLever()
    {
        return btnLever;
    }

    /**
     * @return the btnDessendre
     */
    public Button getBtnDessendre()
    {
        return btnDessendre;
    }

}
