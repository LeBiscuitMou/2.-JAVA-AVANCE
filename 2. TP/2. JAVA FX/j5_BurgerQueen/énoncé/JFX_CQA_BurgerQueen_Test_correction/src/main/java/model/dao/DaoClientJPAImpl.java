package model.dao;

import common.utils.REQ;
import model.entities.Client;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Objects;

public class DaoClientJPAImpl extends AbstractDao<Client, Long> implements DaoClient {

//	@Override
//	public void initialisationClient() {
//		List<Client> lstClients = new  ArrayList<>();
//
//		lstClients.add(EntitiesFactory.fabriquerClient("toto", "totop", "toto@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("titi", "titip", "titi@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("alf", "raid", "alf@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("dark", "queen", "dark.queen@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("Udutorse", "paul", "paul.udutore@g"));
//
//
//		lstClients.forEach((p)->{
//			try {
//				rechercherCreerClient(p);
//			} catch (DaoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//	}

    private void rechercherCreerClient(Client c) throws DaoException {
        if (Objects.isNull(this.searchClientByNomPrenom(c.getNom(), c.getPrenom()))) {
            this.save(c);
        }
    }


    @Override
    public Client searchClientByNomPrenom(String nom, String prenom) {
        Client c = null;
        try {
            TypedQuery<Client> query = super.getEm()
                    .createQuery(REQ.CLIENT_BY_NOM_PRENOM, Client.class);
            query.setParameter(1, nom);
            query.setParameter(2, prenom);
            c = query.getSingleResult();
        } catch (NoResultException e) {
            c = null;
        }
        return c;
    }

}
