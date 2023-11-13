package christmas.controller;

import christmas.model.VisitDate;
import christmas.view.OutputView;

public class PromotionController {
    private final InputController inputController;

    public PromotionController() {
        this.inputController = new InputController();
    }

    public void init() {
        OutputView.printGreeting();
        VisitDate visitDate = inputController.getVisitDateFromUser();
        inputController.getOrderFromUser();
    }
}
