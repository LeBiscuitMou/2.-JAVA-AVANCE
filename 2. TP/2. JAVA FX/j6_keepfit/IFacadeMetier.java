package keepfit.model.facade;

import java.util.List;

import keepfit.model.entities.Abonne;
import keepfit.model.entities.Prestation;
import keepfit.model.exceptions.BusinessException;

public interface IFacadeMetier {
	/**
	 * Fonctionnalité "initialiser des prestations en base de données" s'il n'y en a pas déjà
	 */
	void initialiserDesPrestations();

	/**
	 * Fonctionnalité "Ajouter un abonné"
	 * @param a : nouvel abonné
	 * @throws BusinessException 
	 */
	void creerUnAbonne(Abonne a) throws BusinessException;

	/**
	 * Fonctionnalité "Supprimer un abonné ( par clic droit/supprimer )"
	 * @param a : abonné séléctionné
	 * @throws BusinessException 
	 */
	void supprimerUnAbonne(Abonne a) throws BusinessException;

	/**
	 * Fonctionnalité "Lister tous les abonnés"
	 * @return List<Abonne> : les abonnés de la bdd
	 * @throws BusinessException 
	 */
	List<Abonne> listerLesAbonnes() throws BusinessException;

	/**
	 * Fonctionnalité "Lister les prestations"
	 * @return List<Prestation> : les prestations de la bdd
	 * @throws BusinessException 
	 */
	List<Prestation> listerLesPrestations() throws BusinessException;

	/**
	 * Fonctionnalité "Ajouter une prestation a un abonné" (écran de modification d'un abonné)
	 * Cocher un checkbox
	 * @param p : la prestation cochée
	 * @param a : l'abonné choisi dans le tableView
	 * @throws BusinessException 
	 */
	void ajouterPrestationAbonne(Prestation p, Abonne a) throws BusinessException;

	/**
	 * Fonctionnalité "Supprimer une prestation a un abonné" (écran de modification d'un abonné)
	 * Décocher un checkbox
	 * @param p : la prestation décochée
	 * @param a : l'abonné choisi dans le tableView
	 * @throws BusinessException 
	 */
	void supprimerPrestationAbonne(Prestation p, Abonne a) throws BusinessException;

}