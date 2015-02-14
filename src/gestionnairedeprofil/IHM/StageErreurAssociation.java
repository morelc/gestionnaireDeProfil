package gestionnairedeprofil.IHM;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class StageErreurAssociation extends Stage
{

    public StageErreurAssociation(double i, StageEditionAssociationTouche stageParent, String messageComplementaireErreur)
    {
        // configuration des dépendances
        this.setTitle("Erreur dans l'assotiation");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png")));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques

        Text texteErreur = new Text();
        texteErreur.setLayoutX(125 * i);
        texteErreur.setLayoutY(30 * i);
        texteErreur.setFont(new Font(15 * i));
        texteErreur.setFill(Color.web("#696969", 1.0));
        texteErreur.setTextAlignment(TextAlignment.CENTER);
        texteErreur.setText("Erreur:"
                + "\nVotre association est impossible car\n" + messageComplementaireErreur);

        ImageView fondErreur = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/info.png")));
        fondErreur.setFitHeight(150 * i);
        fondErreur.setPreserveRatio(true);
        fondErreur.setLayoutX(-20 * i);
        fondErreur.setLayoutY(25 * i);

        // Configuration des contrôles

        Button btnOk = new Button("Ok", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(325 * i);
        btnOk.setLayoutY(100 * i);
        btnOk.setPrefSize(50 * i, 25 * i);
        btnOk.setMaxSize(50 * i, 25 * i);
        btnOk.setMinSize(50 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));


        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fondErreur);
        root.getChildren().add(texteErreur);
        root.getChildren().add(btnOk);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                StageErreurAssociation.this.close();
            }
        });

        this.show();
    }

}
