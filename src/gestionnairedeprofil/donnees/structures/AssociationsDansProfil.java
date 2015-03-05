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
                if (this.get(0).isEstAutofire()) {
                    return AssociationsDansProfil.TYPE_AUTOFIRE;
                }
                if (this.get(0).getTouches().size() > 1) {
                    return AssociationsDansProfil.TYPE_COMBINAISON;
                }
                return AssociationsDansProfil.TYPE_TOUCHE_SIMPLE;
            }
            else {
                return AssociationsDansProfil.TYPE_MACRO;
            }
        }
    }

}
