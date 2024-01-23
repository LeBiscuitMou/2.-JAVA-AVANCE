package net.ent.etrs.hockey.model.facade.impl;

import net.ent.etrs.hockey.model.dao.IDaoEquipe;
import net.ent.etrs.hockey.model.dao.impl.DaoFactory;
import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.exceptions.DaoException;
import net.ent.etrs.hockey.model.facade.IFacadeEquipe;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FacadeEquipeImpl implements IFacadeEquipe {
    IDaoEquipe daoEquipe;
    public FacadeEquipeImpl() {
        daoEquipe = DaoFactory.getDaoEquipe();
    }

    @Override
    public void saveEquipe(Equipe equipe) throws BusinessException {
        try {
            daoEquipe.save(equipe);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteEquipe(Long idEquipe) throws BusinessException {
        try {
            daoEquipe.delete(idEquipe);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Equipe> findAllEquipes() throws BusinessException {
        try {
            return Collections.unmodifiableSet(new HashSet<>(IterableUtils.toList(daoEquipe.findAll())));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) {
        return daoEquipe.findAllByYearAndName(annee, nomChampionnat);
    }

    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) {
        return daoEquipe.findBestEquipeByYearAndName(annee, nomChampionnat);
    }

    @Override
    public Equipe findEquipeByName(String name) throws BusinessException {
        return daoEquipe.findEquipeByName(name);
    }

    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        return daoEquipe.findBestJoueurByTeamForYearAndName(annee, nomChampionnat);
    }
}