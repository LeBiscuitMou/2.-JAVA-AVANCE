package net.ent.etrs.projet.models.facades.interfaces;

import net.ent.etrs.projet.models.entities.Client;
import net.ent.etrs.projet.models.exceptions.BusinessException;

import java.util.Optional;
import java.util.SortedSet;

public interface IFacadeClient extends GenericFacade<Client> {
    public Client createClient(Client pClient) throws BusinessException;

    public void deleteClient(Client pClient) throws BusinessException;

    public SortedSet<Client> getAllClient() throws BusinessException;

    public Optional<Client> getClientById(Long pId) throws BusinessException;

}
