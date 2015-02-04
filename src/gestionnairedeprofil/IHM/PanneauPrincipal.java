/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.IHM;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
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
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
public class PanneauPrincipal extends Application
{

    int i = 0;

    @Override
    public void start(final Stage Stage)
    {
        // Configuration des paramètres d'écran

        final double dim = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 200.0) / 720.0;
        System.out.println(dim);

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
        texteNumEtape1.setText("1");

        Text texteNumEtape2 = new Text();
        texteNumEtape2.setX(285 * dim);
        texteNumEtape2.setY(40 * dim);
        texteNumEtape2.setFont(new Font(20 * dim));
        texteNumEtape2.setFill(Color.WHITE);
        texteNumEtape2.setTextAlignment(TextAlignment.CENTER);
        texteNumEtape2.setText("2");

        Text texteNumEtape3 = new Text();
        texteNumEtape3.setX(525 * dim);
        texteNumEtape3.setY(40 * dim);
        texteNumEtape3.setFont(new Font(20 * dim));
        texteNumEtape3.setFill(Color.WHITE);
        texteNumEtape3.setTextAlignment(TextAlignment.CENTER);
        texteNumEtape3.setText("3");

        Text texteEtape1 = new Text();
        texteEtape1.setX(80 * dim);
        texteEtape1.setY(30 * dim);
        texteEtape1.setFont(new Font(20 * dim));
        texteEtape1.setFill(Color.web("#696969", 1.0));
        texteEtape1.setTextAlignment(TextAlignment.CENTER);
        texteEtape1.setText("Gestion des\nprofils sur PC");

        Text texteEtape2 = new Text();
        texteEtape2.setX(320 * dim);
        texteEtape2.setY(30 * dim);
        texteEtape2.setFont(new Font(20 * dim));
        texteEtape2.setFill(Color.web("#696969", 1.0));
        texteEtape2.setTextAlignment(TextAlignment.CENTER);
        texteEtape2.setText("Profils à envoyer\nsur le JoyPad");

        Text texteEtape3 = new Text();
        texteEtape3.setX(575 * dim);
        texteEtape3.setY(40 * dim);
        texteEtape3.setFont(new Font(20 * dim));
        texteEtape3.setFill(Color.web("#696969", 1.0));
        texteEtape3.setTextAlignment(TextAlignment.CENTER);
        texteEtape3.setText("Envoi");

        Text texteSelectionLecteur = new Text();
        texteSelectionLecteur.setX(515 * dim);
        texteSelectionLecteur.setY(125 * dim);
        texteSelectionLecteur.setFont(new Font(15 * dim));
        texteSelectionLecteur.setFill(Color.web("#696969", 1.0));
        texteSelectionLecteur.setTextAlignment(TextAlignment.CENTER);
        texteSelectionLecteur.setText("Selectionnez le lecteur de\nla carte SD...");

        Text texteBoutonEnvoyer = new Text();
        texteBoutonEnvoyer.setX(550 * dim);
        texteBoutonEnvoyer.setY(250 * dim);
        texteBoutonEnvoyer.setFont(new Font(15 * dim));
        texteBoutonEnvoyer.setFill(Color.web("#696969", 1.0));
        texteBoutonEnvoyer.setTextAlignment(TextAlignment.CENTER);
        texteBoutonEnvoyer.setText("... et envoyez!");

        // Configuration des contrôles

        Accordion panneauProfilsDuPc = new Accordion();
        panneauProfilsDuPc.setLayoutX(20 * dim);
        panneauProfilsDuPc.setLayoutY(70 * dim);
        panneauProfilsDuPc.setPrefSize(200 * dim, 270 * dim);
        panneauProfilsDuPc.setMaxSize(200 * dim, 270 * dim);
        panneauProfilsDuPc.setMinSize(200 * dim, 270 * dim);

