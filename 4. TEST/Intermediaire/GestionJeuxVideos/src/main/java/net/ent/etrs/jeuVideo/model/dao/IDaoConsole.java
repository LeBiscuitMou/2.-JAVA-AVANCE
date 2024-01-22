package net.ent.etrs.jeuVideo.model.dao;

import net.ent.etrs.jeuVideo.model.dao.base.BaseDao;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import java.util.List;
import java.util.Map;

public interface IDaoConsole extends BaseDao<Console> {

    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws DaoException;

    Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws DaoException;

    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console);
}