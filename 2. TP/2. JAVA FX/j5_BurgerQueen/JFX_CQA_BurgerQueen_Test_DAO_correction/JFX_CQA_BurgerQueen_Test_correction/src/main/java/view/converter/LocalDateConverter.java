package view.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import common.utils.C;
import javafx.util.StringConverter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocalDateConverter extends StringConverter<LocalDate> {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(C.PATTERN_FORMAT_DATE);
	
	@Override
	public String toString(LocalDate date) {
		String retour;
		try {
			retour = date.format(dtf);
		} catch (Exception e) {
			retour = null;
		}
		return retour;
	}

	@Override
	public LocalDate fromString(String string) {
		LocalDate retour = null; 
		try {
			retour = LocalDate.parse(string, dtf);
		} catch (DateTimeParseException e) {
			return null;
		}
		
		return retour;
	}

}
