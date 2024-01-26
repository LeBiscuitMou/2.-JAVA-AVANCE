package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import common.utils.REQ;
import model.entities.Client;
import model.entities.Commande;

public class DaoCommandeJPAImpl extends AbstractDao<Commande, String> implements DaoCommande {


//	@Override
//	public void create(Commande t) throws DaoException {
//		EntityTransaction trans = getEntityManager().getTransaction();
//		trans.begin();
//		getEntityManager().persist(t);
//		trans.commit();
//	}
//
//	@Override
//	public void update(Commande t) throws DaoException {
//		EntityTransaction trans = getEntityManager().getTransaction();
//		trans.begin();
//		getEntityManager().merge(t);
//		trans.commit();
//	}

	@Override
	public List<Commande> searchCommandeByClient(Client client) {
		List<Commande> lst = new ArrayList<>();
		try {
			TypedQuery<Commande> query = this.getEntityManager()
					.createQuery(REQ.COMMANDE_PAR_CLIENT, Commande.class);
			query.setParameter("client", client);
			lst = query.getResultList();
		} catch (NoResultException e) {
			lst = null;
		}
		return lst;
	}
}
