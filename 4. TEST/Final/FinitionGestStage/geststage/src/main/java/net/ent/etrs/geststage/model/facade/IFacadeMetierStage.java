package net.ent.etrs.geststage.model.facade;

import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.facade.exceptions.BusinessException;

import java.util.Set;

public interface IFacadeMetierStage {
    void creerStage(Stage stage) throws BusinessException;

    void supprimerStage(Stage stage) throws BusinessException;

    Set<Stage> recupererTousLesStages() throws BusinessException;
}