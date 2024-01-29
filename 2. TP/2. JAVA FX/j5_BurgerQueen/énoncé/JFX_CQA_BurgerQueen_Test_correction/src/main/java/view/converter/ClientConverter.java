package view.converter;

import javafx.util.StringConverter;
import model.entities.Client;

public class ClientConverter extends StringConverter<Client> {

    // Method to convert a Person-Object to a String
    @Override
    public String toString(Client client) {
        return client == null ? null : String.format("%s %s", client.getNom(), client.getPrenom());
    }

    @Override
    public Client fromString(String string) {
        return null;
    }
}
