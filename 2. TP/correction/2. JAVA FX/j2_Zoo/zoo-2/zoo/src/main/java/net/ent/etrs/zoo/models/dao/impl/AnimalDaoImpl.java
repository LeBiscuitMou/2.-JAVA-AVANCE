package net.ent.etrs.zoo.models.dao.impl;

import net.ent.etrs.zoo.models.dao.AnimalDao;
import net.ent.etrs.zoo.models.dao.exceptions.DaoException;
import net.ent.etrs.zoo.models.entities.Animal;

import javax.persistence.TypedQuery;

public class AnimalDaoImpl extends JpaBaseDao<Animal> implements AnimalDao {
    @Override
    public Iterable<Animal> findAllWithoutSoigneur() throws DaoException {
        try {
            TypedQuery<Animal> query = this.em.createQuery("""

                    SELECT a FROM Animal a\s
                    WHERE a NOT IN (\s
                    SELECT a2 FROM Soigneur s INNER JOIN s.animaux a2 WHERE s.id IS NOT NULL)
                    
                    """, Animal.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
