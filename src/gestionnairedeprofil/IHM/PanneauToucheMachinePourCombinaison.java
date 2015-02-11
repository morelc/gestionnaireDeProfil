/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnairedeprofil.IHM;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author MOREL Charles
 */
public class PanneauToucheMachinePourCombinaison extends AnchorPane
{

    ComboBox bouttonAAppuyer;

    public PanneauToucheMachinePourCombinaison(double dim)
    {
        this.bouttonAAppuyer = new ComboBox();
        this.bouttonAAppuyer.setMaxSize(80 * dim, 20 * dim);
        this.bouttonAAppuyer.setMinSize(80 * dim, 20 * dim);
        this.bouttonAAppuyer.setPrefSize(80 * dim, 20 * dim);
        this.getChildren().add(this.bouttonAAppuyer);

        Button btnEnlever = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png"))));
        btnEnlever.setLayoutX(85 * dim);
        btnEnlever.setLayoutY(0);
        btnEnlever.setMaxSize(20 * dim, 20 * dim);
        btnEnlever.setMinSize(20 * dim, 20 * dim);
        btnEnlever.setPrefSize(20 * dim, 20 * dim);
        Tooltip infobulleEnlever = new Tooltip();
        infobulleEnlever.setText("Enlever cette touche de la combinaison");
        btnEnlever.setTooltip(infobulleEnlever);
        this.getChildren().add(btnEnlever);

    }
}
