package garage.model.dao.services;

import java.util.List;

import garage.model.entities.Voiture;
import garage.model.facade.FacadeFactory;
import garage.model.facade.IFacadeMetier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


/**
 * Classe de service qui effectue une requete d'interrogation en bdd sur la table des voitures
 * Ce service sera lancé dans un autre thread( different de JAVAFX Applicatino Thread)
 * @author codeur
 *
 */
public class ListingVoituresService extends Service<ObservableList<Voiture>> {
	
	private ObservableList<Voiture> oLstVoitures = FXCollections.observableArrayList();
	private IFacadeMetier leMetier = FacadeFactory.fabriquerFacadeMetier();
	
	@Override
	protected Task<ObservableList<Voiture>> createTask() {
		return new Task<ObservableList<Voiture>>(){

			@Override
			protected ObservableList<Voiture> call() throws Exception {
				//Demo pour bien se rendre compte que la tache est éxécutée ailleurs
				//que dans le JAVA FX Apllication Thead
//				Thread.sleep(5000);
				List<Voiture> lstVoitures = leMetier.listerVoitures();
				for (int i =0; i < lstVoitures.size(); i++) {
					Thread.sleep(2000);
					oLstVoitures.add(lstVoitures.get(i));
					this.updateProgress(i+1, lstVoitures.size());
					//updateValue(oLstVoitures);
				}					
				return oLstVoitures;
			}			
		};	
	}
}
