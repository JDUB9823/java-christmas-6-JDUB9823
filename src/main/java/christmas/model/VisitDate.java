package christmas.model;

import christmas.utils.ErrorMessage;

import static java.lang.Integer.parseInt;

public class VisitDate {
    private static final int BEGIN_DATE = 1;
    private static final int END_DATE = 31;
    private static final int CHRISTMAS_DATE = 25;
    private static final int DAYS_IN_A_WEEK = 7;
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

    public boolean checkChristmasDDay() {
        return this.getDate() >= BEGIN_DATE && this.getDate() <= CHRISTMAS_DATE;
    }

    public boolean checkWeekday() { //12월의 주말은 1,2 8,9 ...이므로 7의 나머지가 1,2가 아니면 평일
        return this.getDate() % DAYS_IN_A_WEEK != 1 && this.getDate() % DAYS_IN_A_WEEK != 2;
    }

    public boolean checkStarDay() { //12월의 특별일은 3,10,17... 이므로 7의 나머지가 3이면 달력에 별 + 25일
        return this.getDate() % DAYS_IN_A_WEEK == 3 || this.getDate() == CHRISTMAS_DATE;
    }

    public int getDate() {
        return date;
    }
}
