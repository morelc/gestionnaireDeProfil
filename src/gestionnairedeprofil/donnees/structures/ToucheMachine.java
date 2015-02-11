package gestionnairedeprofil.donnees.structures;

/**
 *
 * @author MOREL Charles
 */
public class ToucheMachine
{

    public final static int ID_TOUCHE_NON_ASSOCIEE = -1;
    public final static String NON_TOUCHE_NON_ASSOCIEE = "(aucune touche)";
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