        Button btnCreationProfil = new Button("Ajouter", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/add.png"))));
        btnCreationProfil.setLayoutX(20 * dim);
        btnCreationProfil.setLayoutY(345 * dim);
        btnCreationProfil.setPrefSize(200 * dim, 20 * dim);
        btnCreationProfil.setMaxSize(200 * dim, 20 * dim);
        btnCreationProfil.setMinSize(200 * dim, 20 * dim);
        btnCreationProfil.setFont(new Font(7 * dim));
        Tooltip infobulleBtnAjouter = new Tooltip();
        infobulleBtnAjouter.setText("Créer un nouveau profil sur le PC");
        btnCreationProfil.setTooltip(infobulleBtnAjouter);

        final ListeProfilsAEnvoyer panneauProfilsVersPad = new ListeProfilsAEnvoyer(dim);
        panneauProfilsVersPad.setLayoutX(260 * dim);
        panneauProfilsVersPad.setLayoutY(70 * dim);
        panneauProfilsVersPad.setPrefHeight(270 * dim);
        panneauProfilsVersPad.setMinHeight(270 * dim);
        panneauProfilsVersPad.setMaxHeight(270 * dim);

        ComboBox cbSelectionLecteur = new ComboBox();
        cbSelectionLecteur.setLayoutX(575 * dim);
        cbSelectionLecteur.setLayoutY(175 * dim);
        cbSelectionLecteur.setPrefSize(40 * dim, 20 * dim);
        cbSelectionLecteur.setMaxSize(40 * dim, 20 * dim);
        cbSelectionLecteur.setMinSize(40 * dim, 20 * dim);

        Button btnafraichirListeLecteurs = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/refresh.png"))));
        btnafraichirListeLecteurs.setLayoutX(620 * dim);
        btnafraichirListeLecteurs.setLayoutY(175 * dim);
        btnafraichirListeLecteurs.setPrefSize(20 * dim, 20 * dim);
        btnafraichirListeLecteurs.setMaxSize(20 * dim, 20 * dim);
        btnafraichirListeLecteurs.setMinSize(20 * dim, 20 * dim);
        Tooltip infobulleBtnRafraichir = new Tooltip();
        infobulleBtnRafraichir.setText("Rafraîchir la liste des lecteurs");
        btnafraichirListeLecteurs.setTooltip(infobulleBtnRafraichir);

        Button btnEnvoiVersCarteSD = new Button("Démarrer l'envoi", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnEnvoiVersCarteSD.setLayoutX(550 * dim);
        btnEnvoiVersCarteSD.setLayoutY(280 * dim);
        btnEnvoiVersCarteSD.setPrefSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setMaxSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setMinSize(100 * dim, 20 * dim);
        btnEnvoiVersCarteSD.setFont(new Font(7 * dim));
        Tooltip infobulleBtnEnvoi = new Tooltip();
        infobulleBtnEnvoi.setText("Envoyer les profils vers la carte SD");
        btnEnvoiVersCarteSD.setTooltip(infobulleBtnEnvoi);

        // Pour l'exemple

        final ListeProfilsDisponiblesMachine panneau = new ListeProfilsDisponiblesMachine(dim, "Machine 1", Stage);
        panneauProfilsDuPc.getPanes().add(panneau);

        btnCreationProfil.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                new PanneauProfilDisponible(dim, panneau, panneauProfilsVersPad, "Profil 1", 1);
                new PanneauCreationProfil(dim, Stage);
            }
        });

        List<File> files = Arrays.asList(File.listRoots());
        for (File f : files) {
            String s = f.getAbsolutePath().toString();
            cbSelectionLecteur.getItems().add(s);
        }
        cbSelectionLecteur.setValue(files.get(0).getAbsolutePath().toString());
        // Chargement des éléments dynamiques de l'IHM

        /* Charger ici la liste des profils et des machines */

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
        root.getChildren().add(panneauProfilsDuPc);
        root.getChildren().add(btnCreationProfil);
        root.getChildren().add(panneauProfilsVersPad);
        root.getChildren().add(cbSelectionLecteur);
        root.getChildren().add(btnafraichirListeLecteurs);
        root.getChildren().add(btnEnvoiVersCarteSD);

        // Configuration de Scene
        panneauProfilsDuPc.getPanes().get(0).expandedProperty().set(true);

        Scene scene = new Scene(root, 720 * dim, 380 * dim, new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.web("#FFFFFF", 1.0)), new Stop(1, Color.web("#B4B4B4", 1.0))}));

        // Configuration de Stage

        Stage.setTitle("Gestionnaire de profil");
        Stage.setScene(scene);
        Stage.setResizable(false);
        Stage.show();
    }
}
