package garage.model.dao.services;

import garage.model.entities.Voiture;
import garage.model.facade.FacadeFactory;
import garage.model.facade.IFacadeMetier;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SuppressionVoitureService extends Service<Void>{
	
	private Voiture laVoiture;	
	private IFacadeMetier leMetier = FacadeFactory.fabriquerFacadeMetier();
	
	public SuppressionVoitureService(Voiture voitureSupprimee) {
		this.laVoiture=voitureSupprimee;
	}
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				//Demo pour bien se rendre compte que la tache est éxécutée ailleurs que dans le JAVA FX Apllication Thead
				//Thread.sleep(10000);
				leMetier.supprimerUneVoiture(laVoiture);
				return null;
			}
			
		};
	
	}
	
}
