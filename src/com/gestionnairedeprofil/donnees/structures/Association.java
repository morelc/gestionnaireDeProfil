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

import java.util.ArrayList;

/**
 * Classe de représentation des données liées à une touche à une association.
 * Class used to represent datas liked to an association.
 *
 * @author MOREL Charles
 */
public class Association
{

    public final static int ID_TOUCHE_NON_ASSOCIEE = -1;
    public final static int ID_NON_ENREGISTREE_DANS_BD = -2;
    public final static boolean EST_AUTOFIRE_TOUCHE_NON_ASSOCIEE = false;
    public final static int TIMER_TOUCHE_NON_ASSOCIEE = 0;

    private final int id;
    private boolean estAutofire;
    private int timer;
    private final ArrayList<ToucheMachine> touches;

    public Association(int idAssoc, boolean assocEstAutofire, int timerAssoc)
    {
        this.id = idAssoc;
        this.estAutofire = assocEstAutofire;
        this.timer = timerAssoc;
        this.touches = new ArrayList();
    }

    public Association()
    {
        this.id = Association.ID_TOUCHE_NON_ASSOCIEE;
        this.estAutofire = Association.EST_AUTOFIRE_TOUCHE_NON_ASSOCIEE;
        this.timer = Association.TIMER_TOUCHE_NON_ASSOCIEE;
        this.touches = new ArrayList();
        this.touches.add(new ToucheMachine());
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the estAutofire
     */
    public boolean isEstAutofire()
    {
        return estAutofire;
    }

    /**
     * @param estAutofire the estAutofire to set
     */
    public void setEstAutofire(boolean estAutofire)
    {
        this.estAutofire = estAutofire;
    }

    /**
     * @return the timer
     */
    public int getTimer()
    {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(int timer)
    {
        this.timer = timer;
    }

    /**
     * @return the touches
     */
    public ArrayList<ToucheMachine> getTouches()
    {
        return touches;
    }

    /**
     * @param touche
     */
    public void ajouterTouche(ToucheMachine touche)
    {
        this.touches.add(touche);
    }

}
