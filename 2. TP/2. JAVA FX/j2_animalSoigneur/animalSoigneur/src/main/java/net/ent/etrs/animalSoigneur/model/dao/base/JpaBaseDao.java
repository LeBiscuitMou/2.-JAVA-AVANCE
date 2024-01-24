package net.ent.etrs.animalSoigneur.model.dao.base;


import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.animalSoigneur.model.commons.JpaUtil;
import net.ent.etrs.animalSoigneur.model.dao.exceptions.DaoException;
import net.ent.etrs.animalSoigneur.model.entities.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public abstract class JpaBaseDao<T extends AbstractEntity> implements BaseDao<T> {

    protected Class<T> entityClass;

    @Getter
    @Setter
    protected EntityManager em = JpaUtil.getEm();

    @SuppressWarnings("unchecked")
    public JpaBaseDao() {
        ParameterizedType genericSuperClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) throws DaoException {
        try {
            EntityTransaction et = this.em.getTransaction();
            et.begin();
            if (this.em.contains(entity)) {
                this.em.persist(entity);
            } else {
                entity = this.em.merge(entity);
            }
            et.commit();
            return entity;
        } catch (PersistenceException | IllegalArgumentException e) {
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
        try {
            EntityTransaction et = this.em.getTransaction();
            et.begin();
            Query query = this.em.createQuery("DELETE FROM " + this.entityClass.getSimpleName() + " t WHERE t.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            et.commit();
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean exists(Long id) throws DaoException {
        try {
            return this.em
                    .createQuery("SELECT COUNT(t) FROM " + this.entityClass.getSimpleName() + " t WHERE t.id = :id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult() > 0;
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
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
