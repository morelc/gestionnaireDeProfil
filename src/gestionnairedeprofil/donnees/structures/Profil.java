package gestionnairedeprofil.donnees.structures;

import java.util.ArrayList;

/**
 *
 * @author MOREL Charles
 */
public class Profil
{

    private final int id;
    private String nom;
    private ArrayList<Association> associations[];

    public Profil(int idProfil, String nomProfil)
    {
        this.id = idProfil;
        this.nom = nomProfil;
        this.associations = new ArrayList[12];
        for (int i = 0; i < 12; i++) {
            this.associations[i] = new ArrayList();
        }
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @return the associations
     */
    public ArrayList<Association> getAssociationsAt(int i)
    {
        if (i >= 0 && i <= 11) {
            return this.associations[i];
        }
        else {
            ArrayList<Association> listeVide;
            listeVide = new ArrayList();
            listeVide.add(new Association());
            return listeVide;
        }
    }

    /**
     * @param association the associations to set
     */
    public void setAssociationsAt(int i, ArrayList<Association> association)
    {
        if (i >= 0 && i <= 11) {
            this.associations[i] = association;
        }
    }
}
