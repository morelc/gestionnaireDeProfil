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
import com.gestionnairedeprofil.configuration.StageDeConfiguration;
import com.gestionnairedeprofil.donnees.BD.MachineDAO;
import com.gestionnairedeprofil.donnees.structures.Association;
import com.gestionnairedeprofil.donnees.structures.Machine;
import com.gestionnairedeprofil.donnees.structures.Profil;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe implémentant Application, il contiens le stage principal de
 * l'application ainsi que toutes les données des profils.
 *
 * Class implementing Application, it contain the main stage of the application
 * and all the data of the profiles.
 *
 * @author MOREL Charles
 */
public class ApplicationPrincipale extends Application
{

    private ComboBox cbSelectionLecteur;

    @Override
    public void start(final Stage stage)
    {
        // Configuration de l'application

        final double dim = (Screen.getPrimary().getBounds().getWidth() - 200) / 720;

        // Configuration des séparations

        Line separateur1 = new Line();
        separateur1.setStartX(240 * dim);
        separateur1.setStartY(50 * dim);
        separateur1.setEndX(240 * dim);
        separateur1.setEndY(350 * dim);
        separateur1.setStroke(Color.web("#696969", 1.0));
        separateur1.setStrokeWidth(1 * dim);

        Line separateur2 = new Line();
        separateur2.setStartX(480 * dim);
        separateur2.setStartY(50 * dim);
        separateur2.setEndX(480 * dim);
        separateur2.setEndY(350 * dim);
        separateur2.setStroke(Color.web("#696969", 1.0));
        separateur2.setStrokeWidth(1 * dim);

        // Configuration des noeuds statiques

        Circle cercleEtape1 = new Circle();
        cercleEtape1.setCenterX(50 * dim);
        cercleEtape1.setCenterY(35 * dim);
        cercleEtape1.setRadius(20 * dim);
        cercleEtape1.setFill(Color.web("#696969", 1.0));

        Circle cercleEtape2 = new Circle();
        cercleEtape2.setCenterX(290 * dim);
        cercleEtape2.setCenterY(35 * dim);
        cercleEtape2.setRadius(20 * dim);
        cercleEtape2.setFill(Color.web("#696969", 1.0));

        Circle cercleEtape3 = new Circle();
        cercleEtape3.setCenterX(530 * dim);
        cercleEtape3.setCenterY(35 * dim);
        cercleEtape3.setRadius(20 * dim);
        cercleEtape3.setFill(Color.web("#696969", 1.0));

        Text texteNumEtape1 = new Text();
        texteNumEtape1.setX(45 * dim);
        texteNumEtape1.setY(40 * dim);
        texteNumEtape1.setFont(new Font(20 * dim));
        texteNumEtape1.setFill(Color.WHITE);
        texteNumEtape1.setTextAlignment(TextAlignment.CENTER);
        texteNumEtape1.setText(Langue.getLangue().getString("MainApp_stepNum1"));

        Text texteNumEtape2 = new Text();
        texteNumEtape2.setX(285 * dim);
        texteNumEtape2.setY(40 * dim);
        texteNumEtape2.setFont(new Font(20 * dim));
        texteNumEtape2.setFill(Color.WHITE);
        texteNumEtape2.setTextAlignment(TextAlignment.CENTER);
        texteNumEtape2.setText(Langue.getLangue().getString("MainApp_stepNum2"));

        Text texteNumEtape3 = new Text();
        texteNumEtape3.setX(525 * dim);
        texteNumEtape3.setY(40 * dim);
        texteNumEtape3.setFont(new Font(20 * dim));
        texteNumEtape3.setFill(Color.WHITE);
        texteNumEtape3.setTextAlignment(TextAlignment.CENTER);
        texteNumEtape3.setText(Langue.getLangue().getString("MainApp_stepNum3"));

        Text texteEtape1 = new Text();
        texteEtape1.setX(80 * dim);
        texteEtape1.setY(30 * dim);
        texteEtape1.setFont(new Font(20 * dim));
        texteEtape1.setFill(Color.web("#696969", 1.0));
        texteEtape1.setTextAlignment(TextAlignment.CENTER);
        texteEtape1.setText(Langue.getLangue().getString("MainApp_textStep1"));

        Text texteEtape2 = new Text();
        texteEtape2.setX(320 * dim);
        texteEtape2.setY(30 * dim);
        texteEtape2.setFont(new Font(20 * dim));
        texteEtape2.setFill(Color.web("#696969", 1.0));
        texteEtape2.setTextAlignment(TextAlignment.CENTER);
        texteEtape2.setText(Langue.getLangue().getString("MainApp_textStep2"));

        Text texteEtape3 = new Text();
        texteEtape3.setX(575 * dim);
        texteEtape3.setY(40 * dim);
        texteEtape3.setFont(new Font(20 * dim));
        texteEtape3.setFill(Color.web("#696969", 1.0));
        texteEtape3.setTextAlignment(TextAlignment.CENTER);
        texteEtape3.setText(Langue.getLangue().getString("MainApp_textStep3"));

        Text texteSelectionLecteur = new Text();
        texteSelectionLecteur.setX(515 * dim);
        texteSelectionLecteur.setY(125 * dim);
        texteSelectionLecteur.setFont(new Font(15 * dim));
        texteSelectionLecteur.setFill(Color.web("#696969", 1.0));
        texteSelectionLecteur.setTextAlignment(TextAlignment.CENTER);
        texteSelectionLecteur.setText(Langue.getLangue().getString("MainApp_textDeviceSelection"));

        Text texteBoutonEnvoyer = new Text();
        texteBoutonEnvoyer.setX(550 * dim);
        texteBoutonEnvoyer.setY(250 * dim);
        texteBoutonEnvoyer.setFont(new Font(15 * dim));
        texteBoutonEnvoyer.setFill(Color.web("#696969", 1.0));
        texteBoutonEnvoyer.setTextAlignment(TextAlignment.CENTER);
        texteBoutonEnvoyer.setText(Langue.getLangue().getString("MainApp_textBtnSend"));

        MenuDAideLogiciel menuDAide = new MenuDAideLogiciel(dim, stage, this);
        menuDAide.setLayoutX(700 * dim);
        menuDAide.setLayoutY(15 * dim);

        // Configuration des contrôles

        final Accordion panneauProfilsDuPc = new Accordion();
        panneauProfilsDuPc.setLayoutX(20 * dim);
        panneauProfilsDuPc.setLayoutY(70 * dim);
        panneauProfilsDuPc.setPrefSize(200 * dim, 270 * dim);
        panneauProfilsDuPc.setMaxSize(200 * dim, 270 * dim);
        panneauProfilsDuPc.setMinSize(200 * dim, 270 * dim);

        Button btnCreationProfil = new Button(Langue.getLangue().getString("MainApp_btnProfileCreation"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/add.png"))));
        btnCreationProfil.setLayoutX(20 * dim);
        btnCreationProfil.setLayoutY(345 * dim);
        btnCreationProfil.setPrefSize(200 * dim, 20 * dim);
        btnCreationProfil.setMaxSize(200 * dim, 20 * dim);
        btnCreationProfil.setMinSize(200 * dim, 20 * dim);
        btnCreationProfil.setFont(new Font(7 * dim));
        Tooltip infobulleBtnAjouter = new Tooltip();
        infobulleBtnAjouter.setText(Langue.getLangue().getString("MainApp_infobulleBtnCreation"));
        btnCreationProfil.setTooltip(infobulleBtnAjouter);

        final ListeProfilsAEnvoyer panneauProfilsVersPad = new ListeProfilsAEnvoyer(dim, panneauProfilsDuPc);
        panneauProfilsVersPad.setLayoutX(260 * dim);
        panneauProfilsVersPad.setLayoutY(70 * dim);
        panneauProfilsVersPad.setPrefHeight(270 * dim);
        panneauProfilsVersPad.setMinHeight(270 * dim);
        panneauProfilsVersPad.setMaxHeight(270 * dim);

        this.cbSelectionLecteur = new ComboBox();
        this.cbSelectionLecteur.setLayoutX(575 * dim);
        this.cbSelectionLecteur.setLayoutY(175 * dim);
        this.cbSelectionLecteur.setPrefSize(40 * dim, 20 * dim);
        this.cbSelectionLecteur.setMaxSize(40 * dim, 20 * dim);
        this.cbSelectionLecteur.setMinSize(40 * dim, 20 * dim);

        Button btnafraichirListeLecteurs = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/refresh.png"))));
        btnafraichirListeLecteurs.setLayoutX(620 * dim);
        btnafraichirListeLecteurs.setLayoutY(175 * dim);
        btnafraichirListeLecteurs.setPrefSize(20 * dim, 20 * dim);
        btnafraichirListeLecteurs.setMaxSize(20 * dim, 20 * dim);
        btnafraichirListeLecteurs.setMinSize(20 * dim, 20 * dim);
        Tooltip infobulleBtnRafraichir = new Tooltip();
        infobulleBtnRafraichir.setText(Langue.getLangue().getString("MainApp_btnrefreshDevicesList"));
        btnafraichirListeLecteurs.setTooltip(infobulleBtnRafraichir);

        Button btnEnvoiVersCarteSD = new Button(Langue.getLangue().getString("MainApp_btnSendToSDCard"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnEnvoiVersCarteSD.setLayoutX(550 * dim);
        btnEnvoiVersCarteSD.setLayoutY(280 * dim);
        btnEnvoiVersCarteSD.setPrefSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setMaxSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setMinSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setFont(new Font(7 * dim));
        Tooltip infobulleBtnEnvoi = new Tooltip();
        infobulleBtnEnvoi.setText(Langue.getLangue().getString("MainApp_infobulleBtnSend"));
        btnEnvoiVersCarteSD.setTooltip(infobulleBtnEnvoi);

        // Pour l'exemple

        final ArrayList<Machine> toutesLesMachines = new ArrayList();


        Machine machine1 = new Machine(2, "Sega Master System", "SMS.png");

        ToucheMachine touche2 = new ToucheMachine(0, "←", 2);
        ToucheMachine touche6 = new ToucheMachine(1, "↑", 8);
        ToucheMachine touche7 = new ToucheMachine(2, "→", 16);
        ToucheMachine touche8 = new ToucheMachine(3, "↓", 32);
        ToucheMachine touche9 = new ToucheMachine(4, "1 - START", 64);
        ToucheMachine touche10 = new ToucheMachine(5, "2", 128);

        machine1.ajouterTouche(touche2);
        machine1.ajouterTouche(touche6);
        machine1.ajouterTouche(touche7);
        machine1.ajouterTouche(touche8);
        machine1.ajouterTouche(touche9);
        machine1.ajouterTouche(touche10);

        Profil profil1 = new Profil(1, "Profil type");
        Association assoc1 = new Association();
        profil1.getAssociationsAt(0).add(assoc1);
        Association assoc2 = new Association(2, false, 0);
        assoc2.getTouches().add(machine1.getTouches().get(1));
        profil1.getAssociationsAt(1).add(assoc2);
        Association assoc3 = new Association(3, false, 0);
        assoc3.getTouches().add(machine1.getTouches().get(1));
        assoc3.getTouches().add(machine1.getTouches().get(2));
        profil1.getAssociationsAt(2).add(assoc3);
        Association assoc4A = new Association(4, false, 0);
        assoc4A.getTouches().add(machine1.getTouches().get(1));
        profil1.getAssociationsAt(3).add(assoc4A);
        Association assoc4B = new Association(4, false, 10);
        assoc4B.getTouches().add(machine1.getTouches().get(2));
        profil1.getAssociationsAt(3).add(assoc4B);
        Association assoc5 = new Association(5, true, 50);
        assoc5.getTouches().add(machine1.getTouches().get(1));
        profil1.getAssociationsAt(4).add(assoc5);
        Association assoc6 = new Association(6, true, 20);
        assoc6.getTouches().add(machine1.getTouches().get(2));
        assoc6.getTouches().add(machine1.getTouches().get(1));
        profil1.getAssociationsAt(5).add(assoc6);
        Association assoc7 = new Association(7, false, 0);
        assoc7.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(6).add(assoc7);
        Association assoc8 = new Association(8, false, 0);
        assoc8.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(7).add(assoc8);
        Association assoc9 = new Association(9, false, 0);
        assoc9.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(8).add(assoc9);
        Association assoc10 = new Association(10, false, 0);
        assoc10.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(9).add(assoc10);
        Association assoc11 = new Association(11, false, 0);
        assoc11.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(10).add(assoc11);
        Association assoc12 = new Association(12, false, 0);
        assoc12.getTouches().add(machine1.getTouches().get(0));
        profil1.getAssociationsAt(11).add(assoc12);
        machine1.ajouterProfil(profil1);

        toutesLesMachines.add(machine1);

        btnCreationProfil.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageCreationProfil(dim, stage, toutesLesMachines, panneauProfilsDuPc);
            }
        });

        btnafraichirListeLecteurs.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                ApplicationPrincipale.this.rafarichirListeLecteurs();
            }
        });

        btnEnvoiVersCarteSD.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                new StageEnvoiProfilSurCarteSD(dim, stage, ApplicationPrincipale.this.cbSelectionLecteur.getValue().toString(), panneauProfilsVersPad.obtenirListeDesProfilsAEnvoyer());
            }
        });


        // Chargement de la liste des lecteurs
        this.rafarichirListeLecteurs();

        // Chargement des éléments dynamiques de l'IHM

        /* Charger ici la liste des profils et des machines */
        for (Machine machineAAjouter : toutesLesMachines) {
            panneauProfilsDuPc.getPanes().add(new ListeProfilsDisponiblesMachine(dim, machineAAjouter, stage, panneauProfilsVersPad));
        }

        // Ouverture automatique du premier panneau des profils de machine

        Timeline timelineOuvertureAutomatique = new Timeline();
        timelineOuvertureAutomatique.setAutoReverse(false);
        timelineOuvertureAutomatique.getKeyFrames().add(new KeyFrame(Duration.millis(10), new KeyValue(panneauProfilsDuPc.getPanes().get(0).expandedProperty(), true)));
        timelineOuvertureAutomatique.play();

        // Configuration du noeud racine Root

        Group root = new Group();
        root.getChildren().add(separateur1);
        root.getChildren().add(separateur2);
        root.getChildren().add(cercleEtape1);
        root.getChildren().add(cercleEtape2);
        root.getChildren().add(cercleEtape3);
        root.getChildren().add(texteNumEtape1);
        root.getChildren().add(texteNumEtape2);
        root.getChildren().add(texteNumEtape3);
        root.getChildren().add(texteEtape1);
        root.getChildren().add(texteEtape2);
        root.getChildren().add(texteEtape3);
        root.getChildren().add(texteSelectionLecteur);
        root.getChildren().add(texteBoutonEnvoyer);
        root.getChildren().add(menuDAide);
        root.getChildren().add(panneauProfilsDuPc);
        root.getChildren().add(btnCreationProfil);
        root.getChildren().add(panneauProfilsVersPad);
        root.getChildren().add(this.cbSelectionLecteur);
        root.getChildren().add(btnafraichirListeLecteurs);
        root.getChildren().add(btnEnvoiVersCarteSD);


        // Configuration de Scene
        panneauProfilsDuPc.getPanes().get(0).expandedProperty().set(true);
        Scene scene = new Scene(root, 720 * dim, 380 * dim, new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.web("#FFFFFF", 1.0)), new Stop(1, Color.web("#B4B4B4", 1.0))}));

        // Configuration de Stage
        stage.setTitle(Langue.getLangue().getString("MainApp_title"));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/icone.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        StageDeConfiguration.lancerConfiguration(dim, stage);
    }

    /**
     * Fonction de rafraîchissement des lecteurs dans le combobox.
     *
     * Function who update tne combobox with actual devices
     */
    private void rafarichirListeLecteurs()
    {
        this.cbSelectionLecteur.getItems().clear();
        List<File> files = Arrays.asList(File.listRoots());
        for (File f : files) {
            String s = f.getAbsolutePath().toString();
            this.cbSelectionLecteur.getItems().add(s);
        }
        this.cbSelectionLecteur.setValue(files.get(0).getAbsolutePath().toString());
    }
}
