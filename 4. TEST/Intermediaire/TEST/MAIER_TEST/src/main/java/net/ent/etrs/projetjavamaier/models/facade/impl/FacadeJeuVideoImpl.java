package net.ent.etrs.projetjavamaier.models.facade.impl;

import net.ent.etrs.projetjavamaier.models.daos.DaoFactory;
import net.ent.etrs.projetjavamaier.models.daos.JeuVideoDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.JeuVideoFacade;
import net.ent.etrs.projetjavamaier.models.facade.base.AbstractFacade;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.List;


public class FacadeJeuVideoImpl extends AbstractFacade<JeuVideo> implements JeuVideoFacade {

    JeuVideoDao daoJeuVideo;

    public FacadeJeuVideoImpl() {
        this.daoJeuVideo = DaoFactory.getJeuVideoDao();
    }

    @Override
    public List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException {
        try {
            return daoJeuVideo.getJeuVideoParGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les jeux videos avec leur genre", e);
        }
    }

    @Override
    public List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException {
        try {
            return daoJeuVideo.getJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(debutNom,paysFabriquant);
        } catch (DaoException e) {
            throw new BusinessException("Impossible de récupérer les jeux videos avec leur nom et le pays du fabriquant ", e);
        }
    }
}