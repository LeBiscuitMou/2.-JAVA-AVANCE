package net.ent.etrs.consoElecgaz.models.daos;

import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;

import java.io.Serializable;
import java.util.List;

public interface DaoOperateur extends BaseDao<Operateur, Serializable> {
    List<Operateur> operateurByFiliere(Filiere filiere) throws DaoException;


}
