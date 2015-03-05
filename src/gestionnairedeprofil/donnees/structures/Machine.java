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
public class Machine {

    private final int id;
    private final String nom;
    private ArrayList<Profil> profils;
    private ArrayList<ToucheMachine> touches;

    public Machine(int idMachine, String nomMachine) {
        this.id = idMachine;
        this.nom = nomMachine;
        this.profils = new ArrayList();
        this.touches = new ArrayList();
        this.touches.add(new ToucheMachine());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the profils
     */
    public ArrayList<Profil> getProfils() {
        return profils;
    }

    public void setProfils(ArrayList<Profil> ArrayListProfilsAssocies) {
        this.profils = ArrayListProfilsAssocies;
    }

    /**
     * @return the touches
     */
    public ArrayList<ToucheMachine> getTouches() {
        return touches;
    }
    public void setTouches(ArrayList<ToucheMachine> ArrayListTouchesAssocies) {
           this.touches=ArrayListTouchesAssocies;
    }
    /**
     * @param nouveauProfil
     */
    public void ajouterProfil(Profil nouveauProfil) {
        this.profils.add(nouveauProfil);
    }

    /**
     * @param ancienProfil
     */
    public void elneverProfil(Profil ancienProfil) {
        this.profils.remove(ancienProfil);
    }

    /**
     * @param nouvelleTouche
     */
    public void ajouterTouche(ToucheMachine nouvelleTouche) {
        this.touches.add(nouvelleTouche);
    }

    @Override
    public String toString()
    {
        return this.nom;
    }



}
