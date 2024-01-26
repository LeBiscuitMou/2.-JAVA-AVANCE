package net.ent.etrs.projetjavamaier.models.daos;

import net.ent.etrs.projetjavamaier.models.daos.base.BaseDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import java.util.List;
import java.util.Map;

public interface ConsoleDao extends BaseDao<Console> {
    Console getLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException;

    Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException;

    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws DaoException;

    Console findByName(String s) throws DaoException;
}