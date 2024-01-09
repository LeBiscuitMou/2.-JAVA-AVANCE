package net.ents.etrs.projethockey.model.facade.impl;

import net.ents.etrs.projethockey.model.dao.IDaoJoueur;
import net.ents.etrs.projethockey.model.dao.exceptions.DaoException;
import net.ents.etrs.projethockey.model.entities.Equipe;
import net.ents.etrs.projethockey.model.entities.Joueur;
import net.ents.etrs.projethockey.model.facade.IFacadeJoueur;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FacadeJoueurImpl implements IFacadeJoueur {

    IDaoJoueur iDaoJoueur;

    @Override
    public void saveJoueur(Joueur joueur) {
        try {
            iDaoJoueur.save(joueur);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJoueur(Long idJoueur) {

        try {
            Optional<Joueur> joueur = iDaoJoueur.find(idJoueur);

            if(joueur.isPresent()){
                iDaoJoueur.delete(joueur.get());
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Joueur> findAllJoueurs() {
        try {
            return new HashSet<>(IterableUtils.toList(iDaoJoueur.findAll()));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) {
        try {
            return iDaoJoueur.findAllJoueurBirthBefore(date);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Joueur findJoueurBestScoreur() {
        try {
            return iDaoJoueur.findJoueurBestScoreur();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        try {
            return iDaoJoueur.findBestJoueurByTeamForYearAndName(annee, nomChampionnat);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
