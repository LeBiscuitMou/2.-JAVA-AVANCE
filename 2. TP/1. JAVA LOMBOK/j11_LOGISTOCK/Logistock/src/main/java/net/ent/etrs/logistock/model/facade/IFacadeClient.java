package net.ent.etrs.logistock.model.facade;

import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;

import java.util.Optional;
import java.util.SortedSet;

public interface IFacadeClient {
    // GESTION DES CLIENTS

    public Client createClient(Client pClient) throws BusinessException;

    public void deleteClient(Client pClient) throws BusinessException;

    public SortedSet<Client> getAllClient() throws BusinessException;

    public Optional<Client> getClientById(Long pId) throws BusinessException;
}
