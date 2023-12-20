package net.ent.etrs.heartStone.models.facades.impl;

import net.ent.etrs.heartStone.models.commons.Utils;
import net.ent.etrs.heartStone.models.dao.CarteDao;
import net.ent.etrs.heartStone.models.dao.exceptions.DaoException;
import net.ent.etrs.heartStone.models.dao.DaoFactory;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.facades.FacadeMetier;
import net.ent.etrs.heartStone.models.facades.exception.BusinessException;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FacadeMetierImpl implements FacadeMetier {
    CarteDao carteDao;

    public FacadeMetierImpl() {
        this.carteDao = DaoFactory.fabriquerCarteDao();
    }

    @Override
    public Carte sauvegarderCarte(Carte carte) throws BusinessException {
        try{
            return this.carteDao.save(carte);
        } catch (DaoException | SQLException e) {
            throw new BusinessException("Erreur sauvegarde carte");        }
    }
    @Override
    public List<Carte> getLesCarte() throws BusinessException {
        try {
            return Collections.unmodifiableList(Utils.iterableToList(this.carteDao.findAll()));
        } catch (DaoException | SQLException e) {
            throw new BusinessException("Erreur récupération des cartes");
        }
    }

    @Override
    public List<Carte> getCarteByType(TypeCarte typeCarte) {
        return this.carteDao.getCarteByType(typeCarte);
    }
    @Override
    public List<Carte> getCarteByClasse(Classe classe) {
        return this.carteDao.getCarteByClasse(classe);
    }

    @Override
    public Map<java.time.Month, List<Carte>> getCarteByMonth() {
        return this.carteDao.getCarteByMonth();
    }

    @Override
    public void supprimerCarte(Carte carte) throws BusinessException {
        try {
            if (this.carteDao.exists(carte.getId())) {
                this.carteDao.delete(carte.getId());
            }
        } catch (DaoException e) {
            throw new BusinessException("Erreur suppression des cartes");
        }
    }



}
