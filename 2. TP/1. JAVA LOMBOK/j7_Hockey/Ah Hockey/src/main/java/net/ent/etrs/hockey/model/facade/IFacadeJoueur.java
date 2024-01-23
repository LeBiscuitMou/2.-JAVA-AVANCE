package net.ent.etrs.hockey.model.facade;

import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;
import net.ent.etrs.hockey.model.facade.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface IFacadeJoueur {
    void saveJoueur(Joueur joueur) throws BusinessException;
    void deleteJoueur(Long idJoueur) throws BusinessException;
    Set<Joueur> findAllJoueurs() throws BusinessException;
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date) throws BusinessException;

    Joueur findJoueurBestScoreur() throws BusinessException;
}
