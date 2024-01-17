package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.IDaoClient;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Client;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ClientDaoJpaImpl extends JpaBaseDao<Client> implements IDaoClient {

    @Override
    public Optional<Client> findClientByIdentity(String clientNom, String clientPrenom) throws DaoException {
        try {
            TypedQuery<Client> query = this.em.createQuery("SELECT t " +
                            "FROM Client t " +
                            "WHERE t.nom = :clientNom AND " +
                                    "t.prenom = :clientPrenom"
                    , Client.class);
            query.setParameter("clientNom", clientNom);
            query.setParameter("clientPrenom", clientPrenom);


            query.setMaxResults(1);
            return Optional.ofNullable(query.getSingleResult());

        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }catch (NoResultException e){
            return  Optional.empty();
        }
    }
}
