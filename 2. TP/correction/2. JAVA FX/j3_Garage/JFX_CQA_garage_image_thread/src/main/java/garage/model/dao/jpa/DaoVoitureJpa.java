package garage.model.dao.jpa;

import java.util.List;
import java.util.Objects;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import garage.model.dao.AbstractDao;
import garage.model.dao.DaoVoiture;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;


public class DaoVoitureJpa extends AbstractDao<Voiture> implements DaoVoiture{	
		
	public List<Voiture> readAll() {
		TypedQuery<Voiture> q= em.createQuery("select v from Voiture v", Voiture.class);
		return q.getResultList();		
	}	

	@Override
	public boolean exist(Voiture v) {
		return  (Objects.nonNull(em.find(Voiture.class,v.getId())));	
		
	}
	
	@Override
	public List<Voiture> listerParPuissance(){
		//Requete jpql( order by)
//		TypedQuery<Voiture> q= em.createQuery("select v from Voiture v order by v.puissance desc",Voiture.class);
//		List<Voiture> lst = q.getResultList();
//		return lst;
		
		//Version NamedQuery		
		TypedQuery<Voiture> q =  em.createNamedQuery("listerVoituresParPuissance",Voiture.class);
		return q.getResultList();

	} 
	
	@Override
	public Voiture rechercherParImmat(String pimmat) {
		//Lire en base connaissant l'immatriculation
		TypedQuery<Voiture> q =  em.createNamedQuery("rechercherVoitureParImmatriculation",Voiture.class);
		q.setParameter("immat",pimmat);
		return q.getSingleResult();

	}
	
	@Override
	public List<Voiture> rechercherParModele(String mod){	
		//souci avec le %mod%
//		TypedQuery<Voiture> q = em.createQuery(" select v from Voiture v where v.modele like  '\u0025':mod\'u0025' ",Voiture.class);
		TypedQuery<Voiture> q = em.createNamedQuery("rechercherVoitureParModele",Voiture.class);
		q.setParameter("mod", mod);
		return q.getResultList();		
	}	

	@Override
	//Méthode requete JPQL inutile si l'entité Marque "connait" ses voitures	
	public List<Voiture> trouverVoituresParMarque(Marque m){
//		TypedQuery<Voiture> q = em.createQuery(" select v from Voiture v where v.marque= :m",Voiture.class);
		TypedQuery<Voiture> q = em.createNamedQuery("trouverVoituresParMarque",Voiture.class);
		q.setParameter("m", m);
		return q.getResultList();	
	}
	
	@Override
	//Méthode avec appel de namedQuery (inutile si l'entité Marque connait l'entité Voiture)
	public long compterVoituresParMarque(Marque m){
		Query q = em.createNamedQuery("compterVoituresDuneMarque");	
		q.setParameter("m", m);
		long count = (long) q.getSingleResult();
		return count;
	}
	
}
