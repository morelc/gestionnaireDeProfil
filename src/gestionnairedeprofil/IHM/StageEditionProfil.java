package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;
import gestionnairedeprofil.donnees.structures.Machine;
import gestionnairedeprofil.donnees.structures.Profil;
import gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author MOREL Charles
 */
public class StageEditionProfil extends Stage
{

    private final double dim;

    public boolean profilModifie;

    private Profil profilAModifier;

    private ArrayList<ToucheMachine> touchesDisponibles;

    private ArrayList<Association> associationsModifies[];

    private final TextField textFieldNomProfil;

    private PanneauProfilDisponible panneauProfilAModifier;

    public StageEditionProfil(double i, final PanneauProfilDisponible panneauProfilConcerne, Stage stageParent, Profil profilAModifier, Machine machineDuProfil)
    {
        // configuration des dépendances
        this.setTitle("Edition du profil");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/edit.png")));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);
        this.profilModifie = false;
        this.dim = i;
        this.profilAModifier = profilAModifier;
        this.touchesDisponibles = machineDuProfil.getTouches();
        this.panneauProfilAModifier = panneauProfilConcerne;
        this.associationsModifies = new ArrayList[12];
        for (int x = 0; x < 12; x++) {
            this.associationsModifies[x] = new ArrayList();
        }

        // Configuration des noeuds statiques

        Text texteNomProfil = new Text();
        texteNomProfil.setLayoutX(20 * i);
        texteNomProfil.setLayoutY(20 * i);
        texteNomProfil.setFont(new Font(12 * i));
        texteNomProfil.setFill(Color.web("#696969", 1.0));
        texteNomProfil.setTextAlignment(TextAlignment.RIGHT);
        texteNomProfil.setText("Nom du profil:");

        Text texteNomMachine = new Text();
        texteNomMachine.setLayoutX(10 * i);
        texteNomMachine.setLayoutY(43 * i);
        texteNomMachine.setFont(new Font(12 * i));
        texteNomMachine.setFill(Color.web("#696969", 1.0));
        texteNomMachine.setTextAlignment(TextAlignment.RIGHT);
        texteNomMachine.setText("Machine associée:");

        Text texteManette = new Text();
        texteManette.setLayoutX(10 * i);
        texteManette.setLayoutY(65 * i);
        texteManette.setFont(new Font(12 * i));
        texteManette.setFill(Color.web("#696969", 1.0));
        texteManette.setTextAlignment(TextAlignment.LEFT);
        texteManette.setText("Manette simulée:");

        Rectangle zoneImgManette = new Rectangle(335 * i, 150 * i);
        zoneImgManette.setLayoutX(10 * i);
        zoneImgManette.setLayoutY(70 * i);
        zoneImgManette.setFill(Color.gray(0.85));
        zoneImgManette.setStroke(Color.web("#696969", 1.0));
        zoneImgManette.setStrokeWidth(1 * i);

        Text textePad = new Text();
        textePad.setLayoutX(10 * i);
        textePad.setLayoutY(240 * i);
        textePad.setFont(new Font(12 * i));
        textePad.setFill(Color.web("#696969", 1.0));
        textePad.setTextAlignment(TextAlignment.LEFT);
        textePad.setText("Touches du joypad:");

        Rectangle zoneImgPad = new Rectangle(335 * i, 130 * i);
        zoneImgPad.setLayoutX(10 * i);
        zoneImgPad.setLayoutY(245 * i);
        zoneImgPad.setFill(Color.gray(0.85));
        zoneImgPad.setStroke(Color.web("#696969", 1.0));
        zoneImgPad.setStrokeWidth(1 * i);

        ImageView btnPad1 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad1.setFitHeight(25 * i);
        btnPad1.setPreserveRatio(true);
        btnPad1.setLayoutX(130 * i);
        btnPad1.setLayoutY(320 * i);

        ImageView btnPad2 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad2.setFitHeight(25 * i);
        btnPad2.setPreserveRatio(true);
        btnPad2.setLayoutX(160 * i);
        btnPad2.setLayoutY(320 * i);

        ImageView btnPad3 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad3.setFitHeight(25 * i);
        btnPad3.setPreserveRatio(true);
        btnPad3.setLayoutX(220 * i);
        btnPad3.setLayoutY(320 * i);

        ImageView btnPad4 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad4.setFitHeight(25 * i);
        btnPad4.setPreserveRatio(true);
        btnPad4.setLayoutX(220 * i);
        btnPad4.setLayoutY(290 * i);

        ImageView btnPad5 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad5.setFitHeight(25 * i);
        btnPad5.setPreserveRatio(true);
        btnPad5.setLayoutX(250 * i);
        btnPad5.setLayoutY(305 * i);

        ImageView btnPad6 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad6.setFitHeight(25 * i);
        btnPad6.setPreserveRatio(true);
        btnPad6.setLayoutX(250 * i);
        btnPad6.setLayoutY(275 * i);

        ImageView btnPad7 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad7.setFitHeight(25 * i);
        btnPad7.setPreserveRatio(true);
        btnPad7.setLayoutX(280 * i);
        btnPad7.setLayoutY(295 * i);

        ImageView btnPad8 = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/padBtn.png")));
        btnPad8.setFitHeight(25 * i);
        btnPad8.setPreserveRatio(true);
        btnPad8.setLayoutX(280 * i);
        btnPad8.setLayoutY(265 * i);

        ImageView stick = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/stick.png")));
        stick.setFitHeight(80 * i);
        stick.setPreserveRatio(true);
        stick.setLayoutX(40 * i);
        stick.setLayoutY(270 * i);

        // Configuration des contrôles

        BoutonEditerAssociation btnStickHaut = new BoutonEditerAssociation("...", this, 8);
        btnStickHaut.setLayoutX(60 * i);
        btnStickHaut.setLayoutY(255 * i);
        btnStickHaut.setPrefSize(15 * i, 15 * i);
        btnStickHaut.setMaxSize(15 * i, 15 * i);
        btnStickHaut.setMinSize(15 * i, 15 * i);
        btnStickHaut.setFont(new Font(7 * i));
        Tooltip infobulleBtnStickHaut = new Tooltip();
        infobulleBtnStickHaut.setText("Définir l'association liée au mouvement 'haut' du stick");
        btnStickHaut.setTooltip(infobulleBtnStickHaut);

        BoutonEditerAssociation btnStickBas = new BoutonEditerAssociation("...", this, 10);
        btnStickBas.setLayoutX(60 * i);
        btnStickBas.setLayoutY(355 * i);
        btnStickBas.setPrefSize(15 * i, 15 * i);
        btnStickBas.setMaxSize(15 * i, 15 * i);
        btnStickBas.setMinSize(15 * i, 15 * i);
        btnStickBas.setFont(new Font(7 * i));
        Tooltip infobulleBtnStickBas = new Tooltip();
        infobulleBtnStickBas.setText("Définir l'association liée au mouvement 'bas' du stick");
        btnStickBas.setTooltip(infobulleBtnStickBas);

        BoutonEditerAssociation btnStickGauche = new BoutonEditerAssociation("...", this, 11);
        btnStickGauche.setLayoutX(30 * i);
        btnStickGauche.setLayoutY(310 * i);
        btnStickGauche.setPrefSize(15 * i, 15 * i);
        btnStickGauche.setMaxSize(15 * i, 15 * i);
        btnStickGauche.setMinSize(15 * i, 15 * i);
        btnStickGauche.setFont(new Font(7 * i));
        Tooltip infobulleBtnStickGauche = new Tooltip();
        infobulleBtnStickGauche.setText("Définir l'association liée au mouvement 'gauche' du stick");
        btnStickGauche.setTooltip(infobulleBtnStickGauche);

        BoutonEditerAssociation btnStickDroite = new BoutonEditerAssociation("...", this, 9);
        btnStickDroite.setLayoutX(90 * i);
        btnStickDroite.setLayoutY(310 * i);
        btnStickDroite.setPrefSize(15 * i, 15 * i);
        btnStickDroite.setMaxSize(15 * i, 15 * i);
        btnStickDroite.setMinSize(15 * i, 15 * i);
        btnStickDroite.setFont(new Font(7 * i));
        Tooltip infobulleBtnStickDroite = new Tooltip();
        infobulleBtnStickDroite.setText("Définir l'association liée au mouvement 'droite' du stick");
        btnStickDroite.setTooltip(infobulleBtnStickDroite);

        BoutonEditerAssociation btnStart = new BoutonEditerAssociation("...", this, 0);
        btnStart.setLayoutX(136 * i);
        btnStart.setLayoutY(350 * i);
        btnStart.setPrefSize(15 * i, 15 * i);
        btnStart.setMaxSize(15 * i, 15 * i);
        btnStart.setMinSize(15 * i, 15 * i);
        btnStart.setFont(new Font(7 * i));
        Tooltip infobulleBtnStart = new Tooltip();
        infobulleBtnStart.setText("Définir l'association liée au bouton START du Pad");
        btnStart.setTooltip(infobulleBtnStart);

        BoutonEditerAssociation btnSelect = new BoutonEditerAssociation("...", this, 1);
        btnSelect.setLayoutX(168 * i);
        btnSelect.setLayoutY(350 * i);
        btnSelect.setPrefSize(15 * i, 15 * i);
        btnSelect.setMaxSize(15 * i, 15 * i);
        btnSelect.setMinSize(15 * i, 15 * i);
        btnSelect.setFont(new Font(7 * i));
        Tooltip infobulleBtnSelect = new Tooltip();
        infobulleBtnSelect.setText("Définir l'association liée au bouton SELECT du Pad");
        btnSelect.setTooltip(infobulleBtnSelect);

        BoutonEditerAssociation btnA = new BoutonEditerAssociation("...", this, 2);
        btnA.setLayoutX(226 * i);
        btnA.setLayoutY(270 * i);
        btnA.setPrefSize(15 * i, 15 * i);
        btnA.setMaxSize(15 * i, 15 * i);
        btnA.setMinSize(15 * i, 15 * i);
        btnA.setFont(new Font(7 * i));
        Tooltip infobulleBtnA = new Tooltip();
        infobulleBtnA.setText("Définir l'association liée au bouton d'action 1 du Pad");
        btnA.setTooltip(infobulleBtnA);

        BoutonEditerAssociation btnB = new BoutonEditerAssociation("...", this, 3);
        btnB.setLayoutX(256 * i);
        btnB.setLayoutY(260 * i);
        btnB.setPrefSize(15 * i, 15 * i);
        btnB.setMaxSize(15 * i, 15 * i);
        btnB.setMinSize(15 * i, 15 * i);
        btnB.setFont(new Font(7 * i));
        Tooltip infobulleBtnB = new Tooltip();
        infobulleBtnB.setText("Définir l'association liée au bouton d'action 2 du Pad");
        btnB.setTooltip(infobulleBtnB);

        BoutonEditerAssociation btnC = new BoutonEditerAssociation("...", this, 4);
        btnC.setLayoutX(286 * i);
        btnC.setLayoutY(250 * i);
        btnC.setPrefSize(15 * i, 15 * i);
        btnC.setMaxSize(15 * i, 15 * i);
        btnC.setMinSize(15 * i, 15 * i);
        btnC.setFont(new Font(7 * i));
        Tooltip infobulleBtnC = new Tooltip();
        infobulleBtnC.setText("Définir l'association liée au bouton d'action 3 du Pad");
        btnC.setTooltip(infobulleBtnC);

        BoutonEditerAssociation btnD = new BoutonEditerAssociation("...", this, 5);
        btnD.setLayoutX(226 * i);
        btnD.setLayoutY(350 * i);
        btnD.setPrefSize(15 * i, 15 * i);
        btnD.setMaxSize(15 * i, 15 * i);
        btnD.setMinSize(15 * i, 15 * i);
        btnD.setFont(new Font(7 * i));
        Tooltip infobulleBtnD = new Tooltip();
        infobulleBtnD.setText("Définir l'association liée au bouton d'action 4 du Pad");
        btnD.setTooltip(infobulleBtnD);

        BoutonEditerAssociation btnE = new BoutonEditerAssociation("...", this, 6);
        btnE.setLayoutX(256 * i);
        btnE.setLayoutY(335 * i);
        btnE.setPrefSize(15 * i, 15 * i);
        btnE.setMaxSize(15 * i, 15 * i);
        btnE.setMinSize(15 * i, 15 * i);
        btnE.setFont(new Font(7 * i));
        Tooltip infobulleBtnE = new Tooltip();
        infobulleBtnE.setText("Définir l'association liée au bouton d'action 5 du Pad");
        btnE.setTooltip(infobulleBtnE);

        BoutonEditerAssociation btnF = new BoutonEditerAssociation("...", this, 7);
        btnF.setLayoutX(286 * i);
        btnF.setLayoutY(325 * i);
        btnF.setPrefSize(15 * i, 15 * i);
        btnF.setMaxSize(15 * i, 15 * i);
        btnF.setMinSize(15 * i, 15 * i);
        btnF.setFont(new Font(7 * i));
        Tooltip infobulleBtnF = new Tooltip();
        infobulleBtnF.setText("Définir l'association liée au bouton d'action 6 du Pad");
        btnF.setTooltip(infobulleBtnF);

        Button btnAnnulerModifs = new Button("Annuler", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnAnnulerModifs.setLayoutX(220 * i);
        btnAnnulerModifs.setLayoutY(385 * i);
        btnAnnulerModifs.setPrefSize(50 * i, 25 * i);
        btnAnnulerModifs.setMaxSize(50 * i, 25 * i);
        btnAnnulerModifs.setMinSize(50 * i, 25 * i);
        btnAnnulerModifs.setFont(new Font(7 * i));

        Button btnModifier = new Button("Enregistrer", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnModifier.setLayoutX(280 * i);
        btnModifier.setLayoutY(385 * i);
        btnModifier.setPrefSize(70 * i, 25 * i);
        btnModifier.setMaxSize(70 * i, 25 * i);
        btnModifier.setMinSize(70 * i, 25 * i);
        btnModifier.setFont(new Font(7 * i));

        this.textFieldNomProfil = new TextField();
        this.textFieldNomProfil.setLayoutX(120 * i);
        this.textFieldNomProfil.setLayoutY(7 * i);
        this.textFieldNomProfil.setPrefSize(230 * i, 20 * i);
        this.textFieldNomProfil.setMaxSize(230 * i, 20 * i);
        this.textFieldNomProfil.setMinSize(230 * i, 20 * i);
        this.textFieldNomProfil.setStyle("-fx-font-size: " + Double.toString(9 * i));
        this.textFieldNomProfil.setText(profilAModifier.getNom());

        TextField textFieldNomMachine = new TextField();
        textFieldNomMachine.setLayoutX(120 * i);
        textFieldNomMachine.setLayoutY(30 * i);
        textFieldNomMachine.setPrefSize(230 * i, 20 * i);
        textFieldNomMachine.setMaxSize(230 * i, 20 * i);
        textFieldNomMachine.setMinSize(230 * i, 20 * i);
        textFieldNomMachine.setStyle("-fx-font-size: " + Double.toString(9 * i));
        textFieldNomMachine.setDisable(true);
        textFieldNomMachine.setText(machineDuProfil.getNom());

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(texteNomProfil);
        root.getChildren().add(texteNomMachine);
        root.getChildren().add(texteManette);
        root.getChildren().add(zoneImgManette);
        root.getChildren().add(textePad);
        root.getChildren().add(zoneImgPad);
        root.getChildren().add(btnPad1);
        root.getChildren().add(btnPad2);
        root.getChildren().add(btnPad3);
        root.getChildren().add(btnPad4);
        root.getChildren().add(btnPad5);
        root.getChildren().add(btnPad6);
        root.getChildren().add(btnPad7);
        root.getChildren().add(btnPad8);
        root.getChildren().add(stick);
        root.getChildren().add(btnStickHaut);
        root.getChildren().add(btnStickBas);
        root.getChildren().add(btnStickGauche);
        root.getChildren().add(btnStickDroite);
        root.getChildren().add(btnStart);
        root.getChildren().add(btnSelect);
        root.getChildren().add(btnA);
        root.getChildren().add(btnB);
        root.getChildren().add(btnC);
        root.getChildren().add(btnD);
        root.getChildren().add(btnE);
        root.getChildren().add(btnF);
        root.getChildren().add(this.textFieldNomProfil);
        root.getChildren().add(textFieldNomMachine);
        root.getChildren().add(btnModifier);
        root.getChildren().add(btnAnnulerModifs);
        this.setScene(new Scene(root, 350 * i, 410 * i, Color.gray(0.85)));

        this.textFieldNomProfil.setOnKeyPressed(new EventHandler<KeyEvent>()
        {

            public void handle(KeyEvent event)
            {
                StageEditionProfil.this.profilModifie = true;
            }
        });

        this.setOnCloseRequest(new EventHandler<WindowEvent>()
        {

            public void handle(WindowEvent event)
            {
                fermerFenetre();
                event.consume();
            }
        });

        btnAnnulerModifs.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                fermerFenetre();
            }
        });

