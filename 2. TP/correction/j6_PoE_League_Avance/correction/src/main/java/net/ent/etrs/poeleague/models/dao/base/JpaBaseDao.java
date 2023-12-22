package net.ent.etrs.poeleague.models.dao.base;

import lombok.Getter;
import net.ent.etrs.poeleague.models.dao.exceptions.DaoException;
import net.ent.etrs.poeleague.models.dao.utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public abstract class JpaBaseDao<T> implements BaseDao<T> {

    private final Class<T> entityClass;
    @Getter
    protected EntityManager em = JpaUtils.getEm("pu-poe");

    public JpaBaseDao() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) throws DaoException {
        EntityTransaction t = null;
        try {
            t = this.em.getTransaction();
            t.begin();
            T retour;
            if (this.em.contains(entity)) {
                retour = this.em.merge(entity);
            } else {
                this.em.persist(entity);
                retour = entity;
            }
            t.commit();
            return retour;
        } catch (Exception e) {
            t.rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> find(Long id) throws DaoException {
        try {
            return Optional.ofNullable(this.em.find(this.entityClass, id));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Iterable<T> findAll() throws DaoException {
        try {
            return this.em.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e", this.entityClass).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        EntityTransaction t = null;
        try {
            t = this.em.getTransaction();
            t.begin();
            this.em.remove(entity);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public Long count() throws DaoException {
        try {
            return this.em.createQuery("SELECT COUNT(e) FROM " + this.entityClass.getSimpleName() + " e", Long.class).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean exists(Long id) throws DaoException {
        try {
            return this.find(id).isPresent();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
