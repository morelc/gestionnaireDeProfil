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

import gestionnairedeprofil.donnees.structures.Association;
import gestionnairedeprofil.donnees.structures.Machine;
import gestionnairedeprofil.donnees.structures.Profil;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
public class StageCreationProfil extends Stage
{

    public StageCreationProfil(final double i, Stage stageParent, ArrayList<Machine> machinesDisponibles, final Accordion panneauxDesProfils)
    {
        // configuration des dépendances
        this.setTitle("Création d'un profil");
        this.getIcons().add(new Image(getClass().getResourceAsStream("ressourcesGraphiques/add.png")));
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(stageParent);
        this.setResizable(false);

        // Configuration des noeuds statiques

        Text texteNomProfil = new Text();
        texteNomProfil.setLayoutX(110 * i);
        texteNomProfil.setLayoutY(50 * i);
        texteNomProfil.setFont(new Font(15 * i));
        texteNomProfil.setFill(Color.web("#696969", 1.0));
        texteNomProfil.setTextAlignment(TextAlignment.RIGHT);
        texteNomProfil.setText("Nom du profil:");

        Text texteNomMachine = new Text();
        texteNomMachine.setLayoutX(100 * i);
        texteNomMachine.setLayoutY(90 * i);
        texteNomMachine.setFont(new Font(15 * i));
        texteNomMachine.setFill(Color.web("#696969", 1.0));
        texteNomMachine.setTextAlignment(TextAlignment.RIGHT);
        texteNomMachine.setText("Machine associée:");

        ImageView fondCreation = new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/create.png")));
        fondCreation.setFitHeight(100 * i);
        fondCreation.setPreserveRatio(true);
        fondCreation.setLayoutX(10 * i);
        fondCreation.setLayoutY(20 * i);

        // Configuration des contrôles

        Button btnNon = new Button("Annuler", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/cancel.png"))));
        btnNon.setLayoutX(220 * i);
        btnNon.setLayoutY(110 * i);
        btnNon.setPrefSize(50 * i, 25 * i);
        btnNon.setMaxSize(50 * i, 25 * i);
        btnNon.setMinSize(50 * i, 25 * i);
        btnNon.setFont(new Font(7 * i));

        Button btnCreer = new Button("Créer", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/ok.png"))));
        btnCreer.setLayoutX(300 * i);
        btnCreer.setLayoutY(110 * i);
        btnCreer.setPrefSize(50 * i, 25 * i);
        btnCreer.setMaxSize(50 * i, 25 * i);
        btnCreer.setMinSize(50 * i, 25 * i);
        btnCreer.setFont(new Font(7 * i));

        final TextField textAreaNomProfil = new TextField();
        textAreaNomProfil.setLayoutX(235 * i);
        textAreaNomProfil.setLayoutY(32 * i);
        textAreaNomProfil.setPrefSize(110 * i, 25 * i);
        textAreaNomProfil.setMaxSize(110 * i, 25 * i);
        textAreaNomProfil.setMinSize(110 * i, 25 * i);
        textAreaNomProfil.setStyle("-fx-font-size: " + Double.toString(11 * i));

        final ComboBox cbSelectionMachine = new ComboBox();
        cbSelectionMachine.setLayoutX(235 * i);
        cbSelectionMachine.setLayoutY(72 * i);
        cbSelectionMachine.setPrefSize(110 * i, 25 * i);
        cbSelectionMachine.setMaxSize(110 * i, 25 * i);
        cbSelectionMachine.setMinSize(110 * i, 25 * i);
        for (Machine machineATraiter : machinesDisponibles) {
            cbSelectionMachine.getItems().add(machineATraiter);
        }
        cbSelectionMachine.setValue(cbSelectionMachine.getItems().get(0));

        // Configuration du noeud racine Root et de Scene
        Group root = new Group();
        root.getChildren().add(fondCreation);
        root.getChildren().add(texteNomProfil);
        root.getChildren().add(texteNomMachine);
        root.getChildren().add(textAreaNomProfil);
        root.getChildren().add(cbSelectionMachine);
        root.getChildren().add(btnCreer);
        root.getChildren().add(btnNon);
        this.setScene(new Scene(root, 400 * i, 150 * i, Color.gray(0.85)));


        btnCreer.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                if (textAreaNomProfil.getText().length() == 0 || textAreaNomProfil.getText().length() >= 50) {
                    new StageMessageErreur(i, StageCreationProfil.this, "Veuillez entrer un nom\nde profil valide.");
                    event.consume();
                }
                else {
                    ListeProfilsDisponiblesMachine panneauDeLaMachine = (ListeProfilsDisponiblesMachine) panneauxDesProfils.getChildrenUnmodifiable().get(0);
                    Machine machineSelectionnee = (Machine) cbSelectionMachine.getValue();
                    for (Node panneauAScanner : panneauxDesProfils.getChildrenUnmodifiable()) {
                        ListeProfilsDisponiblesMachine panneauAScannerCaste = (ListeProfilsDisponiblesMachine) panneauAScanner;
                        if (panneauAScannerCaste.getText() == machineSelectionnee.getNom()) {
                            panneauDeLaMachine = panneauAScannerCaste;
                        }
                    }
                    lancerAjoutSurBD(machineSelectionnee, textAreaNomProfil.getText(), panneauDeLaMachine);
                    StageCreationProfil.this.close();
                }
            }
        });

        btnNon.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                StageCreationProfil.this.close();
            }
        });

        this.show();
    }

    private void lancerAjoutSurBD(Machine machineSelectionee, String nomProfil, ListeProfilsDisponiblesMachine panneauMachine)
    {
        System.err.println("FN lancerAjoutSurBD() NON IMPLEMENTEE");

        // ici la fonction de création 
        Profil nvProfil = new Profil(-1, nomProfil);
        for (int x = 0; x < 12; x++) {
            nvProfil.getAssociationsAt(x).add(new Association());
        }

        machineSelectionee.ajouterProfil(nvProfil);
        panneauMachine.ajouterProfil(nvProfil);
        panneauMachine.expandedProperty().setValue(true);
    }

}
