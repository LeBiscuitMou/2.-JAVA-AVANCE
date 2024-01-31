package net.ent.etrs.geststage.model.facade.impl;

import net.ent.etrs.geststage.model.dao.IDaoStage;
import net.ent.etrs.geststage.model.dao.exceptions.DaoException;
import net.ent.etrs.geststage.model.dao.impl.DaoFactory;
import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.facade.IFacadeMetierStage;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeMetierStageImpl implements IFacadeMetierStage {
    private IDaoStage daoStage;

    protected FacadeMetierStageImpl() {
        daoStage = DaoFactory.getDaoStage();
    }

    @Override
    public void creerStage(Stage stage) throws BusinessException {
        try {
            daoStage.save(stage);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void supprimerStage(Stage stage) throws BusinessException {
        try {
            daoStage.delete(stage);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Stage> recupererTousLesStages() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(daoStage.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}