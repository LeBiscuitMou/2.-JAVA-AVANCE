package garage.presenter;

import java.util.List;

import garage.model.entities.Marque;
import garage.model.exceptions.BusinessException;
import garage.view.utils.AlerteUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class StatsPresenter extends AbstractPresenter {
	@FXML
	private PieChart piechart;
	
	private final Label caption = new Label("");

	@FXML
	public void initialize() {
		try {
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			List<Marque> lstMarques = super.getLeMetier().listerMarques();
			for (Marque marque : lstMarques) {
				long nb = super.getLeMetier().compterVoituresParMarque(marque);
				PieChart.Data p = new PieChart.Data(marque.getLibelle(), nb);
				
//				p.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//	                @Override
//	                public void handle(MouseEvent e) {
//	                    caption.setTranslateX(e.getSceneX());
//	                    caption.setTranslateY(e.getSceneY());
//	 
//	                    caption.setText(String.valueOf(p.getPieValue()));
//	                }
//	            });
				
				pieChartData.add(p);
			}
			piechart.setData(pieChartData);
//			piechart.setLegendVisible(true);
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), AlertType.ERROR);
		}

	}
}
