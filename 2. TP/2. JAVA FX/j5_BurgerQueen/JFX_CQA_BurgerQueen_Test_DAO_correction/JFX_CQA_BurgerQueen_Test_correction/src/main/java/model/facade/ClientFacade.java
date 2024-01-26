package model.facade;

import model.entities.Client;
import model.exceptions.BusinessException;

import java.util.Set;

public interface ClientFacade {

    void initialisationClient() throws BusinessException;

    Client searchClientByNomPrenom(String string, String string2);

    Set<Client> findAll() throws BusinessException;

    Client save(Client clientAModifier) throws BusinessException;

    void delete(Client c) throws BusinessException;

}
