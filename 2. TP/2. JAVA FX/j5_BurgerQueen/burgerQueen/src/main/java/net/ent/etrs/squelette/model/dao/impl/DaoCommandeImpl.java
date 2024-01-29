package net.ent.etrs.squelette.model.dao.impl;

import net.ent.etrs.squelette.model.dao.IDaoCommande;
import net.ent.etrs.squelette.model.dao.base.JpaBaseDao;
import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.entities.Commande;

import java.util.List;

public class DaoCommandeImpl extends JpaBaseDao<Commande> implements IDaoCommande {
    @Override
    public List<Commande> searchCommandeByClient(Client client) {
        return null;
    }
}