package garage.model.facade;

import java.util.List;
import java.util.UUID;

import garage.model.entities.Marque;
import garage.model.entities.Voiture;
import garage.model.exceptions.BusinessException;

public interface IFacadeMetier {

	void creerUneVoiture(Voiture v) throws BusinessException;

	void supprimerUneVoiture(Voiture v) throws BusinessException;

	List<Voiture> listerVoitures() throws BusinessException;

	List<Voiture> listerVoituresParPuissance();

	Voiture listerUneVoiture(UUID cle) throws BusinessException;

	void mettreAJour(Voiture v) throws BusinessException;

	void creerUneMarque(Marque m) throws BusinessException;

	List<Marque> listerMarques() throws BusinessException;

	Marque listerUneMarque(UUID cle) throws BusinessException;

//	boolean existe(String libelle);
	
	 long compterVoituresParMarque(Marque m);

	void initialiser();

}