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
package com.gestionnairedeprofil.IHM;

import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author MOREL Charles
 */
public class PanneauToucheMachinePourCombinaison extends AnchorPane
{

    private final ComboBox bouttonAAppuyer;
    private final Button btnEnlever;

    public PanneauToucheMachinePourCombinaison(double dim, final InterfacePanneauTypeCombinaison panneauParent, ToucheMachine ToucheDeBase)
    {
        this.bouttonAAppuyer = new ComboBox();
        this.bouttonAAppuyer.setMaxSize(80 * dim, 20 * dim);
        this.bouttonAAppuyer.setMinSize(80 * dim, 20 * dim);
        this.bouttonAAppuyer.setPrefSize(80 * dim, 20 * dim);
        for (ToucheMachine toucheAAfficher : panneauParent.getTouchesDisponiblesAffichables()) {
            this.bouttonAAppuyer.getItems().add(toucheAAfficher);
        }
        this.bouttonAAppuyer.setValue(ToucheDeBase);
        this.getChildren().add(this.bouttonAAppuyer);

        this.btnEnlever = new Button("", new ImageView(new Image(getClass().getResourceAsStream("ressourcesGraphiques/del.png"))));
        this.btnEnlever.setLayoutX(85 * dim);
        this.btnEnlever.setLayoutY(0);
        this.btnEnlever.setMaxSize(20 * dim, 20 * dim);
        this.btnEnlever.setMinSize(20 * dim, 20 * dim);
        this.btnEnlever.setPrefSize(20 * dim, 20 * dim);
        Tooltip infobulleEnlever = new Tooltip();
        infobulleEnlever.setText("Enlever cette touche de la combinaison");
        this.btnEnlever.setTooltip(infobulleEnlever);
        this.getChildren().add(this.btnEnlever);

        this.btnEnlever.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                panneauParent.enleverUneAssociation(PanneauToucheMachinePourCombinaison.this);
            }
        });
    }

    public void activerBtnSupprimer()
    {
        this.btnEnlever.setDisable(false);
    }

    public void desactiverBtnSupprimer()
    {
        this.btnEnlever.setDisable(true);
    }

    public ToucheMachine getToucheCourrante()
    {
        return (ToucheMachine) this.bouttonAAppuyer.getValue();
    }

}
