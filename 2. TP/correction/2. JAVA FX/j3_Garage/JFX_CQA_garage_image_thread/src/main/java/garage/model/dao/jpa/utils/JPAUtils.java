package garage.model.dao.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Classe utilitaire capable de donner l'instance d'EntityManager en échange
 *  du nom de l'unité de persistence ( cf. votre persistence.xml)
 * @author nicolas.magniez
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JPAUtils {
	
	private static EntityManager em;

	public synchronized static EntityManager getEm(String unitName)  {
		if ( em==null){
			em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
		}
		return em;
	}
}