        btnModifier.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                StageEditionProfil.this.sauvgarderProfilCourrant();
                StageEditionProfil.this.close();
            }
        });

        this.show();
    }

    public void sauvgarderProfilCourrant()
    {
        this.profilAModifier.setNom(this.textFieldNomProfil.getText());
        for (int x = 0; x < this.associationsModifies.length; x++) {
            if (this.associationsModifies[x].size() != 0) {
                this.profilAModifier.setAssociationsAt(x, this.associationsModifies[x]);
            }
        }
        this.panneauProfilAModifier.rafraichirNomProfil();
        System.err.println("ERR. Action pas encore supportée");
    }

    public void modifierUneAssocDeTouches(int numDuBtn)
    {
        this.profilModifie = true;
        new StageEditionAssociationTouche(dim, this, this.touchesDisponibles, this.profilAModifier.getAssociationsAt(numDuBtn), this.associationsModifies[numDuBtn]);
        System.err.println("ERR. Action pas encore supportée");
    }

    private void fermerFenetre()
    {
        if (this.profilModifie) {
            new StageConfirmationAnnulationEditionProfil(StageEditionProfil.this);
        }
        else {
            this.close();
        }
    }

    /**
     * @return the dim
     */
    public double getDim()
    {
        return dim;
    }


}
