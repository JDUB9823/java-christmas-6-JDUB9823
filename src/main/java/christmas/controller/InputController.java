package christmas.controller;

import christmas.view.InputView;

import static java.lang.Integer.parseInt;

public class InputController {
    public int getVisitDateFromUser() {
        return parseInt(InputView.readDate());
    }
}
