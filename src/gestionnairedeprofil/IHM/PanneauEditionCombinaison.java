/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;
import gestionnairedeprofil.donnees.structures.AssociationsDansProfil;
import gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author MOREL Charles
 */
public class PanneauEditionCombinaison extends AnchorPane implements InterfaceEditionAssociation
{

    public final static String MESSAGE_INVALIDITE = "au moins deux touches différentes\ndoivent être selectionnées!";
    private ArrayList<PanneauToucheMachinePourCombinaison> combinaison;
    private ArrayList<ToucheMachine> touchesDisponiblesAffichables;
    private Button btnAjouterCmb;
    private double dim;


    public PanneauEditionCombinaison(double i, ArrayList<ToucheMachine> touchesDisponibles, AssociationsDansProfil associationsDeBase)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setMinSize(303 * i - 2, 250 * i - 2);
        this.touchesDisponiblesAffichables = touchesDisponibles;
        this.dim = i;

        // Configuration des noeuds statiques
        Text textecombinaison = new Text();
        textecombinaison.setLayoutX(75 * i);
        textecombinaison.setLayoutY(75 * i);
        textecombinaison.setFont(new Font(15 * i));
        textecombinaison.setFill(Color.web("#696969", 1.0));
        textecombinaison.setTextAlignment(TextAlignment.CENTER);
        textecombinaison.setText("Appui simultané sur\nles touches:");
        this.getChildren().add(textecombinaison);

        // Configuration des contrôles
        this.combinaison = new ArrayList();
        this.btnAjouterCmb = new Button("Ajouter...", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/add.png"))));
        this.btnAjouterCmb.setLayoutX(90 * dim);
        this.btnAjouterCmb.setLayoutY(155 * dim);
        this.btnAjouterCmb.setMaxSize(105 * dim, 20 * dim);
        this.btnAjouterCmb.setMinSize(105 * dim, 20 * dim);
        this.btnAjouterCmb.setPrefSize(105 * dim, 20 * dim);
        Tooltip infobulleBtnAjouterCmb = new Tooltip();
        infobulleBtnAjouterCmb.setText("Ajouter une nouvelle touche à la combinaison");
        this.btnAjouterCmb.setTooltip(infobulleBtnAjouterCmb);
        this.getChildren().add(this.btnAjouterCmb);

        if (associationsDeBase.getAssocType() == AssociationsDansProfil.TYPE_VIDE) {
            ajouterUnPanneauDeCombinaison(new ToucheMachine());
            ajouterUnPanneauDeCombinaison(new ToucheMachine());
        }
        else {
            for (ToucheMachine toucheAAjouter : associationsDeBase.get(0).getTouches()) {
                ajouterUnPanneauDeCombinaison(toucheAAjouter);
            }
        }


        this.btnAjouterCmb.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                ajouterUnPanneauDeCombinaison(new ToucheMachine());
            }
        });

    }

    public void ajouterUnPanneauDeCombinaison(ToucheMachine toucheAAjouter)
    {
        PanneauToucheMachinePourCombinaison nouvelleCombinaisonTocuhe = new PanneauToucheMachinePourCombinaison(dim, this, toucheAAjouter);
        nouvelleCombinaisonTocuhe.setLayoutX(90 * dim);
        nouvelleCombinaisonTocuhe.setLayoutY(111 * dim + (35 * this.combinaison.size()));
        nouvelleCombinaisonTocuhe.setOpacity(0);
        combinaison.add(nouvelleCombinaisonTocuhe);
        reorganiserListeAssociations();
        Timeline timelinePanneauAAjoyter = new Timeline();
        timelinePanneauAAjoyter.setAutoReverse(false);
        timelinePanneauAAjoyter.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(nouvelleCombinaisonTocuhe.opacityProperty(), 1)));
        timelinePanneauAAjoyter.play();
        this.getChildren().add(nouvelleCombinaisonTocuhe);

    }

    public void enleverUneAssociation(final PanneauToucheMachinePourCombinaison assocAEnlever)
    {
        EventHandler<ActionEvent> enleverLeProfilDeLaListe = new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent arg0)
            {
                PanneauEditionCombinaison.this.getChildren().remove(assocAEnlever);
                PanneauEditionCombinaison.this.combinaison.remove(assocAEnlever);
                reorganiserListeAssociations();
            }
        };
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(150), new KeyValue(assocAEnlever.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(150), enleverLeProfilDeLaListe));
        timeline.play();
    }

    private void reorganiserListeAssociations()
    {
        for (int i = 0; i < this.combinaison.size(); i++) {
            replacerElement(this.combinaison.get(i), i);
        }
        Timeline timelineBtnAjouter = new Timeline();
        timelineBtnAjouter.setAutoReverse(false);
        timelineBtnAjouter.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(this.btnAjouterCmb.layoutYProperty(), 111 * dim + (35 * this.combinaison.size()))));
        timelineBtnAjouter.play();
    }

    private void replacerElement(PanneauToucheMachinePourCombinaison panneauAReplacer, int idEmplacementDansCollection)
    {
        double emplacementYLogique = 111 * dim + (35 * idEmplacementDansCollection);
        double emplacementYActuel = panneauAReplacer.getLayoutY();
        if (emplacementYLogique != emplacementYActuel) {
            Timeline timeline = new Timeline();
            timeline.setAutoReverse(false);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new KeyValue(panneauAReplacer.layoutYProperty(), emplacementYLogique)));
            timeline.play();
        }
        if (!uneAssociationPeutEtreEnlevee()) {
            panneauAReplacer.desactiverBtnSupprimer();
        }
        else {
            panneauAReplacer.activerBtnSupprimer();
        }
    }

    private boolean uneAssociationPeutEtreEnlevee()
    {
        return (this.combinaison.size() > 2);
    }

    /**
     * @return the touchesDisponiblesAffichables
     */
    public ArrayList<ToucheMachine> getTouchesDisponiblesAffichables()
    {
        return touchesDisponiblesAffichables;
    }

    @Override
    public Association getAssociation()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean associationValide()
    {
        int nbTouchesValides = 0;
        ArrayList idTouchesAppuyees = new ArrayList();
        for (PanneauToucheMachinePourCombinaison toucheSelectionnee : this.combinaison) {
            if(!idTouchesAppuyees.contains(toucheSelectionnee.getToucheCourrante().getId()))
                idTouchesAppuyees.add(toucheSelectionnee.getToucheCourrante().getId());
            if (toucheSelectionnee.getToucheCourrante().getId() != ToucheMachine.ID_TOUCHE_NON_ASSOCIEE) {
                nbTouchesValides++;
            }
        }
        if (nbTouchesValides < 2 || idTouchesAppuyees.size() < 2) {
            return false;
        }
        return true;
    }

    @Override
    public String getMessageDInvalidite()
    {
        return PanneauEditionCombinaison.MESSAGE_INVALIDITE;
    }
}
