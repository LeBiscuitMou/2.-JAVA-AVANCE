package net.ent.etrs.logistock.model.facade.impl;

import lombok.*;
import net.ent.etrs.logistock.model.dao.IDaoClient;
import net.ent.etrs.logistock.model.dao.impl.DaoFactory;
import net.ent.etrs.logistock.model.entities.Client;
import net.ent.etrs.logistock.model.exceptions.DaoException;
import net.ent.etrs.logistock.model.facade.IFacadeMetierClient;
import net.ent.etrs.logistock.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeMetierClientImpl implements IFacadeMetierClient {
    private IDaoClient daoClient;

    public FacadeMetierClientImpl() {
        daoClient = DaoFactory.getDaoClient();
    }

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
            daoClient.delete(pClient);
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
            return daoClient.getClientById(pId);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}