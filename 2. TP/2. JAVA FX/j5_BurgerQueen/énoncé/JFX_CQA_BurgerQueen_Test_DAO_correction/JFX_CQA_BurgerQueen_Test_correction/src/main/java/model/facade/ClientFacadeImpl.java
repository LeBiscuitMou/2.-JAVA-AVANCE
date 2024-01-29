package model.facade;

import common.validator.ValidException;
import model.dao.DaoException;
import model.entities.Client;
import model.entities.EntitiesFactory;
import model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ClientFacadeImpl extends AbstractFacade<Client> implements ClientFacade {

    @Override
    public void initialisationClient() throws BusinessException {


        try {
            List<Client> lstClients = new ArrayList<>();
            lstClients.add(EntitiesFactory.fabriquerClient("toto", "totop", "toto@g"));

            lstClients.add(EntitiesFactory.fabriquerClient("titi", "titip", "titi@g"));
            lstClients.add(EntitiesFactory.fabriquerClient("alf", "raid", "alf@g"));
            lstClients.add(EntitiesFactory.fabriquerClient("dark", "queen", "dark.queen@g"));
            lstClients.add(EntitiesFactory.fabriquerClient("Udutorse", "paul", "paul.udutore@g"));


            lstClients.forEach((p) -> rechercherCreerClient(p));

        } catch (ValidException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage(), e);
        }

    }

    private void rechercherCreerClient(Client c) {

        try {
            if (Objects.isNull(this.daoClient.searchClientByNomPrenom(c.getNom(), c.getPrenom()))) {
                this.daoClient.save(c);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Client searchClientByNomPrenom(String nom, String prenom) {
        return this.daoClient.searchClientByNomPrenom(nom, prenom);
    }

    @Override
    public Set<Client> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.daoClient.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Client save(Client c) throws BusinessException {
        try {
            return this.daoClient.save(c).get();
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Client c) throws BusinessException {
        try {
            this.daoClient.delete(c.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
