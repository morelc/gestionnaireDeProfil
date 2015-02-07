package gestionnairedeprofil.IHM;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
public class PanneauProfilDisponible extends Pane
{

    private ListeProfilsDisponiblesMachine listeDesProfilsDeLaMachine;

    public PanneauProfilDisponible(final double dim, final ListeProfilsDisponiblesMachine listeOuAjouterLeProfil, final ListeProfilsAEnvoyer listeDesProfilsAEnvoyer, final String nomDuProfil, final int numDuProfil)
    {
        this.listeDesProfilsDeLaMachine = listeOuAjouterLeProfil;

        Label nomProfilAAfficher = new Label(nomDuProfil);
        nomProfilAAfficher.setLayoutX(5 * dim);
        nomProfilAAfficher.setLayoutY(3 * dim);
        nomProfilAAfficher.setPrefWidth(105 * dim);
        nomProfilAAfficher.setMinWidth(105 * dim);
        nomProfilAAfficher.setMaxWidth(105 * dim);
        nomProfilAAfficher.setFont(new Font(7 * dim));
        this.getChildren().add(nomProfilAAfficher);

        final Button btnEditer = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/edit.png"))));
        btnEditer.setLayoutX(109 * dim);
        btnEditer.setLayoutY(1 * dim);
        btnEditer.setMaxSize(25 * dim, 15 * dim);
        btnEditer.setMinSize(25 * dim, 15 * dim);
        btnEditer.setPrefSize(25 * dim, 15 * dim);
        btnEditer.setOpacity(0);
        Tooltip infobulleBtnEditer = new Tooltip();
        infobulleBtnEditer.setText("Éditer le profil");
        btnEditer.setTooltip(infobulleBtnEditer);
        this.getChildren().add(btnEditer);

        final Button btnSupprimer = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png"))));
        btnSupprimer.setLayoutX(137 * dim);
        btnSupprimer.setLayoutY(1 * dim);
        btnSupprimer.setMaxSize(25 * dim, 15 * dim);
        btnSupprimer.setMinSize(25 * dim, 15 * dim);
        btnSupprimer.setPrefSize(25 * dim, 15 * dim);
        btnSupprimer.setOpacity(0);
        Tooltip infobulleBtnSupprimer = new Tooltip();
        infobulleBtnSupprimer.setText("Supprimer définitivement le profil du PC");
        btnSupprimer.setTooltip(infobulleBtnSupprimer);
        this.getChildren().add(btnSupprimer);

        final Button btnAjouterALaListeDEnvoi = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/right.png"))));
        btnAjouterALaListeDEnvoi.setLayoutX(165 * dim);
        btnAjouterALaListeDEnvoi.setLayoutY(1 * dim);
        btnAjouterALaListeDEnvoi.setMaxSize(25 * dim, 15 * dim);
        btnAjouterALaListeDEnvoi.setMinSize(25 * dim, 15 * dim);
        btnAjouterALaListeDEnvoi.setPrefSize(25 * dim, 15 * dim);
        btnAjouterALaListeDEnvoi.setOpacity(0);
        Tooltip infobulleBtnAjouterALaListeDEnvoi = new Tooltip();
        infobulleBtnAjouterALaListeDEnvoi.setText("Ajouter le profil à la liste d'envoi");
        btnAjouterALaListeDEnvoi.setTooltip(infobulleBtnAjouterALaListeDEnvoi);
        this.getChildren().add(btnAjouterALaListeDEnvoi);

        this.setOnMouseEntered(new EventHandler<MouseEvent>()
        {

            public void handle(MouseEvent t)
            {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(false);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(100), new KeyValue(btnEditer.opacityProperty(), 1)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnSupprimer.opacityProperty(), 1)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnAjouterALaListeDEnvoi.opacityProperty(), 1))
                );
                timeline.play();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>()
        {

            public void handle(MouseEvent t)
            {
                Timeline timeline = new Timeline();
                timeline.setAutoReverse(false);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(100), new KeyValue(btnEditer.opacityProperty(), 0)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnSupprimer.opacityProperty(), 0)),
                        new KeyFrame(Duration.millis(100), new KeyValue(btnAjouterALaListeDEnvoi.opacityProperty(), 0))
                );
                timeline.play();
            }
        });
        btnEditer.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                new PanneauEditionProfil(dim, PanneauProfilDisponible.this, listeOuAjouterLeProfil.getStageDeLApplication(), nomDuProfil, listeOuAjouterLeProfil.getText());
                System.err.println("ATTENTION: L'ACTION N'EST PAS ENCORE DEFINIE!");
            }
        });

        btnSupprimer.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                new PanneauConfirmationSuppression(dim, PanneauProfilDisponible.this, listeOuAjouterLeProfil.getStageDeLApplication(), nomDuProfil, listeOuAjouterLeProfil.getText());
            }
        });
        btnAjouterALaListeDEnvoi.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                new PanneauProfilAEnvoyer(dim, listeDesProfilsAEnvoyer, nomDuProfil + " - " + listeOuAjouterLeProfil.getText(), numDuProfil);
            }
        });
        listeOuAjouterLeProfil.ajouterProfilAEnvoyer(this);
    }

    public void supprimerProfil()
    {
        this.listeDesProfilsDeLaMachine.enleverProfilAEnvoyer(this);
        System.err.println("ATTENTION: L'ACTION N'EST PAS ENCORE DEFINIE!");
    }
}
