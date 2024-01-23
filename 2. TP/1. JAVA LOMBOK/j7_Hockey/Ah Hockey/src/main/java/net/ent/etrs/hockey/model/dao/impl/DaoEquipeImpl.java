package net.ent.etrs.hockey.model.dao.impl;

import net.ent.etrs.hockey.model.dao.IDaoEquipe;
import net.ent.etrs.hockey.model.dao.base.AbstractJpaDao;
import net.ent.etrs.hockey.model.entities.Equipe;
import net.ent.etrs.hockey.model.entities.Joueur;

import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.Set;

public class DaoEquipeImpl extends AbstractJpaDao<Equipe, Long> implements IDaoEquipe {
    @Override
    public Set<Equipe> findAllByYearAndName(Integer annee, String nomChampionnat) {
        TypedQuery<Equipe> tp = this.em.createQuery(
                "SELECT e FROM Equipe e " +
                        "WHERE e.nom = :nom "
                        , Equipe.class);

        return null;
    }

    @Override
    public Equipe findBestEquipeByYearAndName(Integer annee, String nomChampionnat) {
        return null;
    }

    @Override
    public Equipe findEquipeByName(String name) {
        TypedQuery<Equipe> tp = this.em.createQuery(
                "SELECT e FROM Equipe e " +
                        "WHERE e.nom = :nom", Equipe.class);
        tp.setParameter("nom", name);
        return tp.getSingleResult();
    }

    @Override
    public Map<Equipe, Joueur> findBestJoueurByTeamForYearAndName(Integer annee, String nomChampionnat) {
        return null;
    }
}