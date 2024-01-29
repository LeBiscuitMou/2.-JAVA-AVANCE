package model.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
	
	public static DaoClient fabriquerDaoClient() {
		return new DaoClientJPAImpl();
	}

	public static DaoProduit fabriquerDaoProduit() {
		return new DaoProduitJPAImpl();
	}

	public static DaoCommande fabriquerDaoCommande() {
		return new DaoCommandeJPAImpl();
	}

	
}
