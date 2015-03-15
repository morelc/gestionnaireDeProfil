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

/**
 * Classe de représentation des données liées à un profil. Class used to
 * represent datas liked to a profil.
 *
 * @author MOREL Charles
 */
public class Profil {

    private int id;
    private String nom;
    private final AssociationsDansProfil associations[];

    public Profil(int idProfil, String nomProfil) {
        this.id = idProfil;
        this.nom = nomProfil;
        this.associations = new AssociationsDansProfil[12];
        for (int i = 0; i < 12; i++) {
            this.associations[i] = new AssociationsDansProfil();
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param i
     * @return the associations
     */
    public AssociationsDansProfil getAssociationsAt(int i) {
        if (i >= 0 && i <= 11) {
            return this.associations[i];
        } else {
            AssociationsDansProfil listeVide;
            listeVide = new AssociationsDansProfil();
            listeVide.add(new Association());
            return listeVide;
        }
    }

    /**
     * @param i
     * @param association the associations to set
     */
    public void setAssociationsAt(int i, AssociationsDansProfil association) {
        if (i >= 0 && i <= 11) {
            this.associations[i] = association;
        }
    }

}
