package net.ent.etrs.squelette.model.facade.impl;

import net.ent.etrs.squelette.model.dao.IDaoProduit;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.dao.impl.DaoFactory;
import net.ent.etrs.squelette.model.entities.Produit;
import net.ent.etrs.squelette.model.entities.references.TailleProduit;
import net.ent.etrs.squelette.model.entities.references.TypeProduit;
import net.ent.etrs.squelette.model.facade.IFacadeMetierProduit;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;

public class FacadeMetierProduitImpl implements IFacadeMetierProduit {
    private IDaoProduit daoProduit;

    protected FacadeMetierProduitImpl() {
        daoProduit = DaoFactory.getDaoProduit();
    }

    @Override
    public Produit creerProduit(Produit produit) throws DaoException {
        return daoProduit.save(produit);
    }

    @Override
    public void supprimerProduit(Produit produit) throws DaoException {
        daoProduit.delete(produit);
    }

    @Override
    public List<Produit> recupererTousLesProduits() throws DaoException {
        return Collections.unmodifiableList(IterableUtils.toList(daoProduit.findAll()));
    }

    @Override
    public Produit searchProduitByNomTypeTaille(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) {
        return daoProduit.searchProduitByNomTypeTaille(nom, typeProduit, tailleProduit);
    }
}