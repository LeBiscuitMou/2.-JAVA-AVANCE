package net.ent.etrs.squelette.model.facade.impl;

import net.ent.etrs.squelette.model.dao.IDaoCommande;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.dao.impl.DaoFactory;
import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.entities.Commande;
import net.ent.etrs.squelette.model.facade.IFacadeMetierCommande;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;

public class FacadeMetierCommandeImpl implements IFacadeMetierCommande {
    private IDaoCommande daoCommande;

    protected FacadeMetierCommandeImpl() {
        daoCommande = DaoFactory.getDaoCommande();
    }

    @Override
    public Commande creerCommande(Commande commande) throws DaoException {
        return daoCommande.save(commande);
    }

    @Override
    public void supprimerCommande(Commande commande) throws DaoException {
        daoCommande.delete(commande);
    }

    @Override
    public List<Commande> recupererToutesLesCommande() throws DaoException {
        return Collections.unmodifiableList(IterableUtils.toList(daoCommande.findAll()));
    }

    @Override
    public List<Commande> searchCommandeByClient(Client client) {
        return daoCommande.searchCommandeByClient(client);
    }
}