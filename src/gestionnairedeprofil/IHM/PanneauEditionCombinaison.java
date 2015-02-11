/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author MOREL Charles
 */
public class PanneauEditionCombinaison extends AnchorPane implements InterfaceEditionAssociation
{

    private ArrayList<PanneauToucheMachinePourCombinaison> cbCombinaison;
    private Button btnAjouterCmb;
    private double dim;


    public PanneauEditionCombinaison(double i)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setPrefSize(303 * i - 2, 250 * i - 2);
        this.setMinSize(303 * i - 2, 250 * i - 2);
        this.setMaxSize(303 * i - 2, 250 * i - 2);
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
        this.cbCombinaison = new ArrayList();
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

        ajouterUnPanneauDeCombinaison();
        ajouterUnPanneauDeCombinaison();

    }

    public void ajouterUnPanneauDeCombinaison()
    {
        PanneauToucheMachinePourCombinaison nouvelleCombinaisonTocuhe = new PanneauToucheMachinePourCombinaison(dim);
        nouvelleCombinaisonTocuhe.setLayoutX(90 * dim);
        nouvelleCombinaisonTocuhe.setLayoutY(111 * dim + (35 * cbCombinaison.size()));
        cbCombinaison.add(nouvelleCombinaisonTocuhe);
        this.getChildren().add(nouvelleCombinaisonTocuhe);
    }

    @Override
    public Association getAssociation()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
