package net.ent.etrs.projet.models.daos;


import net.ent.etrs.projet.models.daos.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.AbstractEntity;

import java.util.*;

public abstract class MemBaseDao<T extends AbstractEntity> implements BaseDao<T> {

    protected final Map<Long, T> persist = new HashMap<>();

    Long sequence = 0L;

    private Long incrementationAutoSequence() {
        return this.sequence++;
    }

    @Override
    public T save(T entity) throws DaoException {
        if (Objects.isNull(entity.getId()) && !this.persist.containsValue(entity)) {
            entity.setId(incrementationAutoSequence());
        }
        this.persist.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> find(Long id) throws DaoException {
        return Optional.ofNullable(this.persist.get(id));
    }

    @Override

    public Iterable<T> findAll() throws DaoException {
        return Collections.unmodifiableCollection(this.persist.values());
    }

    @Override
    public void delete(Long id) throws DaoException {
        this.persist.remove(id);
    }

    @Override
    public boolean exists(Long id) throws DaoException {
        return this.find(id).isPresent();
    }

    @Override
    public long count() throws DaoException {
        return this.persist.size();
    }
}
