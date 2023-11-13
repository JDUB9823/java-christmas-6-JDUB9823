package christmas.controller;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.InputView;

import java.util.Arrays;

public class InputController {
    public VisitDate getVisitDateFromUser() {
        try {
            return new VisitDate(InputView.readDate());
        } catch (IllegalArgumentException e) {
            return getVisitDateFromUser();
        }
    }

    public Order getOrderFromUser() {
        try {
            return new Order(Arrays.asList(InputView.readOrder().split(",")));
        } catch (IllegalArgumentException e) {
            return getOrderFromUser();
        }
    }
}
