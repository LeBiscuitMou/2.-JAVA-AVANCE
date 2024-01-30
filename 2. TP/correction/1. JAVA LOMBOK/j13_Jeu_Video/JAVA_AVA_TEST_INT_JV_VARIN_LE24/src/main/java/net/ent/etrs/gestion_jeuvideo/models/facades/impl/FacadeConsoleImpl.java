package net.ent.etrs.gestion_jeuvideo.models.facades.impl;

import net.ent.etrs.gestion_jeuvideo.models.commons.CoUtils;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoConsole;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.dao.impl.DaoFactory;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeConsole;

import java.util.List;
import java.util.Map;

public class FacadeConsoleImpl extends GenericFacadeImpl<Console> implements IFacadeConsole {
    private final IDaoConsole daoConsole;

    protected FacadeConsoleImpl() {
        super(DaoFactory.getDaoConsole());
        daoConsole = DaoFactory.getDaoConsole();
    }

    /**
     * Permet de sauvegarder une console.
     *
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     * @throws BusinessException l'exception lancée
     */
    @Override
    public Console sauvegarderConsole(Console console) throws BusinessException {
        return super.save(console);
    }

    /**
     * Permet de récupérer toutes les consoles.
     *
     * @return la liste de toutes les console
     * @throws BusinessException l'exception lancée
     */
    @Override
    public List<Console> recupererlesConsoles() throws BusinessException {
        return CoUtils.iterableToList(super.findAll()).stream().sorted().toList();
    }

    /**
     * Permet de récupérer la console qui est la plus vieille pour un pays.
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     * @throws BusinessException l'exception lancée
     */
    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException {
        try {
            return daoConsole.recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(pays);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Permet de récupérer un dictionnaire contenant toutes les consoles avec leurs jeux video.
     *
     * @return une map contenant toutes les consoles avec leurs jeux video
     * @throws BusinessException l'exception lancée
     */
    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException {
        try {
            return daoConsole.recupererConsoleAvecLeurJeuxVideo();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}