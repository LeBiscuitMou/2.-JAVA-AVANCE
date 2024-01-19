package net.ent.etrs.jeuVideo.model.dao;

import net.ent.etrs.jeuVideo.model.dao.base.BaseDao;
import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

public interface IDaoConsole extends BaseDao<Console> {

    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays);
}