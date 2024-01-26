package common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 * @author christophe.cerqueira
 *
 */
public class REQ {

	public static final String CLIENT_BY_NOM_PRENOM = " SELECT c FROM Client c WHERE c.nom = ?1 AND c.prenom = ?2 ";
	public static final String PRODUIT_BY_NOM_TYPE_TAILLE = " SELECT c FROM Produit c WHERE c.nom = ?1 AND c.typeProduit =?2 AND c.tailleProduit = ?3 ";
	public static final String COMMANDE_PAR_CLIENT = " SELECT c FROM Commande c WHERE  c.client = :client";
	public static final String COMMANDE_READ_ALL = " SELECT c FROM Commande c"; 
	
}
