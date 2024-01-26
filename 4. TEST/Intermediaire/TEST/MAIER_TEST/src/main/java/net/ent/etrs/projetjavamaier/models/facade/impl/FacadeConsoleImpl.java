package net.ent.etrs.projetjavamaier.models.facade.impl;

import net.ent.etrs.projetjavamaier.models.daos.DaoFactory;
import net.ent.etrs.projetjavamaier.models.daos.ConsoleDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.ConsoleFacade;
import net.ent.etrs.projetjavamaier.models.facade.base.AbstractFacade;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Map;


public class FacadeConsoleImpl extends AbstractFacade<Console> implements ConsoleFacade {

    ConsoleDao daoConsole;

    public FacadeConsoleImpl() {
        this.daoConsole = DaoFactory.getConsoleDao();
    }

    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException {
        try {
            return daoConsole.getLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays( pays);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer la console dont la sortie est la plus ancienne pour un pays", e);
        }
    }

    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException {
        try {
            return daoConsole.recupererConsoleAvecLeurJeuxVideo();
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer la console avec ses jeux videos", e);
        }
    }

    @Override
    public String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException {
        try {
            return daoConsole.recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(console);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer la duree moyenne de sortie entre deux jeux video pour une console", e);
        }
    }
}