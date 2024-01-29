package net.ent.etrs.squelette.model.dao;

import net.ent.etrs.squelette.model.dao.base.BaseDao;
import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.entities.Commande;

import java.util.List;

public interface IDaoCommande extends BaseDao<Commande> {

    List<Commande> searchCommandeByClient(Client client);
}