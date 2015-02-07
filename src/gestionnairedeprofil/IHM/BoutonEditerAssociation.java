package gestionnairedeprofil.IHM;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author MOREL Charles
 */
public class BoutonEditerAssociation extends Button
{
    
    public BoutonEditerAssociation(String texteBtn, final PanneauEditionProfil panneauDEditionDuProfil, final int numDuBtn)
    {
        super(texteBtn);
        this.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                panneauDEditionDuProfil.modifierUneAssocDeTouches(numDuBtn);
            }
        });
    }
    
}
