package net.ent.etrs.projet.views.controllers.interfaces;

import javafx.scene.control.TableView;
import net.ent.etrs.projet.models.entities.AbstractEntity;
import net.ent.etrs.projet.models.facades.interfaces.GenericFacade;

import java.util.function.Function;

public interface ListDisplayerController<T extends AbstractEntity, R> {
    void initialize();

    GenericFacade<T> getFMet();
    String getClassName();
    Function<T, R> getCaller();
    TableView<T> getTableView();

}
