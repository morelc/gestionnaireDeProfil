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
public class PanneauConfirmationAnnulationEditionProfil extends Stage
{

    public PanneauConfirmationAnnulationEditionProfil(final PanneauEditionProfil profilConcerne)
    {
        // configuration des dépendances
        this.setTitle("Annuler les changements");
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(profilConcerne);
        this.setResizable(false);
        double i = profilConcerne.getDim();

        // Configuration des noeuds statiques

        Text texteDanger = new Text();
        texteDanger.setLayoutX(60 * i);
        texteDanger.setLayoutY(30 * i);
        texteDanger.setFont(new Font(15 * i));
        texteDanger.setFill(Color.web("#696969", 1.0));
        texteDanger.setTextAlignment(TextAlignment.CENTER);
        texteDanger.setText("Attention, vous avez effectué des changements sur ce profil."
                + "\nTout changement non sauvgardé est perdu."
                + "\nSouhaitez-vous vraiment quitter l'édition ?");

        ImageView fond_Danger = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/warning.png")));
        fond_Danger.setFitHeight(185 * i);
        fond_Danger.setPreserveRatio(true);
        fond_Danger.setLayoutX(-15 * i);
        fond_Danger.setLayoutY(-10 * i);

        // Configuration des contrôles

        Button btnOk = new Button("Oui", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnOk.setLayoutX(170 * i);
        btnOk.setLayoutY(100 * i);
        btnOk.setPrefSize(50 * i, 25 * i);
        btnOk.setMaxSize(50 * i, 25 * i);
        btnOk.setMinSize(50 * i, 25 * i);
        btnOk.setFont(new Font(7 * i));

        Button btnNon = new Button("Non", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png"))));
        btnNon.setLayoutX(250 * i);
        btnNon.setLayoutY(100 * i);
        btnNon.setPrefSize(50 * i, 25 * i);
        btnNon.setMaxSize(50 * i, 25 * i);
        btnNon.setMinSize(50 * i, 25 * i);
        btnNon.setFont(new Font(7 * i));
       

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fond_Danger);
        root.getChildren().add(texteDanger);
        root.getChildren().add(btnOk);
        root.getChildren().add(btnNon);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                profilConcerne.close();
            }
        });

        btnNon.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                PanneauConfirmationAnnulationEditionProfil.this.close();
            }
        });

        this.show();
    }

}
