package net.ent.etrs.championnathockey.models.facades.impl;

import net.ent.etrs.championnathockey.models.daos.DaoEquipe;
import net.ent.etrs.championnathockey.models.daos.DaoFactory;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.FacadeEquipe;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FacadeEquipeImpl implements FacadeEquipe {

    DaoEquipe daoEquipe;

    public FacadeEquipeImpl() {
        this.daoEquipe = DaoFactory.getDaoEquipe();
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
            return new HashSet<>(IterableUtils.toList(daoEquipe.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de recuperer l'équipe par son nom
     *
     * @param name nom de l'équipe
     * @return l'équipe correspondant au nom
     * @throws BusinessException
     */
    @Override
    public Equipe findEquipeByName(String name) throws BusinessException {
        if (daoEquipe.findByName(name).isPresent()) {
            return daoEquipe.findByName(name).get();
        }
        return null;
    }

    /**
     * Permet de recuperer les équipe d'une année de championnat
     *
     * @param annee (Integer) année de championnat
     * @return un Set des Equipes de cette année de championnat
     */
    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) throws BusinessException {
        try {
            return daoEquipe.findAllEquipeByYearAndName(annee, nomChampionnat);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de réupérer l'équipe qui a marqué le plus de point pour cette année
     *
     * @param annee (Integer) année de championnat
     * @return la meilleur équipe de l'année de championnat
     */
    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) throws BusinessException {
        try {
            return daoEquipe.findBestEquipeByYearAndName(annee, nomChampionnat).orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récupérer le meilleur joueur par équipe
     *
     * @param annee
     * @return une Map<Equipe, Joueur> du meilleur joueur par équipe
     */
    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) throws BusinessException {
        try {
            return daoEquipe.findBestJoueurByTeamForYearAndName(annee, nomChampionnat);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
