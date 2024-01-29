package net.ent.etrs.squelette.model.facade;

import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.facade.exceptions.BusinessException;

import java.util.List;

public interface IFacadeMetierClient {
    Client creerClient(Client client) throws BusinessException;

    void supprimerClient(Client client) throws BusinessException;

    List<Client> recupererTousLesClients() throws BusinessException;

    Client searchClientByNomPrenom(String nom, String prenom) throws BusinessException;
}