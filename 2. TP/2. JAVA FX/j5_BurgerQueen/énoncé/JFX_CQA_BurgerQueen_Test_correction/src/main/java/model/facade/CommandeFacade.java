package model.facade;

import model.entities.Client;
import model.entities.Commande;
import model.exceptions.BusinessException;

import java.util.List;

public interface CommandeFacade {

    void initialisation(ClientFacade facadeClient, ProduitFacade facadeProduit) throws BusinessException;

    List<Commande> searchCommandeByClient(Client client);

    Commande save(Commande commandeAModifier) throws BusinessException;

}
