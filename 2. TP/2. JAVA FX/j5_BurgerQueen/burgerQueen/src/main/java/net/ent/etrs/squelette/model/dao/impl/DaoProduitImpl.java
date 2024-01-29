package net.ent.etrs.squelette.model.dao.impl;

import net.ent.etrs.squelette.model.dao.IDaoProduit;
import net.ent.etrs.squelette.model.dao.base.JpaBaseDao;
import net.ent.etrs.squelette.model.entities.Produit;
import net.ent.etrs.squelette.model.entities.references.TailleProduit;
import net.ent.etrs.squelette.model.entities.references.TypeProduit;

public class DaoProduitImpl extends JpaBaseDao<Produit> implements IDaoProduit {
    @Override
    public Produit searchProduitByNomTypeTaille(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) {
        return null;
    }
}