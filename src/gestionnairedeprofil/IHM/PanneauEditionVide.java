package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;
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
public class PanneauEditionVide extends AnchorPane implements InterfaceEditionAssociation
{

    public PanneauEditionVide(double i)
    {
        // Configuration de l'apparence
        this.setStyle("-fx-background-color: #D8D8D8;");
        this.setPrefSize(303 * i - 2, 250 * i - 2);
        this.setMinSize(303 * i - 2, 250 * i - 2);
        this.setMaxSize(303 * i - 2, 250 * i - 2);

        // Configuration des noeuds statiques
        ImageView fondRegarderIci = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/lookHere.png")));
        fondRegarderIci.setFitWidth(100 * i);
        fondRegarderIci.setPreserveRatio(true);
        fondRegarderIci.setLayoutX(195 * i);
        fondRegarderIci.setLayoutY(5 * i);
        this.getChildren().add(fondRegarderIci);
        
        
        Text texteAppuiSimple = new Text();
        texteAppuiSimple.setLayoutX(15 * i);
        texteAppuiSimple.setLayoutY(95 * i);
        texteAppuiSimple.setFont(new Font(15 * i));
        texteAppuiSimple.setFill(Color.web("#696969", 1.0));
        texteAppuiSimple.setTextAlignment(TextAlignment.CENTER);
        texteAppuiSimple.setText("Aucun signal n'est"
                + "\nenvoyé à l'appui de ce bouton!"
                + "\npour changer cet état, choisissez un"
                + "\ntype d'association...");
        this.getChildren().add(texteAppuiSimple);
    }

    @Override
    public Association getAssociation()
    {
        return new Association(1, false, 1);
    }


}
