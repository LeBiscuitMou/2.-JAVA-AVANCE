package net.ent.etrs.hockey.models.dao;

import net.ent.etrs.hockey.models.dao.base.BaseDao;
import net.ent.etrs.hockey.models.entities.Joueur;

import java.time.LocalDate;
import java.util.Set;

public interface DaoJoueur extends BaseDao<Joueur> {
    Set<Joueur> findAllJoueurBirthBefore(LocalDate date);

    Joueur findJoueurBestScoreur();
}
