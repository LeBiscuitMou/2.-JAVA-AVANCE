package garage.model.facade;

import java.util.List;
import java.util.UUID;

//import garage.model.dao.Dao;
import common.dao.Dao;
import common.dao.exceptions.DaoException;
import garage.model.dao.DaoFactory;
import garage.model.dao.DaoVoiture;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;
import garage.model.exceptions.BusinessException;

public class FacadeMetier implements IFacadeMetier {
	
	DaoVoiture persistanceV = DaoFactory.fabriquerDaoVoitureJpaFactory();
	Dao<Marque,UUID> persistanceM = DaoFactory.fabriquerDaoMarqueJpaFactory();
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#creerUneVoiture(model.entities.Voiture)
	 */
	@Override
	public void creerUneVoiture(Voiture v) throws BusinessException {
		try {
			persistanceV.create(v);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}		
	}
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#supprimerUneVoiture(model.entities.Voiture)
	 */
	@Override
	public void supprimerUneVoiture(Voiture v) throws BusinessException {		
		try {
			persistanceV.delete(v);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}	
		
	}
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#listerVoitures()
	 */
	@Override
	public List<Voiture> listerVoitures() throws BusinessException {
		try {
			return this.persistanceV.readAll();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}	
	}	
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#listerVoituresParPuissance()
	 */
	@Override
	public List<Voiture> listerVoituresParPuissance(){
		return this.persistanceV.listerParPuissance();
	}
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#listerUneVoiture(java.util.UUID)
	 */
	@Override
	public Voiture listerUneVoiture(UUID cle) throws BusinessException {
		try {
			return persistanceV.read(cle);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#mettreAJour(model.entities.Voiture)
	 */
	
	
	
	@Override
	public void mettreAJour(Voiture v) throws BusinessException{
		try {
			persistanceV.update(v);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#creerUneMarque(model.entities.Marque)
	 */
	@Override
	public void creerUneMarque(Marque m) throws BusinessException {
		try {
			persistanceM.create(m);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		};		
	}
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#listerMarques()
	 */
	@Override
	public List<Marque> listerMarques() throws BusinessException {
		try {
			return this.persistanceM.readAll();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}		

	}	
	
	/* (non-Javadoc)
	 * @see model.facade.IfacadeMetier#listerUneMarque(java.util.UUID)
	 */
	@Override
	public Marque listerUneMarque(UUID cle) throws BusinessException {
		try {
			return persistanceM.read(cle);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}	
	}
	
//	/* (non-Javadoc)
//	 * @see model.facade.IfacadeMetier#existe(java.lang.String)
//	 */
//	@Override
//	public boolean existe(String libelle){
//		return persistanceM.equals(libelle);
//	}

	@Override
	public long compterVoituresParMarque(Marque m) {
		//Soit je demande a la persistance qui m'execute une requete jpql
		return persistanceV.compterVoituresParMarque(m);
		//Soit :comme la marque "connait" ses voitures ( et donc porte une collection de voitures en son sein)
		//return m.getLesVoitures().size();
	}

	@Override
	public void initialiser() {
		this.persistanceM.init();
		
	}
}
