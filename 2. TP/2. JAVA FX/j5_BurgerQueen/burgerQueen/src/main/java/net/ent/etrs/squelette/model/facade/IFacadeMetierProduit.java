package net.ent.etrs.squelette.model.facade;

import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.entities.Produit;
import net.ent.etrs.squelette.model.entities.references.TailleProduit;
import net.ent.etrs.squelette.model.entities.references.TypeProduit;

import java.util.List;

public interface IFacadeMetierProduit {
    Produit creerProduit(Produit produit) throws DaoException;

    void supprimerProduit(Produit produit) throws DaoException;

    List<Produit> recupererTousLesProduits() throws DaoException;

    Produit searchProduitByNomTypeTaille(String nom, TypeProduit typeProduit, TailleProduit tailleProduit);
}