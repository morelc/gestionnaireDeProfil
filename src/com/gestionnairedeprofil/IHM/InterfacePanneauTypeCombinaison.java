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
 * along with Gestionnaire de profil.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gestionnairedeprofil.IHM;

import com.gestionnairedeprofil.donnees.structures.ToucheMachine;
import java.util.ArrayList;

/**
 * Interface utilisée pour définir tous les panneaux de type combinaison
 * (combinaison, autofire, combinaison dans macro).
 *
 * Interface used to define all pnanes of type combinaison (combinaison,
 * autofire, combinaison in autofire)
 *
 * @author MOREL Charles
 */
public interface InterfacePanneauTypeCombinaison
{

    public ArrayList<ToucheMachine> getTouchesDisponiblesAffichables();

    public void enleverUneAssociation(final PanneauToucheMachinePourCombinaison assocAEnlever);

}
