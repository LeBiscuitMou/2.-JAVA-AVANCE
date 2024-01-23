package net.ent.etrs.consoElecgaz.models.daos.impl;

import net.ent.etrs.consoElecgaz.models.daos.DaoOperateur;
import net.ent.etrs.consoElecgaz.models.daos.exception.DaoException;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class DaoImplOperateur extends AbstractJpaDao<Operateur, Serializable> implements DaoOperateur {

    @Override
    public List<Operateur> operateurByFiliere(Filiere filiere) throws DaoException {
        try {
            TypedQuery<Operateur> requete = em.createQuery(
                    "SELECT o FROM Operateur o WHERE o.filiere = :filiere", Operateur.class);
            requete.setParameter("filiere", filiere);

            return requete.getResultList();
        } catch (Exception e) {
            throw new DaoException("Erreur lors de la récupération des opérateurs pour la filière : " + filiere, e);
        }
    }
}