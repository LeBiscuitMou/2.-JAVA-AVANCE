package model.facade;


import common.validator.ValidException;
import model.dao.DaoException;
import model.entities.Client;
import model.entities.Commande;
import model.entities.EntitiesFactory;
import model.entities.Produit;
import model.exceptions.BusinessException;
import model.exceptions.ProduitException;
import model.references.TailleProduit;
import model.references.TypeProduit;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class CommandeFacadeImpl extends AbstractFacade<Commande> implements CommandeFacade {

    @Override
    public void initialisation(ClientFacade facadeClient, ProduitFacade facadeProduit) throws BusinessException {

        List<Produit> lstProduits = new ArrayList<>();
        List<Commande> lstCommandes = new ArrayList<>();
        try {
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("burger", TypeProduit.SANDWICH, TailleProduit.PETIT));
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("coca", TypeProduit.BOISSON, TailleProduit.PETIT));
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("patatoes", TypeProduit.POTATOES, TailleProduit.PETIT));

            lstCommandes.add(EntitiesFactory.fabriquerCommande(facadeClient.searchClientByNomPrenom("dark", "queen"), lstProduits));

            lstProduits.clear();

            lstProduits.add(facadeProduit.searchProduitByNomPrenom("coca", TypeProduit.BOISSON, TailleProduit.GRAND));
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("coca", TypeProduit.BOISSON, TailleProduit.GRAND));
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("coca", TypeProduit.BOISSON, TailleProduit.MOYEN));
            lstProduits.add(facadeProduit.searchProduitByNomPrenom("tiges blondes", TypeProduit.FRITE, TailleProduit.MOYEN));


            lstCommandes.add(EntitiesFactory.fabriquerCommande(facadeClient.searchClientByNomPrenom("Udutorse", "paul"), lstProduits));
        } catch (ProduitException | ValidException e) {
            e.printStackTrace();
//            AlerteUtils.afficherMessageDansAlerte(C.MSG_ERREUR_INIT_COMMANDE, Alert.AlertType.INFORMATION);
            throw new BusinessException(e.getMessage(), e);
        }

//        lstCommandes.forEach(c -> this.daoCommande.save(c));
        try {
            for (Commande com : lstCommandes) {

                this.daoCommande.save(com);

            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Commande> searchCommandeByClient(Client client) {
        return this.daoCommande.searchCommandeByClient(client);
    }

    @Override
    public Commande save(Commande c) throws BusinessException {
        try {
            return this.daoCommande.save(c).get();
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
