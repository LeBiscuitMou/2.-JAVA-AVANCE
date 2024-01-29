package view.converter;

import java.util.Objects;

import javafx.util.StringConverter;
import model.entities.Commande;

public class CommandeConverter extends StringConverter<Commande>{

	// Method to convert a Person-Object to a String
    @Override
    public String toString(Commande c)
    {
    		return Objects.isNull(c)? "" : String.format("%s : %.2f",c.getDateCommande(),c.totalCommande());
    }

	@Override
	public Commande fromString(String string) {
		return null;
	}
}
