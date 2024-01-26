package garage.model.dao.jpa.utils;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        //Instant instant = Instant.from(date);
        return Date.valueOf(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        //Instant instant = value.toInstant();
        return value.toLocalDate();
    }
}
