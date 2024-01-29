package net.ent.etrs.squelette.model.dao;

import net.ent.etrs.squelette.model.dao.base.BaseDao;
import net.ent.etrs.squelette.model.entities.Produit;
import net.ent.etrs.squelette.model.entities.references.TailleProduit;
import net.ent.etrs.squelette.model.entities.references.TypeProduit;

public interface IDaoProduit extends BaseDao<Produit> {

    Produit searchProduitByNomTypeTaille(String nom, TypeProduit typeProduit, TailleProduit tailleProduit);
}