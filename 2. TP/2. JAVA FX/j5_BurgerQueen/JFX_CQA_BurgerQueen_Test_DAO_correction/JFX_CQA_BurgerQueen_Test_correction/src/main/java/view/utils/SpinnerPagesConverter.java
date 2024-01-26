package view.utils;

import javafx.util.StringConverter;

public class SpinnerPagesConverter extends StringConverter<Integer> {

    private final Integer MAX_PAGES;

    public SpinnerPagesConverter(int maxPages) {
        MAX_PAGES = maxPages;
    }

    @Override
    public String toString(Integer object) {
        return object.toString();
    }

    @Override
    public Integer fromString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return MAX_PAGES;
        }
    }

}
