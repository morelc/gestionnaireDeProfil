package gestionnairedeprofil.IHM;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author MOREL Charles
 */
public class PanneauCreationProfil extends Stage
{

    public PanneauCreationProfil(double i, Stage stageParent)
    {
        // configuration des dépendances
        this.setTitle("Création d'un profil");
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques

        Text texteNomProfil = new Text();
        texteNomProfil.setLayoutX(100 * i);
        texteNomProfil.setLayoutY(50 * i);
        texteNomProfil.setFont(new Font(15 * i));
        texteNomProfil.setFill(Color.web("#696969", 1.0));
        texteNomProfil.setTextAlignment(TextAlignment.RIGHT);
        texteNomProfil.setText("Nom du profil:");

        Text texteNomMachine = new Text();
        texteNomMachine.setLayoutX(100 * i);
        texteNomMachine.setLayoutY(90 * i);
        texteNomMachine.setFont(new Font(15 * i));
        texteNomMachine.setFill(Color.web("#696969", 1.0));
        texteNomMachine.setTextAlignment(TextAlignment.RIGHT);
        texteNomMachine.setText("Machine associée:");

        ImageView fondCreation = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/create.png")));
        fondCreation.setFitHeight(100 * i);
        fondCreation.setPreserveRatio(true);
        fondCreation.setLayoutX(10 * i);
        fondCreation.setLayoutY(20 * i);

        // Configuration des contrôles

        Button btnNon = new Button("Annuler", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnNon.setLayoutX(220 * i);
        btnNon.setLayoutY(110 * i);
        btnNon.setPrefSize(50 * i, 25 * i);
        btnNon.setMaxSize(50 * i, 25 * i);
        btnNon.setMinSize(50 * i, 25 * i);
        btnNon.setFont(new Font(7 * i));

        Button btnCreer = new Button("Créer", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnCreer.setLayoutX(300 * i);
        btnCreer.setLayoutY(110 * i);
        btnCreer.setPrefSize(50 * i, 25 * i);
        btnCreer.setMaxSize(50 * i, 25 * i);
        btnCreer.setMinSize(50 * i, 25 * i);
        btnCreer.setFont(new Font(7 * i));

        TextField textAreaNomProfil = new TextField();
        textAreaNomProfil.setLayoutX(205 * i);
        textAreaNomProfil.setLayoutY(32 * i);
        textAreaNomProfil.setPrefSize(170 * i, 25 * i);
        textAreaNomProfil.setMaxSize(170 * i, 25 * i);
        textAreaNomProfil.setMinSize(170 * i, 25 * i);
        textAreaNomProfil.setStyle("-fx-font-size: " + Double.toString(11 * i));

//        ComboBox cbSelectionMachine = new ComboBox();
//        cbSelectionMachine.setLayoutX(225 * i);
//        cbSelectionMachine.setLayoutY(72 * i);
//        cbSelectionMachine.setPrefSize(100 * i, 25 * i);
//        cbSelectionMachine.setMaxSize(100 * i, 25 * i);
//        cbSelectionMachine.setMinSize(100 * i, 25 * i);
//        cbSelectionMachine.setStyle("-fx-font-size: " + Double.toString(11 * i));

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fondCreation);
        root.getChildren().add(texteNomProfil);
        root.getChildren().add(texteNomMachine);
        root.getChildren().add(textAreaNomProfil);
//        root.getChildren().add(cbSelectionMachine);
        root.getChildren().add(btnCreer);
        root.getChildren().add(btnNon);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));


        btnCreer.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                PanneauCreationProfil.this.close();
                System.err.println("FN NON IMPLEMENTE");
            }
        });

        btnNon.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                PanneauCreationProfil.this.close();
            }
        });

        this.show();
    }

}
