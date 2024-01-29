package model.facade;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public final class FacadeFactory {

	/**
	 * Fabrique une nouvelle instance de ClientFacade.
	 * @return une instance de ClientFacade
	 */
	public static ClientFacade fabriquerClientFacade() {
		return new ClientFacadeImpl();
	}
	
	/**
	 * Fabrique une nouvelle instance de CommandeFacade.
	 * @return une instance de CommandeFacade
	 */
	public static CommandeFacade fabriquerCommandeFacade() {
		return new CommandeFacadeImpl();
	}

	/**
	 * Fabrique une nouvelle instance de ProduitFacade.
	 * @return une instance de ProduitFacade
	 */
	public static ProduitFacade fabriquerProduitFacade() {
		return new ProduitFacadeImpl();
	}
	
}
