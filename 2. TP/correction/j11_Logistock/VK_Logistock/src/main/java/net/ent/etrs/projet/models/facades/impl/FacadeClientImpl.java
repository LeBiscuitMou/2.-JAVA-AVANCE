package net.ent.etrs.projet.models.facades.impl;

import net.ent.etrs.projet.models.commons.CoUtils;
import net.ent.etrs.projet.models.dao.IDaoClient;
import net.ent.etrs.projet.models.dao.impl.DaoFactory;
import net.ent.etrs.projet.models.entities.Client;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeClient;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FacadeClientImpl extends GenericFacadeImpl<Client> implements IFacadeClient {
    protected IDaoClient daoClient;

    protected FacadeClientImpl() {
        super(DaoFactory.getDaoClient());
        daoClient = DaoFactory.getDaoClient();
    }


    @Override
    public Client createClient(Client pClient) throws BusinessException {
        return super.save(pClient);
    }

    @Override
    public void deleteClient(Client pClient) throws BusinessException {
        super.delete(pClient.getId());
    }

    @Override
    public SortedSet<Client> getAllClient() throws BusinessException {
        return new TreeSet<>(CoUtils.iterableToSet(super.findAll()));
    }

    @Override
    public Optional<Client> getClientById(Long pId) throws BusinessException {
        return super.find(pId);
    }
}
