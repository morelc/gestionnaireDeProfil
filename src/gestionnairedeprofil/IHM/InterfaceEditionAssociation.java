package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.Association;

/**
 *
 * @author MOREL Charles
 */
interface InterfaceEditionAssociation
{

    public Association getAssociation();
    
    public boolean associationValide();
    
    public String getMessageDInvalidite();
}
