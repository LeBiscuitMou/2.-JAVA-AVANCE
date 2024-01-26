package garage.model.dao.services;

import garage.model.entities.Marque;
import garage.model.facade.FacadeFactory;
import garage.model.facade.IFacadeMetier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ListingMarquesService extends Service<ObservableList<Marque>>{
	
	
	
	private ObservableList<Marque> oLstMarque = FXCollections.observableArrayList();
	
	private IFacadeMetier leMetier = FacadeFactory.fabriquerFacadeMetier();
	
	@Override
	protected Task<ObservableList<Marque>> createTask() {
		return new Task<ObservableList<Marque>>(){

			@Override
			protected ObservableList<Marque> call() throws Exception {
				//Demo pour bien se rendre compte que la tache est éxécutée ailleurs que dans le JAVA FX Apllication Thead
				//Thread.sleep(10000);
				for (Marque m : leMetier.listerMarques()) {
					oLstMarque.add(m);
				}	
				for (Marque m : oLstMarque) {
					System.out.println(m.getLibelle());
				}
				return oLstMarque;
			}
			
		};
}
}
