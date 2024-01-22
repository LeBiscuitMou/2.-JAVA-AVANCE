package net.ent.etrs.jeuVideo.model.dao.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoConsole;
import net.ent.etrs.jeuVideo.model.dao.base.JpaBaseDao;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoConsoleImpl extends JpaBaseDao<Console> implements IDaoConsole {
    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException {
        try {
            TypedQuery<Console> query = this.em.createQuery(
                    """
                            SELECT c FROM Console c
                            WHERE VALUE(c.sorties) <= (SELECT MIN(VALUE(c1.sorties))
                                                       FROM Console c1
                                                       WHERE c1.fabriquant.pays = :pays)
                            """
                    , Console.class);
            query.setParameter("pays", pays);
            return query.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException {
        try {
            TypedQuery<Console> query = this.em.createQuery(
                    """
                            SELECT j FROM JeuVideo j
                            JOIN Console c ON 
                            """
                    , Console.class);
            query.setParameter("annee", annee);
            query.setParameter("nom", nomChampionnat);

            return query.getResultStream().collect(Collectors.toMap(
                    tuple -> ((Console) tuple.get("console")),
                    tuple -> ((List<JeuVideo>) tuple.get("joueurs"))
            ));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    @Override
    public String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) {
        return null;
    }
}