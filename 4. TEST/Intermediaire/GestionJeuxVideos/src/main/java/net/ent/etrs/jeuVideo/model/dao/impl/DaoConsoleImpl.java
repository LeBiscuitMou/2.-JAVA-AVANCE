package net.ent.etrs.jeuVideo.model.dao.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoConsole;
import net.ent.etrs.jeuVideo.model.dao.base.JpaBaseDao;
import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;

import javax.persistence.Query;

public class DaoConsoleImpl extends JpaBaseDao<Console> implements IDaoConsole {
    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) {
        return null;
    }
}