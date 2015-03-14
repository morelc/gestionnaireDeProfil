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
import com.gestionnairedeprofil.donnees.structures.Profil;
import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Fenêtre (et code métier) de création des fichiers de profil sur le lecteur.
 *
 * Window of Profiles file creation
 *
 * @author MOREL Charles
 */
public class StageEnvoiProfilSurCarteSD extends Stage
{

    public final static String NOM_FICHIER_PROFIL = "conf.bin";
    public final static String NOM_FICHIER_PROFIL_SELECTIONNE = "prefs.bin";
    private final AnchorPane panneauADeplacer;
    private final ArrayList<Profil> listeDesProfils;
    private File fichierProfil;
    private final double dim;
    private int etape;
    private Text texteErreur;

    public StageEnvoiProfilSurCarteSD(final double i, Stage stageParent, String emplacement, ArrayList<Profil> listeDesProfilsAEnvoyer)
    {
        // configuration des dépendances
        this.setTitle(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_title"));
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/saveToSDCard.png")));
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);
        this.dim = i;
        this.listeDesProfils = listeDesProfilsAEnvoyer;
        this.texteErreur = new Text();
        File racineProfil = new File(emplacement);
        this.fichierProfil = new File(emplacement + StageEnvoiProfilSurCarteSD.NOM_FICHIER_PROFIL);
        if (listeDesProfilsAEnvoyer.isEmpty()) {
            this.etape = -1;
            this.texteErreur.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_texteErreur1"));
        }
        else if (!racineProfil.exists()) {
            this.etape = -1;
            this.texteErreur.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_texteErreur2"));
        }
        else if (this.fichierProfil.exists()) {
            this.etape = 0;
        }
        else {
            this.etape = 1;
        }

        // Configuration des noeuds statiques
        this.panneauADeplacer = new AnchorPane();
        this.panneauADeplacer.setLayoutX(0);
        this.panneauADeplacer.setLayoutY(0);
        this.panneauADeplacer.setMinSize(1600 * i, 80 * i);
        this.panneauADeplacer.setPrefSize(1600 * i, 80 * i);
        this.panneauADeplacer.setMaxSize(1600 * i, 80 * i);

        ImageView fondErreur = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/info.png")));
        fondErreur.setFitHeight(90 * i);
        fondErreur.setPreserveRatio(true);
        fondErreur.setLayoutX(-10 * i);
        fondErreur.setLayoutY(10 * i);

        this.texteErreur.setLayoutX(90 * i);
        this.texteErreur.setLayoutY(30 * i);
        this.texteErreur.setFont(new Font(15 * i));
        this.texteErreur.setFill(Color.web("#696969", 1.0));
        this.texteErreur.setTextAlignment(TextAlignment.LEFT);

        ImageView fondAttention = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/warning.png")));
        fondAttention.setFitHeight(78 * i);
        fondAttention.setPreserveRatio(true);
        fondAttention.setLayoutX(410 * i);
        fondAttention.setLayoutY(3 * i);

        Text texteAttention = new Text();
        texteAttention.setLayoutX(480 * i);
        texteAttention.setLayoutY(20 * i);
        texteAttention.setFont(new Font(15 * i));
        texteAttention.setFill(Color.web("#696969", 1.0));
        texteAttention.setTextAlignment(TextAlignment.CENTER);
        texteAttention.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_texteAttention"));

        ProgressBar pbPlacebo = new ProgressBar();
        pbPlacebo.setLayoutX(820 * i);
        pbPlacebo.setLayoutY(50 * i);
        pbPlacebo.setMinWidth(360 * i);
        pbPlacebo.setPrefWidth(360 * i);
        pbPlacebo.setMaxWidth(360 * i);
        pbPlacebo.setProgress(-1);

        Text textePlocebo = new Text();
        textePlocebo.setLayoutX(950 * i);
        textePlocebo.setLayoutY(30 * i);
        textePlocebo.setFont(new Font(15 * i));
        textePlocebo.setFill(Color.web("#696969", 1.0));
        textePlocebo.setTextAlignment(TextAlignment.LEFT);
        textePlocebo.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_textePlocebo"));

        Text texteNePasRetirerCarteSD = new Text();
        texteNePasRetirerCarteSD.setLayoutX(900 * i);
        texteNePasRetirerCarteSD.setLayoutY(75 * i);
        texteNePasRetirerCarteSD.setFont(new Font(8 * i));
        texteNePasRetirerCarteSD.setFill(Color.web("#696969", 1.0));
        texteNePasRetirerCarteSD.setTextAlignment(TextAlignment.LEFT);
        texteNePasRetirerCarteSD.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_texteNePasRetirerCarteSD"));

        ImageView fondFin = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/saveToSDCard.png")));
        fondFin.setFitHeight(78 * i);
        fondFin.setPreserveRatio(true);
        fondFin.setLayoutX(1207 * i);
        fondFin.setLayoutY(3 * i);
        fondFin.setOpacity(0.60);

        Text texteFin = new Text();
        texteFin.setLayoutX(1300 * i);
        texteFin.setLayoutY(20 * i);
        texteFin.setFont(new Font(15 * i));
        texteFin.setFill(Color.web("#696969", 1.0));
        texteFin.setTextAlignment(TextAlignment.LEFT);
        texteFin.setText(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_texteFin"));

        // Configuration des contrôles

        Button btnFermerFenetreErreur = new Button(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_btnFermerFenetreErreur"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnFermerFenetreErreur.setLayoutX(325 * i);
        btnFermerFenetreErreur.setLayoutY(45 * i);
        btnFermerFenetreErreur.setPrefSize(50 * i, 25 * i);
        btnFermerFenetreErreur.setMaxSize(50 * i, 25 * i);
        btnFermerFenetreErreur.setMinSize(50 * i, 25 * i);
        btnFermerFenetreErreur.setFont(new Font(7 * i));

        Button btnContinuerEnvoi = new Button(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_btnContinuerEnvoi"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnContinuerEnvoi.setLayoutX(700 * i);
        btnContinuerEnvoi.setLayoutY(50 * i);
        btnContinuerEnvoi.setPrefSize(75 * i, 25 * i);
        btnContinuerEnvoi.setMaxSize(75 * i, 25 * i);
        btnContinuerEnvoi.setMinSize(75 * i, 25 * i);
        btnContinuerEnvoi.setFont(new Font(7 * i));

        Button btnAnnulerEnvoi = new Button(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_btnAnnulerEnvoi"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnAnnulerEnvoi.setLayoutX(600 * i);
        btnAnnulerEnvoi.setLayoutY(50 * i);
        btnAnnulerEnvoi.setPrefSize(75 * i, 25 * i);
        btnAnnulerEnvoi.setMaxSize(75 * i, 25 * i);
        btnAnnulerEnvoi.setMinSize(75 * i, 25 * i);
        btnAnnulerEnvoi.setFont(new Font(7 * i));

        Button btnOk = new Button(Langue.getLangue().getString("StageEnvoiProfilSurCarteSD_btnOk"), new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(1500 * i);
        btnOk.setLayoutY(50 * i);
        btnOk.setPrefSize(75 * i, 25 * i);
        btnOk.setMaxSize(75 * i, 25 * i);
        btnOk.setMinSize(75 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));


        // Configuration du noeud racine Root et de Scene
        this.panneauADeplacer.getChildren().add(fondErreur);
        this.panneauADeplacer.getChildren().add(this.texteErreur);
        this.panneauADeplacer.getChildren().add(fondAttention);
        this.panneauADeplacer.getChildren().add(texteAttention);
        this.panneauADeplacer.getChildren().add(pbPlacebo);
        this.panneauADeplacer.getChildren().add(textePlocebo);
        this.panneauADeplacer.getChildren().add(texteNePasRetirerCarteSD);
        this.panneauADeplacer.getChildren().add(fondFin);
        this.panneauADeplacer.getChildren().add(texteFin);
        this.panneauADeplacer.getChildren().add(btnFermerFenetreErreur);
        this.panneauADeplacer.getChildren().add(btnContinuerEnvoi);
        this.panneauADeplacer.getChildren().add(btnAnnulerEnvoi);
        this.panneauADeplacer.getChildren().add(btnOk);

        Group root = new Group();
        root.getChildren().add(this.panneauADeplacer);
        this.setScene(new Scene(root, 400 * i, 80 * i, Color.gray(0.85)));

        this.setOnCloseRequest(new EventHandler<WindowEvent>()
        {

            @Override
            public void handle(WindowEvent t)
            {
                if (StageEnvoiProfilSurCarteSD.this.etape == 1) {
                    t.consume();
                }
                else {
                    StageEnvoiProfilSurCarteSD.this.close();
                }
            }
        });

        btnFermerFenetreErreur.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageEnvoiProfilSurCarteSD.this.close();
            }
        });

        btnAnnulerEnvoi.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageEnvoiProfilSurCarteSD.this.close();
            }
        });

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageEnvoiProfilSurCarteSD.this.close();
            }
        });

        btnContinuerEnvoi.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageEnvoiProfilSurCarteSD.this.etape++;
                StageEnvoiProfilSurCarteSD.this.changerEtape();
            }
        });

        // Autoselection du panneau de départ
        switch (this.etape) {
            case 1:
                this.panneauADeplacer.setLayoutX(-800 * i);
                break;
            case 0:
                this.panneauADeplacer.setLayoutX(-400 * i);
                break;
            case -1:
            default:
                this.panneauADeplacer.setLayoutX(0);
                break;
        }

        this.changerEtape();

        this.show();
    }

    private void changerEtape()
    {
        EventHandler<ActionEvent> passageALEtapeSuivante = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                changerEtape();
            }
        };
        double nvEmplacementX = -400 * (this.etape + 1) * this.dim;
        Timeline timelinePanneauAADeplacer = new Timeline();
        timelinePanneauAADeplacer.setAutoReverse(false);
        timelinePanneauAADeplacer.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(StageEnvoiProfilSurCarteSD.this.panneauADeplacer.layoutXProperty(), nvEmplacementX)));
        timelinePanneauAADeplacer.play();
        if (this.etape == 1) {
            try {
                this.ecrireLesProfis();
                this.etape = 2;
            }
            catch (IOException exception) {
                this.texteErreur.setText("Une erreur s'est produite, veuillez réessayer!");
                System.out.println(exception.getMessage());
                this.etape = -1;
            }
            Timeline timelinechangerEtape = new Timeline();
            timelinechangerEtape.setAutoReverse(false);
            timelinechangerEtape.getKeyFrames().add(new KeyFrame(Duration.millis(1000), passageALEtapeSuivante));
            timelinechangerEtape.play();
        }
    }

    private void ecrireLesProfis() throws IOException
    {
        if (this.fichierProfil.exists()) {
            File fichierSauvegarde = new File(this.fichierProfil.getPath() + ".old");
            if (fichierSauvegarde.exists()) {
                fichierSauvegarde.delete();
            }
            Files.copy(this.fichierProfil.toPath(), fichierSauvegarde.toPath());
        }
        DataOutputStream teteDeLecture = new DataOutputStream(new FileOutputStream(this.fichierProfil));
        teteDeLecture.writeShort(this.listeDesProfils.size());
        for (Profil profilAEcrire : this.listeDesProfils) {
            ecrireUnProfil(teteDeLecture, profilAEcrire);
        }
        teteDeLecture.close();
        teteDeLecture = new DataOutputStream(new FileOutputStream(this.fichierProfil.getParentFile().getAbsolutePath() + NOM_FICHIER_PROFIL_SELECTIONNE));
        teteDeLecture.writeShort(0);
        teteDeLecture.close();
    }

    private void ecrireUnProfil(DataOutputStream teteDeLecture, Profil profilAEcrire) throws IOException
    {
        ecrireNomProfil(teteDeLecture, profilAEcrire.getNom());
        teteDeLecture.writeShort(profilAEcrire.getId());
        for (int x = 0; x < 12; x++) {
            ecrireAssociationDansProfil(teteDeLecture, profilAEcrire.getAssociationsAt(x));
        }
    }

    private void ecrireNomProfil(DataOutputStream teteDeLecture, String nomProfil) throws IOException
    {
        if (nomProfil.length() < 16) {
            for (int x = nomProfil.length(); x < 16; x++) {
                nomProfil += " ";
            }
        }
        for (int x = 0; x < 16; x++) {
            teteDeLecture.write(nomProfil.charAt(x));
        }
    }

    private void ecrireAssociationDansProfil(DataOutputStream teteDeLecture, AssociationsDansProfil associationAEnregistrer) throws IOException
    {
        switch (associationAEnregistrer.getAssocType()) {
            case AssociationsDansProfil.TYPE_MACRO:
                teteDeLecture.writeByte(0);
                int idAssocDansListe = 0;
                int dernierTemps = 0;
                for (Association associationAEcrire : associationAEnregistrer) {
                    idAssocDansListe++;
                    short signalMacroAEnvoyer = 0;
                    for (ToucheMachine toucheATraiter : associationAEcrire.getTouches()) {
                        signalMacroAEnvoyer = (short) (signalMacroAEnvoyer | Short.valueOf((short) toucheATraiter.getSignal()));
                    }
                    teteDeLecture.writeShort(signalMacroAEnvoyer);
                    teteDeLecture.writeShort(associationAEcrire.getTimer() - dernierTemps);
                    dernierTemps = associationAEcrire.getTimer();
                    if (associationAEnregistrer.size() == idAssocDansListe) {
                        teteDeLecture.writeByte(0);
                    }
                    else {
                        teteDeLecture.writeByte(1);
                    }
                }
                break;
            case AssociationsDansProfil.TYPE_AUTOFIRE:
                teteDeLecture.writeByte(1);
                short signalAutofireAEnvoyer = 0;
                for (ToucheMachine toucheATraiter : associationAEnregistrer.get(0).getTouches()) {
                    signalAutofireAEnvoyer = (short) (signalAutofireAEnvoyer | Short.valueOf((short) toucheATraiter.getSignal()));
                }
                teteDeLecture.writeShort(signalAutofireAEnvoyer);
                teteDeLecture.writeShort(associationAEnregistrer.get(0).getTimer());
                break;
            case AssociationsDansProfil.TYPE_COMBINAISON:
                teteDeLecture.writeByte(0);
                short signalCombianisonAEnvoyer = 0;
                for (ToucheMachine toucheATraiter : associationAEnregistrer.get(0).getTouches()) {
                    signalCombianisonAEnvoyer = (short) (signalCombianisonAEnvoyer | Short.valueOf((short) toucheATraiter.getSignal()));
                }
                teteDeLecture.writeShort(signalCombianisonAEnvoyer);
                for (int x = 0; x < 3; x++) {
                    teteDeLecture.writeByte(0);
                }
                break;
            case AssociationsDansProfil.TYPE_TOUCHE_SIMPLE:
                teteDeLecture.writeByte(0);
                teteDeLecture.writeShort(associationAEnregistrer.get(0).getTouches().get(0).getSignal());
                for (int x = 0; x < 3; x++) {
                    teteDeLecture.writeByte(0);
                }
                break;
            default:
            case AssociationsDansProfil.TYPE_VIDE:
                for (int x = 0; x < 6; x++) {
                    teteDeLecture.writeByte(0);
                }
                break;
        }
    }

}
