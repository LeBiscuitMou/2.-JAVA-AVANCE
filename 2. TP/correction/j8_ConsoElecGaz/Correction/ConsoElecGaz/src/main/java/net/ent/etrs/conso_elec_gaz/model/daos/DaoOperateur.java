package net.ent.etrs.conso_elec_gaz.model.daos;

import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.Operateur;

import java.util.List;

public interface DaoOperateur extends BaseDao<Operateur> {
    Operateur findByNomAndFiliere(String nom, Filiere filiere) throws DaoException;

    List<Operateur> getOperateurByFiliere(Filiere filiere) throws DaoException;
}
