package garage.view.utils;

import garage.model.entities.Marque;
import javafx.util.StringConverter;

public class MarqueConverter extends StringConverter<Marque>{

	// Method to convert a Person-Object to a String
    @Override
    public String toString(Marque marque)
    {
        return marque == null? null : String.format("%s",marque.getLibelle());
    }

	@Override
	public Marque fromString(String string) {
		return null;
	}
}
