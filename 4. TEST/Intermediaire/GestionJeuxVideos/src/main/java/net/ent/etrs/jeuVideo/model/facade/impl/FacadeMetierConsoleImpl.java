package net.ent.etrs.jeuVideo.model.facade.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoConsole;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.dao.impl.DaoFactory;
import net.ent.etrs.jeuVideo.model.entities.Console;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierConsole;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FacadeMetierConsoleImpl implements IFacadeMetierConsole {
    private IDaoConsole daoConsole;

    public FacadeMetierConsoleImpl() {
        daoConsole = DaoFactory.getDaoConsole();
    }

    /**
     * Permet de sauvegarder une console
     *
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     */
    @Override
    public Console sauvegarderConsole(Console console) throws BusinessException {
        try {
            return daoConsole.save(console);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_SAVE + Console.class.getSimpleName(), e);
        }
    }

    /**
     * Permet de récupérer toutes les consoles
     *
     * @return la liste de toutes les console
     */
    @Override
    public List<Console> recupererlesConsoles() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoConsole.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_FIND + Console.class.getSimpleName(), e);
        }
    }

    /**
     * Permet de récuperer la console qui est la plus vieille pour un pays
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     */
    @Override
    public Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException {
        try {
            return daoConsole.recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(pays);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récuperer un dictionnaire contenant toutes les consoles avec leur jeux video
     *
     * @return une map contenant toutes les consoles avec leur jeux video
     */
    @Override
    public Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException {
        try {
            return daoConsole.recupererConsoleAvecLeurJeuxVideo();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de renvoyer une chaîne de caractère au format suivant : X années, Y mois, Z jours arrondi au jours près supérieur
     * donnant la durée moyenne entre 2 sortie de jeux vidéo pour une console
     *
     * @param console la console choisie
     * @return une chaine de caractère qui donne la durée moyenne entre 2 sortie de jeux vidéo pour une console
     */
    @Override
    public String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException {
        return daoConsole.recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(console);
    }
}