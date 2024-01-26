package net.ent.etrs.projetjavamaier.models.daos.base;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.projetjavamaier.commons.utils.JpaUtil;
import net.ent.etrs.projetjavamaier.models.daos.exception.DaoException;
import net.ent.etrs.projetjavamaier.models.entities.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import java.util.Optional;
public abstract class JpaBaseDao<T extends AbstractEntity> implements BaseDao<T> {

    protected Class<T> entityClass;

    @Getter
    @Setter
    protected EntityManager em;


    protected JpaBaseDao() {
        this.setEm(JpaUtil.getEm());
        ParameterizedType genericSuperClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    /**
     * Permet la sauvegarde en BDD d'une entité.
     *
     * @param entity à persister en BDD
     * @return l'entité persisté
     * @throws DaoException si une persistenceException ou une IllegalArgumentException est catch
     */
    @Override
    public T save(T entity) throws DaoException {
        EntityTransaction et = this.em.getTransaction();
        try {
            et.begin();
            if (Objects.isNull(entity.getId())) {
                em.persist(entity);
            } else {
                entity = this.em.merge(entity);
            }
            et.commit();
            return entity;
        } catch (PersistenceException | IllegalArgumentException e) {
            et.rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> find(Long id) throws DaoException {
        try {
            return Optional.ofNullable(this.em.find(this.entityClass, id));
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override

    public Iterable<T> findAll() throws DaoException {
        try {
            return this.em.createQuery("SELECT t FROM " + this.entityClass.getSimpleName() + " t", this.entityClass).getResultList();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        EntityTransaction et = this.em.getTransaction();
        try {
            et.begin();
            Query query = this.em.createQuery("DELETE FROM " + this.entityClass.getSimpleName() + " t WHERE t.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            et.commit();
        } catch (PersistenceException | IllegalArgumentException e) {
            et.rollback();
            throw new DaoException(e);
        }
    }


    @Override
    public boolean exists(Long id) throws DaoException {
        return this.find(id).isPresent();
    }

    @Override
    public long count() throws DaoException {
        try {
            return this.em.createQuery("SELECT COUNT(t) FROM " + this.entityClass.getSimpleName() + " t", Long.class).getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

}