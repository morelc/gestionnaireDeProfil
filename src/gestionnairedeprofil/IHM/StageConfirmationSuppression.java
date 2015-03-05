/* Gestionnaire de profil
 * Programme used to manage all profils from a database and send them
 * to a SD Card
 * Copyright (C) 2014-2015 MOREL Charles
 * See COPYING for Details
 * 
 * This file is part of Gestionnaire de profil.
 *
 * Gestionnaire de profil is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gestionnaire de profil is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
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
public class StageConfirmationSuppression extends Stage
{

    public StageConfirmationSuppression(double i, final PanneauProfilDisponible profilConcerne, Stage stageParent, String nomProfilASupprimer, String nomMachineDuProfil)
    {
        // configuration des dépendances
        this.setTitle("Suppression du profil");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png")));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques

        Text texteDanger = new Text();
        texteDanger.setLayoutX(70 * i);
        texteDanger.setLayoutY(30 * i);
        texteDanger.setFont(new Font(15 * i));
        texteDanger.setFill(Color.web("#696969", 1.0));
        texteDanger.setTextAlignment(TextAlignment.CENTER);

        String nomProfilAAfiicher;
        if (nomProfilASupprimer.length() > 20) {
            nomProfilAAfiicher = nomProfilASupprimer.substring(0, 20) + "...";
        }
        else {
            nomProfilAAfiicher = nomProfilASupprimer;
        }
        String nomMachineAAfiicher;
        if (nomMachineDuProfil.length() > 16) {
            nomMachineAAfiicher = nomMachineDuProfil.substring(0, 16) + "...";
        }
        else {
            nomMachineAAfiicher = nomMachineDuProfil;
        }

        texteDanger.setText("Attention, la suppression d'un profil est définitif."
                + "\nSouhaitez-vous vraiment supprimer le profil\n" + nomProfilAAfiicher + " (" + nomMachineAAfiicher + ") ?");

        ImageView fondDanger = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/warning.png")));
        fondDanger.setFitHeight(185 * i);
        fondDanger.setPreserveRatio(true);
        fondDanger.setLayoutX(-15 * i);
        fondDanger.setLayoutY(-10 * i);

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
        root.getChildren().add(fondDanger);
        root.getChildren().add(texteDanger);
        root.getChildren().add(btnOk);
        root.getChildren().add(btnNon);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));

        btnOk.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                profilConcerne.supprimerProfil();
                StageConfirmationSuppression.this.close();
            }
        });

        btnNon.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageConfirmationSuppression.this.close();
            }
        });

        this.show();
    }

}
