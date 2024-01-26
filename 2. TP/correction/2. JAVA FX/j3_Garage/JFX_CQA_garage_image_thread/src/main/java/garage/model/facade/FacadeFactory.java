package garage.model.facade;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {
	
	public static IFacadeMetier fabriquerFacadeMetier(){
		return new FacadeMetier();
	}
	
}

