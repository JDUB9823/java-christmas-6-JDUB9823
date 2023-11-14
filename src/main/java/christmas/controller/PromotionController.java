package christmas.controller;

import christmas.model.Benefits;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.OutputView;

import java.util.Iterator;

public class PromotionController {
    private final InputController inputController;

    public PromotionController() {
        this.inputController = new InputController();
    }

    public void initEventPlanner() {
        OutputView.printGreeting();
        OutputView.printPrecautions();

        VisitDate visitDate = inputController.getVisitDateFromUser();
        Order order = inputController.getOrderFromUser();

        OutputView.printEventPreviewHead();
        createOrderDetails(order);

        OutputView.printTotalPriceBeforeDiscount(order.getTotalPrice());
    }

    public void createOrderDetails(Order order) {
        OutputView.printOrderDetailsHead();
        order.getOrders().forEach((menu, amount) -> OutputView.printOrderDetails(menu.getName(), amount));
    }
}
