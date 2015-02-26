package gestionnairedeprofil.IHM;

import gestionnairedeprofil.donnees.structures.AssociationsDansProfil;

/**
 *
 * @author MOREL Charles
 */
interface InterfaceEditionAssociation
{

    public AssociationsDansProfil getAssociations();

    public boolean associationValide();

    public String getMessageDInvalidite();

}
