package net.ent.etrs.projetjavamaier.models.daos.impl;

import net.ent.etrs.projetjavamaier.models.daos.JeuVideoDao;
import net.ent.etrs.projetjavamaier.models.daos.base.JpaBaseDao;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;


public class DaoJeuVideoImpl extends JpaBaseDao<JeuVideo> implements JeuVideoDao {

    @Override
    public List<JeuVideo> getJeuVideoParGenre(Genre genre) throws DaoException {
      try{
          TypedQuery<JeuVideo> query = this.em.createQuery("SELECT j FROM JeuVideo j WHERE j.genre = :genre",JeuVideo.class);
          query.setParameter("genre", genre);
          return query.getResultList();


      }catch (IllegalArgumentException | PersistenceException e) {
          throw new DaoException(e.getMessage(), e);
      }
    }

    @Override
    public List<JeuVideo> getJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws DaoException {
        try{
            TypedQuery<JeuVideo> query = this.em.createQuery("SELECT j FROM JeuVideo j JOIN Fabriquant f WHERE j.nom LIKE :debutNom AND f.pays =: pays",JeuVideo.class);
            query.setParameter("debutNom", debutNom+"%");
            query.setParameter("pays", paysFabriquant);
            return query.getResultList();

        }catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
