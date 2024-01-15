package net.ent.etrs.logistock.model.facade.impl;

import net.ent.etrs.logistock.model.dao.DaoFactory;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeClient;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeClientImpl implements IFacadeClient {
    private final IDaoClient daoClient = DaoFactory.getDaoClient();
    @Override
    public Client createClient(Client pClient) throws BusinessException {
        try {
            return daoClient.save(pClient);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteClient(Client pClient) throws BusinessException {
        try {
            daoClient.delete(pClient.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public SortedSet<Client> getAllClient() throws BusinessException {
        try {
            return Collections.unmodifiableSortedSet(new TreeSet<>(IterableUtils.toList(daoClient.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Client> getClientById(Long pId) throws BusinessException {
        try {
            return daoClient.findClientById(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}