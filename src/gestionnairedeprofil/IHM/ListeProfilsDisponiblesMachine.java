package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Machine;
import gestionnairedeprofil.donnees.structures.Profil;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
class ListeProfilsDisponiblesMachine extends TitledPane
{

    ArrayList<PanneauProfilDisponible> profilsDisponibles;
    private final AnchorPane panneauDesProfilsDisponibles;
    private final double espacementEntreProfils;
    private final Stage stageDeLApplication;

    private final double dim;
    private final Machine machineLieeAuProfil;
    private final ListeProfilsAEnvoyer listeDesProfils;

    public ListeProfilsDisponiblesMachine(double i, Machine machineConcernee, Stage stagePrincipal, ListeProfilsAEnvoyer listeDesProfilsAEnvoyer)
    {
        // Configuration des dépendances
        this.setText(machineConcernee.getNom());
        this.setStyle("-fx-background-color: #E9E9E9; -fx-font-size:" + Double.toString(7 * i) + "; -fx-text-fill: black;");

        this.dim = i;
        this.machineLieeAuProfil = machineConcernee;
        this.listeDesProfils = listeDesProfilsAEnvoyer;

        this.stageDeLApplication = stagePrincipal;
        this.profilsDisponibles = new ArrayList();
        this.espacementEntreProfils = i * 17;
        this.panneauDesProfilsDisponibles = new AnchorPane();

        ScrollPane panneauAScrolling = new ScrollPane();
        panneauAScrolling.setContent(this.panneauDesProfilsDisponibles);
        this.setContent(panneauAScrolling);

        for (Profil profilAAjouter : machineConcernee.getProfils()) {
            ajouterProfil(profilAAjouter);
            //new PanneauProfilDisponible(i, this, listeDesProfilsAEnvoyer, profilAAjouter, machineConcernee);
        }
    }

    public final void ajouterProfil(Profil profilAAjouter)
    {
        new PanneauProfilDisponible(this.dim, this, this.listeDesProfils, profilAAjouter, this.machineLieeAuProfil);
    }

    public void ajouterProfilAEnvoyer(PanneauProfilDisponible panneauAAjouter)
    {
        panneauAAjouter.setLayoutX(0);
        panneauAAjouter.setLayoutY(this.profilsDisponibles.size() * this.espacementEntreProfils);
        panneauAAjouter.setOpacity(0);
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(150), new KeyValue(panneauAAjouter.opacityProperty(), 1)));
        this.panneauDesProfilsDisponibles.getChildren().add(panneauAAjouter);
        this.profilsDisponibles.add(panneauAAjouter);
        timeline.play();
    }

    public void enleverProfilAEnvoyer(final PanneauProfilDisponible panneauAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                ListeProfilsDisponiblesMachine.this.panneauDesProfilsDisponibles.getChildren().remove(panneauAEnlever);
                ListeProfilsDisponiblesMachine.this.profilsDisponibles.remove(panneauAEnlever);
                reorganiserListeProfils();
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(panneauAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    private void reorganiserListeProfils()
    {
        for (int i = 0; i < this.profilsDisponibles.size(); i++) {
            replacerElement(this.profilsDisponibles.get(i), i);
        }
    }

    private void replacerElement(PanneauProfilDisponible panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementYLogique = this.espacementEntreProfils * idEmplacementDansCollection;
        double emplacementYActuel = panneauAReplacer.getLayoutY();
        if (emplacementYLogique != emplacementYActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(panneauAReplacer.layoutYProperty(), emplacementYLogique)));
            timeline.play();
        }
    }

    /**
     * @return the stageDeLApplication
     */
    public Stage getStageDeLApplication()
    {
        return stageDeLApplication;
    }
}
