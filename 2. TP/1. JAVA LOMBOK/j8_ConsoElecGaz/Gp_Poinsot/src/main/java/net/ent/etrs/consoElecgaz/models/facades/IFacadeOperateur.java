package net.ent.etrs.consoElecgaz.models.facades;

import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.util.List;

public interface IFacadeOperateur {
    Operateur sauvegarderOperateur(Operateur operateur) throws BusinessException;

    List<Operateur> operateurByFiliere(Filiere filiere) throws BusinessException, DaoException;
}
