package christmas.controller;

import christmas.model.VisitDate;
import christmas.view.InputView;

import static java.lang.Integer.parseInt;

public class InputController {
    public VisitDate getVisitDateFromUser() {
        try {
            return new VisitDate(InputView.readDate());
        } catch (IllegalArgumentException e) {
            return getVisitDateFromUser();
        }
    }

    public void getOrderFromUser() {
        System.out.println(InputView.readOrder());
    }
}
