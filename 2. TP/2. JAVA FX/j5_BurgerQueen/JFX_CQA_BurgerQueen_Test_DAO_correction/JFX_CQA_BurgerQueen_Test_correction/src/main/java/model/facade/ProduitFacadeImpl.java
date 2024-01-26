package model.facade;

import common.validator.ValidException;
import model.dao.DaoException;
import model.entities.EntitiesFactory;
import model.entities.Produit;
import model.exceptions.BusinessException;
import model.references.TailleProduit;
import model.references.TypeProduit;
import org.apache.commons.collections4.IterableUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProduitFacadeImpl extends AbstractFacade<Produit> implements ProduitFacade {

    @Override
    public void initialisationProduit() throws BusinessException {
        try {
            List<Produit> lstProduits = new ArrayList<>();

            lstProduits.add(EntitiesFactory.fabriquerProduit("burger", TailleProduit.PETIT, new BigDecimal(2), TypeProduit.SANDWICH));
            lstProduits.add(EntitiesFactory.fabriquerProduit("double miche", TailleProduit.PETIT, new BigDecimal(2), TypeProduit.SANDWICH));
            lstProduits.add(EntitiesFactory.fabriquerProduit("double miche", TailleProduit.MOYEN, new BigDecimal(3), TypeProduit.SANDWICH));
            lstProduits.add(EntitiesFactory.fabriquerProduit("double miche", TailleProduit.GRAND, new BigDecimal(4), TypeProduit.SANDWICH));
            lstProduits.add(EntitiesFactory.fabriquerProduit("coca", TailleProduit.PETIT, new BigDecimal(1), TypeProduit.BOISSON));

            lstProduits.add(EntitiesFactory.fabriquerProduit("coca", TailleProduit.MOYEN, new BigDecimal(1.5), TypeProduit.BOISSON));

            lstProduits.add(EntitiesFactory.fabriquerProduit("coca", TailleProduit.GRAND, new BigDecimal(2), TypeProduit.BOISSON));
            lstProduits.add(EntitiesFactory.fabriquerProduit("patatoes", TailleProduit.PETIT, new BigDecimal(1), TypeProduit.POTATOES));
            lstProduits.add(EntitiesFactory.fabriquerProduit("tiges blondes", TailleProduit.PETIT, new BigDecimal(1), TypeProduit.FRITE));
            lstProduits.add(EntitiesFactory.fabriquerProduit("tiges blondes", TailleProduit.MOYEN, new BigDecimal(1.5), TypeProduit.FRITE));
            lstProduits.add(EntitiesFactory.fabriquerProduit("tiges blondes", TailleProduit.GRAND, new BigDecimal(2), TypeProduit.FRITE));

            lstProduits.forEach((p) -> rechercherCreerProduit(p));

        } catch (ValidException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage(), e);
        }

    }

    private void rechercherCreerProduit(Produit p) {
        try {
            if (Objects.isNull(this.searchProduitByNomPrenom(p.getNom(), p.getTypeProduit(), p.getTailleProduit()))) {
                this.daoProduit.save(p);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produit searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) {
        return this.daoProduit.searchProduitByNomPrenom(nom, typeProduit, tailleProduit);
    }

    @Override
    public Set<Produit> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.daoProduit.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
