package garage.model.dao.jpa;

import java.util.List;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import garage.model.dao.AbstractDao;
import garage.model.dao.DaoMarque;
import garage.model.entities.Marque;

public class DaoMarqueJpa extends AbstractDao<Marque> implements DaoMarque {

	

	@Override
	public List<Marque> readAll() {
		String requete = "select m from Marque m";
		TypedQuery<Marque> q = em.createQuery(requete, Marque.class);
		return q.getResultList();
	}

	
	@Override
	public boolean exist(Marque t) {
		TypedQuery<Marque> q = em.createQuery(" select m from Marque m where m.libelleMarque = :m", Marque.class);
		q.setParameter("m", t.getLibelle());
		List<Marque> lst = q.getResultList();
		return lst.size() > 0;
	}

	@Override
	public Marque rechercherMarqueParLibelle(String libelle) {
		// Lire en base connaissant le libelle de la marque
		TypedQuery<Marque> q = em.createNamedQuery("rechercherMarqueParLibelle", Marque.class);
		q.setParameter("libelle", libelle);
		try {
			return q.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}

	}

	@Override
	public void init() {
		createNewMarque("OPEL");
		createNewMarque("PEUGEOT");
		createNewMarque("MERCEDES");
		createNewMarque("CITROEN");

	}

	private void createNewMarque(String lib) {
		if(Objects.isNull(rechercherMarqueParLibelle(lib))){
			Marque m = new Marque();
			m.setLibelle(lib);
			create(m);
		}
	}

}
