package controleur;

import java.io.File;

import Model.Metier.Livraison;
import Model.Metier.Tournee;

public class EtatTourneesCalculees extends EtatDefaut{
	@Override
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	@Override
	public void definirNombreLivreur(int nbLivreurs){
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	@Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	
	@Override
    public void selectionnerPointLivraison(Livraison livraison){
		Controleur.setEtatCourant(Controleur.etatPointLivraisonSelectionne);
    }
    @Override
	public void boutonAjouterPointLivraison(Tournee tournee, Livraison livraison){
    	Controleur.setEtatCourant(Controleur.etatAjouterPointLivraison);
	}
	
}
