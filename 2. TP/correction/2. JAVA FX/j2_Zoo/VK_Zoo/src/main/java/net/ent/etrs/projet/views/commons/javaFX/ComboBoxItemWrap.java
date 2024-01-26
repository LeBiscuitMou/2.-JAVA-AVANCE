package net.ent.etrs.projet.views.commons.javaFX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.MouseEvent;
import net.ent.etrs.projet.models.entities.references.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComboBoxItemWrap<T> {
    private BooleanProperty check = new SimpleBooleanProperty(false);
    private ObjectProperty<T> item = new SimpleObjectProperty<>();

    public ComboBoxItemWrap() {

    }

    public ComboBoxItemWrap(T item) {
        this.item.set(item);
    }

    public ComboBoxItemWrap(T item, Boolean check) {
        this.item.set(item);
        this.check.set(check);
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public Boolean getCheck() {
        return check.getValue();
    }

    public void setCheck(Boolean value) {
        check.set(value);
    }

    public ObjectProperty<T> itemProperty() {
        return item;
    }

    public T getItem() {
        return item.getValue();
    }

    public void setItem(T value) {
        item.setValue(value);
    }

    @Override
    public String toString() {
        return item.getValue().toString();
    }

    public static <T> List<ComboBoxItemWrap<T>> makeComboList(T[] array){
        List<ComboBoxItemWrap<T>> list = new ArrayList<>();
        for(T t : array){
            if(null != t){
                list.add(new ComboBoxItemWrap<>(t));
            }
        }
        return list;
    }

    public static <T> List<T> getAllChecked(ObservableList<ComboBoxItemWrap<T>> dataList){
        List<T> resultList = new ArrayList<>();
        for(ComboBoxItemWrap<T> combo : dataList){
            if(combo.getCheck()){
                resultList.add(combo.getItem());
            }
        }
        return resultList;
    }

    public static <T> ObservableList<ComboBoxItemWrap<T>> createComboWrap(ComboBox<ComboBoxItemWrap<T>> comboBox, T[] values) {
        ObservableList<ComboBoxItemWrap<T>> options = FXCollections.observableArrayList(ComboBoxItemWrap.makeComboList(values));

        ComboBoxListViewSkin<ComboBoxItemWrap<T>> skin = new ComboBoxListViewSkin<>(comboBox);
        skin.setHideOnClick(false);
        comboBox.setSkin(skin);

        comboBox.setCellFactory( c -> {
            ListCell<ComboBoxItemWrap<T>> cell = new ListCell<>(){
                @Override
                protected void updateItem(ComboBoxItemWrap<T> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        final CheckBox cb = new CheckBox(item.toString());
                        cb.selectedProperty().bind(item.checkProperty());
                        setGraphic(cb);
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                cell.getItem().checkProperty().set(!cell.getItem().checkProperty().get());
                StringBuilder sb = new StringBuilder();
                comboBox.getItems().filtered(Objects::nonNull).filtered(ComboBoxItemWrap::getCheck).forEach(p -> sb.append("; ").append(p.getItem()));
                final String string = sb.toString();
                comboBox.setPromptText(string.substring(Integer.min(2, string.length())));
            });

            return cell;
        });
        return options;
    }
}
