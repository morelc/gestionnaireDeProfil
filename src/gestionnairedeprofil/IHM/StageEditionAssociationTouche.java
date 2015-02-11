package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;
import gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
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

/**
 *
 * @author MOREL Charles
 */
public class StageEditionAssociationTouche extends Stage
{

    private InterfaceEditionAssociation panneauEditionAssocCourrant;
    private ArrayList<Association> associationsAuDepart;
    private final ComboBox cbTypeAssoc;
    private double dim;


    public StageEditionAssociationTouche(final double i, final StageEditionProfil profilConcerne, final ArrayList<ToucheMachine> touchesDisponibles, ArrayList<Association> associationsDeBase, ArrayList<Association> associationsModifies)
    {
        // configuration des dépendances
        this.setTitle("Edition de l'association");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/edit.png")));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(profilConcerne);
        this.setResizable(false);
        this.associationsAuDepart = associationsDeBase;
        this.dim = i;

        // Configuration des noeuds statiques

        Text texteTypeAssoc = new Text();
        texteTypeAssoc.setLayoutX(15 * i);
        texteTypeAssoc.setLayoutY(20 * i);
        texteTypeAssoc.setFont(new Font(12 * i));
        texteTypeAssoc.setFill(Color.web("#696969", 1.0));
        texteTypeAssoc.setTextAlignment(TextAlignment.RIGHT);
        texteTypeAssoc.setText("Type d'association:");

        // Configuration des contrôles

        Button btnAnnulerModifs = new Button("Annuler", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnAnnulerModifs.setLayoutX(200 * i);
        btnAnnulerModifs.setLayoutY(315 * i);
        btnAnnulerModifs.setPrefSize(50 * i, 25 * i);
        btnAnnulerModifs.setMaxSize(50 * i, 25 * i);
        btnAnnulerModifs.setMinSize(50 * i, 25 * i);
        btnAnnulerModifs.setFont(new Font(7 * i));

        Button btnModifier = new Button("Modifier", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnModifier.setLayoutX(260 * i);
        btnModifier.setLayoutY(315 * i);
        btnModifier.setPrefSize(55 * i, 25 * i);
        btnModifier.setMaxSize(55 * i, 25 * i);
        btnModifier.setMinSize(55 * i, 25 * i);
        btnModifier.setFont(new Font(7 * i));

        this.cbTypeAssoc = new ComboBox();
        this.cbTypeAssoc.setLayoutX(120 * i);
        this.cbTypeAssoc.setLayoutY(7 * i);
        this.cbTypeAssoc.setPrefSize(200 * i, 20 * i);
        this.cbTypeAssoc.setMaxSize(200 * i, 20 * i);
        this.cbTypeAssoc.setMinSize(200 * i, 20 * i);
        this.cbTypeAssoc.getItems().addAll("(aucune action)", "Appui simple", "Combinaison", "Autofire", "Macro");
        if (associationsDeBase.get(0).getId() == Association.ID_TOUCHE_NON_ASSOCIEE
                || associationsDeBase.get(0).getTouches().get(0).getId() == ToucheMachine.ID_TOUCHE_NON_ASSOCIEE) {
            this.cbTypeAssoc.getSelectionModel().select(0);
        }
        else {
            if (associationsDeBase.size() == 1) {
                this.cbTypeAssoc.getSelectionModel().select(1);
                if (associationsDeBase.get(0).getTouches().size() > 1) {
                    this.cbTypeAssoc.getSelectionModel().select(2);
                }
                if (associationsDeBase.get(0).isEstAutofire()) {
                    this.cbTypeAssoc.getSelectionModel().select(3);
                }
            }
            else {
                this.cbTypeAssoc.getSelectionModel().select(4);
            }
        }

        // Configuration des sous-panneaux

        final ScrollPane panneauAssociation = new ScrollPane();
        panneauAssociation.setLayoutX(10 * i);
        panneauAssociation.setLayoutY(50 * i);
        panneauAssociation.setPrefSize(303 * i, 250 * i);
        panneauAssociation.setMaxSize(303 * i, 250 * i);
        panneauAssociation.setMinSize(303 * i, 250 * i);
        panneauAssociation.setStyle("-fx-background-color: #D8D8D8; -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 1, 0.0 , 0 , 1 );");

        changerPanneauEdition(panneauAssociation, touchesDisponibles, false);

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(texteTypeAssoc);
        root.getChildren().add(cbTypeAssoc);
        root.getChildren().add(btnModifier);
        root.getChildren().add(btnAnnulerModifs);
        root.getChildren().add(panneauAssociation);
        this.setScene(new Scene(root, 320 * i, 340 * i, Color.gray(0.85)));

        cbTypeAssoc.valueProperty().addListener(new ChangeListener()
        {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                changerPanneauEdition(panneauAssociation, touchesDisponibles, true);
            }
        });

        this.setOnCloseRequest(new EventHandler<WindowEvent>()
        {

            public void handle(WindowEvent event)
            {
                event.consume();
            }
        });

        btnAnnulerModifs.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                StageEditionAssociationTouche.this.close();
            }
        });

        btnModifier.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event)
            {
                StageEditionAssociationTouche.this.close();
            }
        });

        this.show();
    }

    private void changerPanneauEdition(ScrollPane panneauAssociation, ArrayList<ToucheMachine> touchesDisponibles, boolean associationModifiee)
    {
        ArrayList<Association> associationsAModifier;
        if (!associationModifiee) {
            associationsAModifier = this.associationsAuDepart;
        }
        else {
            associationsAModifier = new ArrayList();
            associationsAModifier.add(new Association());
        }
        switch (this.cbTypeAssoc.getSelectionModel().getSelectedIndex()) {
            case 1:
                this.panneauEditionAssocCourrant = new PanneauEditionToucheSimple(dim, touchesDisponibles, associationsAModifier);
                break;
            case 2:
                this.panneauEditionAssocCourrant = new PanneauEditionCombinaison(dim);
                break;
            default:
                this.panneauEditionAssocCourrant = new PanneauEditionVide(dim);
                break;
        }
        panneauAssociation.setContent((AnchorPane) this.panneauEditionAssocCourrant);
    }
}
