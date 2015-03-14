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
package com.gestionnairedeprofil.donnees.structures;

import com.gestionnairedeprofil.configuration.Langue;

/**
 * Classe de représentation des données liées à une touche à simuler. Class used
 * to represent datas liked to a button to emulate.
 *
 * @author MOREL Charles
 */
public class ToucheMachine
{

    public final static int ID_TOUCHE_NON_ASSOCIEE = -1;
    public final static String NON_TOUCHE_NON_ASSOCIEE = Langue.getLangue().getString("ToucheMachine_NON_TOUCHE_NON_ASSOCIEE");
    public final static int SIGNAL_TOUCHE_NON_ASSOCIEE = 0;
    private final int id;
    private final String nom;
    private final int signal;

    public ToucheMachine(int idTouche, String nomTouche, int signalTouche)
    {
        this.id = idTouche;
        this.nom = nomTouche;
        this.signal = signalTouche;
    }

    public ToucheMachine()
    {
        this.id = ToucheMachine.ID_TOUCHE_NON_ASSOCIEE;
        this.nom = ToucheMachine.NON_TOUCHE_NON_ASSOCIEE;
        this.signal = ToucheMachine.SIGNAL_TOUCHE_NON_ASSOCIEE;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the signal
     */
    public int getSignal()
    {
        return signal;
    }

    @Override
    public String toString()
    {
        return nom;
    }
}
