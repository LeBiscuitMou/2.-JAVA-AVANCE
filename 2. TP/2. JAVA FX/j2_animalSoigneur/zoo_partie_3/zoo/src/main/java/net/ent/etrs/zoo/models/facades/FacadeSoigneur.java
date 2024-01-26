package net.ent.etrs.zoo.models.facades;

import net.ent.etrs.zoo.models.dao.SoigneurDao;
import net.ent.etrs.zoo.models.dao.impl.SoigneurDaoImpl;
import net.ent.etrs.zoo.models.entities.Soigneur;
import net.ent.etrs.zoo.models.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeSoigneur {

    private SoigneurDao dao;

    public FacadeSoigneur() {
        this.dao = new SoigneurDaoImpl();
    }

    public void save(Soigneur soigneur) throws BusinessException {
        try {
            this.dao.save(soigneur);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public void delete(Soigneur soigneur) throws BusinessException {
        try {
            this.dao.delete(soigneur.getId());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Set<Soigneur> findAll() throws BusinessException {
        try {
            return new HashSet<>(IterableUtils.toList(this.dao.findAll()));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
