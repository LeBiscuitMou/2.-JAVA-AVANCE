package net.ent.etrs.squelette.model.facade;

import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.entities.Commande;

import java.util.List;

public interface IFacadeMetierCommande {
    Commande creerCommande(Commande commande) throws DaoException;

    void supprimerCommande(Commande commande) throws DaoException;

    List<Commande> recupererToutesLesCommande() throws DaoException;

    List<Commande> searchCommandeByClient(Client client);
}