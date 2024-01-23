package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.commons.CoUtils;
import net.ent.etrs.judo.models.dao.IDaoClub;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.dao.impl.DaoFactory;
import net.ent.etrs.judo.models.entities.Club;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.facades.interfaces.FacadeClub;

import java.util.List;
import java.util.Optional;

public class FacadeClubImpl extends GenericFacadeImpl<Club> implements FacadeClub {
    protected IDaoClub daoClub;

    protected FacadeClubImpl() {
        super(DaoFactory.getDaoClub());
        daoClub = DaoFactory.getDaoClub();
    }

    @Override
    public Club saveClub(Club pClub) throws BusinessException {
        return super.save(pClub);
    }

    @Override
    public Optional<Club> findClub(Long pId) throws BusinessException {
        return super.find(pId);
    }

    @Override
    public List<Club> findAllClub() throws BusinessException {
        return CoUtils.iterableToList(super.findAll());
    }

    @Override
    public void deleteClub(Long pId) throws BusinessException {
        super.delete(pId);
    }

    /**
     * Trouve le club ayant le nom voulu et qui ont au moins 1 judoka.
     *
     * @param pName le nom du club
     * @return un club ou null si le club n'existe pas
     * @throws BusinessException erreur BDD
     */
    @Override
    public Optional<Club> findClubByNomWithJudokas(String pName) throws BusinessException {
        try {
            return daoClub.findClubByNomWithJudokas(pName);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Trouve tous les clubs basés dans la ville choisis
     *
     * @param pVille la ville choisis
     * @return une liste de clubs
     * @throws BusinessException erreur BDD
     */
    @Override
    public List<Club> findAllClubByVille(Ville pVille) throws BusinessException {
        try {
            return daoClub.findAllClubByVille(pVille);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
