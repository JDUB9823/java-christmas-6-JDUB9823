package christmas.controller;

import christmas.view.OutputView;

public class PromotionController {
    private final InputController inputController;

    public PromotionController() {
        this.inputController = new InputController();
    }

    public void init() {
        OutputView.printGreeting();
        System.out.println(inputController.getVisitDateFromUser());
    }
}
