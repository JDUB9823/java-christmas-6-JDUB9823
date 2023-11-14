package christmas.controller;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class EventPlannerController {
    private static final int GIFT_EVENT_CONDITION = 120000;
    private static final int GIFT_EVENT_PRICE = 25000;
    private static final int CHRISTMAS_D_DAY_EVENT_BASE_DISCOUNT = 1000;
    private static final int WEEK_DISCOUNT_EVENT_PRICE = 2023;
    private final InputController inputController;

    public EventPlannerController() {
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

        getEventPlannerResult(order, visitDate);
    }

    private void getEventPlannerResult(Order order, VisitDate visitDate) {
        OutputView.printGift(getGift(order));
        getBenefits(order, visitDate);
    }

    private String getGift(Order order) {
        if (checkGift(order.getTotalPrice())) {
            return "샴페인 1개";
        }

        return "없음";
    }

    private boolean checkGift(int totalPrice) {
        return totalPrice >= GIFT_EVENT_CONDITION;
    }

    private void getBenefits(Order order, VisitDate visitDate) {
        OutputView.printBenefitsHead();
        if (visitDate.checkChristmasDDay())
            OutputView.printChristmasDDayDiscount(getChristmasDDayDiscount(visitDate));
        if (visitDate.checkWeekday() && order.checkCategoryExists("DESSERT"))
            OutputView.printweekdayDiscount(getWeekEventDiscount(order, "DESSERT"));
        if (!visitDate.checkWeekday() && order.checkCategoryExists("MAIN_MENU"))
            OutputView.printweekendDiscount(getWeekEventDiscount(order, "MAIN_MENU"));
    }

    private int getChristmasDDayDiscount(VisitDate visitDate) {
        return CHRISTMAS_D_DAY_EVENT_BASE_DISCOUNT + (visitDate.getDate() - 1) * 100;
    }

    private int getWeekEventDiscount(Order order, String discountCategoryName) {
        return WEEK_DISCOUNT_EVENT_PRICE * order.getCategoryMenuAmountByKeys(order.getOrderKeysByCategory(discountCategoryName));
    }

    private int calculateGiftPrice(Order order) {
        if (order.getTotalPrice() >= GIFT_EVENT_CONDITION)
            return GIFT_EVENT_PRICE;
        return 0;
    }


    private void createOrderDetails(Order order) {
        OutputView.printOrderDetailsHead();
        order.getOrders().forEach((menu, amount) -> OutputView.printOrderDetails(menu.getName(), amount));
    }
}
