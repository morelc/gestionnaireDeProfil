package gestionnairedeprofil.donnees.structures;

import java.util.ArrayList;

/**
 *
 * @author MOREL Charles
 */
public class AssociationsDansProfil extends ArrayList<Association>
{

    public final static int TYPE_VIDE = 0;
    public final static int TYPE_TOUCHE_SIMPLE = 1;
    public final static int TYPE_COMBINAISON = 2;
    public final static int TYPE_AUTOFIRE = 3;
    public final static int TYPE_MACRO = 4;

    public AssociationsDansProfil()
    {
        super();
    }

    public int getAssocType()
    {
        if (this.get(0).getId() == Association.ID_TOUCHE_NON_ASSOCIEE
                || this.get(0).getTouches().get(0).getId() == ToucheMachine.ID_TOUCHE_NON_ASSOCIEE) {
            return AssociationsDansProfil.TYPE_VIDE;
        }
        else {
            if (this.size() == 1) {
                if (this.get(0).getTouches().size() > 1) {
                    return AssociationsDansProfil.TYPE_COMBINAISON;
                }
                if (this.get(0).isEstAutofire()) {
                    return AssociationsDansProfil.TYPE_AUTOFIRE;
                }
                return AssociationsDansProfil.TYPE_TOUCHE_SIMPLE;
            }
            else {
                return AssociationsDansProfil.TYPE_MACRO;
            }
        }
    }

}
