package net.ent.etrs.conso_elec_gaz.model.daos.impl;

import net.ent.etrs.conso_elec_gaz.model.daos.DaoOperateur;
import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.Operateur;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoOperateurImplJPA extends AbstractJpaDao<Operateur> implements DaoOperateur {
    @Override
    public Operateur findByNomAndFiliere(String nom, Filiere filiere) throws DaoException {
        try {
            TypedQuery<Operateur> tp = this.em.createQuery(
                    "SELECT o FROM Operateur o " +
                            "WHERE o.nom = :nom " +
                            "AND o.filiere = :filiere", Operateur.class);
            tp.setParameter("nom", nom);
            tp.setParameter("filiere", filiere);
            return tp.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Operateur> getOperateurByFiliere(Filiere filiere) throws DaoException {
        try {
        TypedQuery<Operateur> tp = this.em.createQuery(
                "SELECT o FROM Operateur o " +
                        " WHERE o.filiere = :filiere", Operateur.class);
        tp.setParameter("filiere", filiere);
        return tp.getResultList();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
