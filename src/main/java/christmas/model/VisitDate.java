package christmas.model;

import christmas.utils.ErrorMessage;

import static java.lang.Integer.parseInt;

public class VisitDate {
    private final int date;

    public VisitDate(String visitDateFromUser) {
        this.date = validate(visitDateFromUser);
    }

    private int validate(String visitDateFromUser) {
        int date = validateNumber(visitDateFromUser);
        return date;
    }

    private int validateNumber(String visitDateFromUser) {
        try {
            return parseInt(visitDateFromUser);
        } catch (NumberFormatException e) {
            ErrorMessage.numberException();
            throw new IllegalArgumentException();
        }
    }
}
