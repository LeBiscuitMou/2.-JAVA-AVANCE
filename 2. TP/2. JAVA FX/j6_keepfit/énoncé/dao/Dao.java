package keepfit.model.dao;

import java.util.List;

/**
 * Interface CRUD générique
 * @author codeur
 *
 * @param <T>
 * Type de classe à persister
 * @param <K>
 * Clé définie pour la classe (equals & hashCode)
 */
public interface Dao<T,K> {	
	
	
	/**
	 * Méthode proposant la création de l'objet passé en paramètre dans le contexte de persistance.
	 * @param t
	 * Objet à persister.
	 */
	public default void create(T t) throws DaoException{
		throw new UnsupportedOperationException();
	}
	/**
	 * Méthode proposant la lecture d'un objet persisté à partir de la clé primaire.
	 * @param k
	 * clé primaire
	 * @return
	 * Objet lu
	 */
	public default T read(K k) throws DaoException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Méthode proposant la lecture de l'ensemble des objets présents dans le contexte de persistance.
	 * @return
	 * Retourne une liste contenant l'ensemble des objets présents dans le contexte de persistance.
	 */
	public default List<T> readAll() throws DaoException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Méthode proposant la suppression de l'objet passé en paramètre dans le contexte de persistance.
	 * @param t
	 * Objet à supprimer
	 * Objet présent dans le contexte de persistance à supprimer.
	 */
	
	public default void delete(T t) throws DaoException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Méthode proposant la mise à jour de l'objet passé en paramètre dans le contexte de persistance.
	 * @param t
	 * Objet à supprimer
	 * Objet présent dans le contexte de persistance à supprimer.
	 */
	
	public default void update(T t) throws DaoException{
		throw new UnsupportedOperationException();
	}	
}
