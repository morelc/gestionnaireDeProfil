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



}
