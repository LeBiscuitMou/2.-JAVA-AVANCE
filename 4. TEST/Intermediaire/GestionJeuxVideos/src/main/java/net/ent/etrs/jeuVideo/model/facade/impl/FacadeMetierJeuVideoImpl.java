package net.ent.etrs.jeuVideo.model.facade.impl;

import net.ent.etrs.jeuVideo.model.dao.IDaoJeuVideo;
import net.ent.etrs.jeuVideo.model.dao.exceptions.DaoException;
import net.ent.etrs.jeuVideo.model.dao.impl.DaoFactory;
import net.ent.etrs.jeuVideo.model.entities.Fabriquant;
import net.ent.etrs.jeuVideo.model.entities.JeuVideo;
import net.ent.etrs.jeuVideo.model.entities.references.ConstantesMetier;
import net.ent.etrs.jeuVideo.model.entities.references.Genre;
import net.ent.etrs.jeuVideo.model.entities.references.Pays;
import net.ent.etrs.jeuVideo.model.facade.IFacadeMetierJeuVideo;
import net.ent.etrs.jeuVideo.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Collections;
import java.util.List;

public class FacadeMetierJeuVideoImpl implements IFacadeMetierJeuVideo {
    private IDaoJeuVideo daoJeuVideo;

    public FacadeMetierJeuVideoImpl() {
        daoJeuVideo = DaoFactory.getDaoJeuVideo();
    }

    /**
     * Permet de sauvegarder un jeu video
     *
     * @param jv le jeu vidfeo à sauvegarder
     * @return le jeu video sauvegarder
     */
    @Override
    public JeuVideo sauvegarderJeuVideo(JeuVideo jv) throws BusinessException {
        try {
            return daoJeuVideo.save(jv);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_SAVE + JeuVideo.class.getSimpleName(), e);
        }
    }

    /**
     * Permet de récuperer tous les jeux video
     *
     * @return la liste de tous les jeux video
     */
    @Override
    public List<JeuVideo> recupererJeuxVideo() throws BusinessException {
        try {
            return Collections.unmodifiableList(IterableUtils.toList(daoJeuVideo.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.ERROR_FIND + JeuVideo.class.getSimpleName(), e);
        }
    }

    /**
     * Permet de récuperer tous les jeux video du genre choisi
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException {
        try {
            return daoJeuVideo.findAllGamesByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet de récuperer tous les jeux video dont le nom commence par : et dont le fabriquant est du pays selectionner
     *
     * @param debutNom       début du nom du jeu video
     * @param paysFabriquant pays du fabriquant
     * @return la liste des jeux video répondant aux filtres
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException {
        try {
            return daoJeuVideo.recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(debutNom, paysFabriquant);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}