package garage.model.dao;

import java.util.UUID;

import javax.persistence.EntityManager;

import garage.model.dao.jpa.utils.JPAUtils;

public abstract class AbstractDao<T> {
	
	protected final static EntityManager em = JPAUtils.getEm("04JFX_Garage");
	
	
	public void create(T t) {
		//ecrire en base
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

	}

	public void delete(T t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	


	public T read(UUID cle) {
		return (T) em.find(this.getClass(), cle);
	}

	public void update(T t){
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}
	
}
