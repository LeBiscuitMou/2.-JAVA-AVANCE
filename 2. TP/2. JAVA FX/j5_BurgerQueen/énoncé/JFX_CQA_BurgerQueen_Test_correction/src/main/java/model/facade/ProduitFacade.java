package model.facade;

import model.entities.Produit;
import model.exceptions.BusinessException;
import model.references.TailleProduit;
import model.references.TypeProduit;

import java.util.Set;

public interface ProduitFacade {

    void initialisationProduit() throws BusinessException;

    Produit searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit);

    Set<Produit> findAll() throws BusinessException;


}
