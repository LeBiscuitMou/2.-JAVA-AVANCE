package net.ents.etrs.projethockey.model.facade.impl;

import net.ents.etrs.projethockey.model.dao.IDaoEquipe;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.facade.IFacadeEquipe;
import net.ents.etrs.projethockey.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FacadeEquipeImpl implements IFacadeEquipe {
    IDaoEquipe daoEquipe;
    @Override
    public void saveEquipe(Equipe equipe) {

        try {
            daoEquipe.save(equipe);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteEquipe(Long idEquipe) {

        try {
            Optional<Equipe> equipe = daoEquipe.find(idEquipe);


            if(equipe.isPresent()){
                daoEquipe.delete(equipe.get());
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Set<Equipe> findAllEquipes() {
        try {
            return new HashSet<>(IterableUtils.toList(daoEquipe.findAll()));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Equipe> findAllEquipeByYearAndName(Integer annee, String nomChampionnat) {


        try {
            return this.daoEquipe.findAllEquipeByYearAndName(annee, nomChampionnat);
        }catch (DaoException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) {
        try {
            return daoEquipe.findBestEquipeByYearAndName(annee, nomChampionnat);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        try {
            return daoEquipe.findBestJoueurByTeamForYearAndName(annee,nomChampionnat);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Equipe findEquipeByName(String name) throws BusinessException {
        try {
            return daoEquipe.findEquipeByName(name);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
