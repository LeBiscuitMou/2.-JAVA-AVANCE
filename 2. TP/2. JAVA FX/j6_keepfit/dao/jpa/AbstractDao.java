package keepfit.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class AbstractDao{

	protected static EntityManager em = Persistence.createEntityManagerFactory("keepfit").createEntityManager();

}
