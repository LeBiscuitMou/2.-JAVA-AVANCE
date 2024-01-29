package net.ent.etrs.squelette.model.facade.impl;

import net.ent.etrs.squelette.model.dao.IDaoClient;
import net.ent.etrs.squelette.model.dao.exceptions.DaoException;
import net.ent.etrs.squelette.model.dao.impl.DaoFactory;
import net.ent.etrs.squelette.model.entities.Client;
import net.ent.etrs.squelette.model.facade.IFacadeMetierClient;
import net.ent.etrs.squelette.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;

public class FacadeMetierClientImpl implements IFacadeMetierClient {
    private IDaoClient daoClient;

    protected FacadeMetierClientImpl() {
        daoClient = DaoFactory.getDaoClient();
    }

    @Override
    public Client creerClient(Client client) throws BusinessException {
        try {
            return daoClient.save(client);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void supprimerClient(Client client) throws BusinessException {
        try {
            daoClient.delete(client);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Client> recupererTousLesClients() throws BusinessException {
        try {
            return IterableUtils.toList(daoClient.findAll());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Client searchClientByNomPrenom(String nom, String prenom) throws BusinessException {
        try {
            return daoClient.searchClientByNomPrenom(nom, prenom);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}