package garage.model.dao;

import java.util.List;

/**
 * Interface CRUD générique
 * @author c.louer
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
	public void create(T t);
	/**
	 * Méthode proposant la lecture d'un objet persisté à partir de la clé primaire.
	 * @param k
	 * clé primaire
	 * @return
	 * Objet lu
	 */
	public T read(K k);
	/**
	 * Méthode proposant la suppression de l'objet passé en paramètre dans le contexte de persistance.
	 * @param t
	 * Objet à supprimer
	 * Objet présent dans le contexte de persistance à supprimer.
	 */
	public void delete(T t);
	/**
	 * Méthode proposant la modification d'un objet existant dans le contexte de persistance.
	 * @param t
	 * Objet à modifier
	 * Objet présent dans le contexte de persistance à modifier.
	 */
	public void update(T t);
	/**
	 * Méthode proposant la lecture de l'ensemble des objets présents dans le contexte de persistance.
	 * @return
	 * Retourne une liste contenant l'ensemble des objets présents dans le contexte de persistance.
	 */
	public List<T> readAll();
	/**
	 * Méthode testant l'existance de l'objet passé en paramètre dans le contexte de persistance.
	 * @param t
	 * Objet recherché.
	 * @return
	 * Retourne 'true' si l'objet existe dans le contexte de persistance, 'false' sinon.
	 */
	public boolean exist(T t);
	
	
		
}
