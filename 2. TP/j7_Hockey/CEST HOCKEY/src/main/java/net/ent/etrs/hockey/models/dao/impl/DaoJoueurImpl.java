package net.ent.etrs.hockey.models.dao.impl;

import net.ent.etrs.hockey.models.dao.DaoJoueur;
import net.ent.etrs.hockey.models.dao.base.JpaBaseDao;
import net.ent.etrs.hockey.models.entities.Joueur;

import java.time.LocalDate;
import java.util.Set;

public class DaoJoueurImpl extends JpaBaseDao<Joueur> implements DaoJoueur {
    @Override
    public Set<Joueur> findAllJoueurBirthBefore(LocalDate date) {
        return null;
    }

    @Override
    public Joueur findJoueurBestScoreur() {
        return null;
    }
}