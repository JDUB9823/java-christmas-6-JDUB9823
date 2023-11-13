package christmas.model;

import christmas.utils.ErrorMessage;

import static java.lang.Integer.parseInt;

public class VisitDate {
    private static final int BEGIN_DATE = 1;
    private static final int END_DATE = 31;
    private final int date;

    public VisitDate(String visitDateFromUser) {
        this.date = validate(visitDateFromUser);
    }

    private int validate(String visitDateFromUser) {
        int date = validateNumber(visitDateFromUser);
        validateDate(date);
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

    private void validateDate(int date) {
        if (date < BEGIN_DATE || date > END_DATE) {
            ErrorMessage.dateException();
            throw new IllegalArgumentException();
        }
    }

    public int getDate() {
        return date;
    }
}
