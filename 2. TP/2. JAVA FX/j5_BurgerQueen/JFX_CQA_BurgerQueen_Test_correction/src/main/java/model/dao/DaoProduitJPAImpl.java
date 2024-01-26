package model.dao;

import common.utils.REQ;
import model.entities.Produit;
import model.references.TailleProduit;
import model.references.TypeProduit;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class DaoProduitJPAImpl extends AbstractDao<Produit, Long> implements DaoProduit {

    @Override
    public Produit searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) {
        Produit p = null;
        try {
            TypedQuery<Produit> query = super.getEm()
                    .createQuery(REQ.PRODUIT_BY_NOM_TYPE_TAILLE, Produit.class);
            query.setParameter(1, nom);
            query.setParameter(2, typeProduit);
            query.setParameter(3, tailleProduit);
            p = query.getSingleResult();
        } catch (NoResultException e) {
            p = null;
        }
        return p;
    }
}
