package net.ent.etrs.projet.views.commons.javaFX;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.entities.AbstractEntity;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.start.Lanceur;
import net.ent.etrs.projet.views.controllers.interfaces.ListDisplayerController;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextMenuCreator {

    public static <T extends AbstractEntity, R> void ajouterContextMenu(ListDisplayerController<T, R> controller) {
        controller.getTableView().setRowFactory(tableView -> {
            final TableRow<T> row = new TableRow<>();
            final ContextMenu rowMenu = createContextMenu(controller);

            // only display context menu for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(rowMenu));
            return row;
        });
    }

    private static <T extends AbstractEntity, R> ContextMenu createContextMenu(ListDisplayerController<T, R> controller) {
        final ContextMenu rowMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("modifier");
        editItem.setOnAction(a -> modifier(controller));

        MenuItem removeItem = new MenuItem("supprimer");
        removeItem.setOnAction(a -> supprimer(controller));

        rowMenu.getItems().addAll(editItem, removeItem);
        return rowMenu;
    }

    @FXML
    private static <T extends AbstractEntity, R> void supprimer(ListDisplayerController<T, R> controller){
        T entity = controller.getTableView().getSelectionModel().getSelectedItem();
        if(null != entity){
            try {
                controller.getFMet().delete(entity.getId());
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
            controller.initialize();
        }
    }

    @FXML
    private static <T extends AbstractEntity, R> void modifier(ListDisplayerController<T, R> controller){
        try {
            T entity = controller.getFMet().find(controller.getTableView().getSelectionModel().getSelectedItem().getId()).orElse(null);
            if(null != entity){
                Lanceur.loadFXML("fiche" + controller.getClassName(), controller.getCaller().apply(entity));
                controller.initialize();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
