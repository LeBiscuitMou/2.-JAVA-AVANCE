package model.dao;

import java.io.Serializable;

import model.dao.base.BaseDao;
import model.entities.Produit;
import model.references.TailleProduit;
import model.references.TypeProduit;

public interface DaoProduit extends BaseDao<Produit, Serializable> {

	public Produit searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit);

}
