package christmas.controller;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.OutputView;

public class EventPlannerController {
    private static final int GIFT_EVENT_CONDITION = 120000;
    private static final int GIFT_EVENT_PRICE = 25000;
    private static final int CHRISTMAS_D_DAY_EVENT_BASE_DISCOUNT = 1000;
    private static final int WEEK_DISCOUNT_EVENT_PRICE = 2023;
    private static final int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10000;
    private static final int STAR_DAY_DISCOUNT_AMOUNT = 1000;
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

        checkDiscountCondition(order, visitDate);
    }

    private void checkDiscountCondition(Order order, VisitDate visitDate) {
        if (order.getTotalPrice() < MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT) {
            printResultWithNoDiscount(order.getTotalPrice());
            return;
        }

        printResultWithDiscount(order, visitDate);
    }

    private void printResultWithNoDiscount(int orderAmount) {
        OutputView.printGift(getGift(orderAmount));

        OutputView.printBenefitsHead();
        OutputView.printNone();

        OutputView.printTotalDiscountAmount(0);

        OutputView.printExpectedBill(orderAmount);

        OutputView.printBadgeHead();
        OutputView.printNone();
    }

    private void printResultWithDiscount(Order order, VisitDate visitDate) {
        OutputView.printGift(getGift(order.getTotalPrice()));

        OutputView.printBenefitsHead();
        int disCountAMount = checkBenefits(order, visitDate);
    }

    private String getGift(int totalPrice) {
        if (checkGift(totalPrice)) {
            return "샴페인 1개";
        }

        return "없음";
    }

    private boolean checkGift(int totalPrice) {
        return totalPrice >= GIFT_EVENT_CONDITION;
    }

    private int checkBenefits(Order order, VisitDate visitDate) {
        int discountAmount = 0;
        discountAmount += getChristmasDDayDiscount(visitDate);
        discountAmount += getWeekEventDiscount(visitDate, order);
        discountAmount += getStarDiscount(visitDate);
        discountAmount += getGiftBenefit(order.getTotalPrice());

        if (discountAmount == 0) {
            OutputView.printNone();
        }

        return discountAmount;
    }

    private int getChristmasDDayDiscount(VisitDate visitDate) {
        int discountAmount = 0;

        if (visitDate.checkChristmasDDay()) {
            discountAmount = calculateChristmasDDayDiscount(visitDate);
            OutputView.printChristmasDDayDiscount(discountAmount);
        }

        return discountAmount;
    }

    private int calculateChristmasDDayDiscount(VisitDate visitDate) {
        return CHRISTMAS_D_DAY_EVENT_BASE_DISCOUNT + (visitDate.getDate() - 1) * 100;
    }

    private int getWeekEventDiscount(VisitDate visitDate, Order order) {
        int discountAmount = 0;

        if (visitDate.checkWeekday() && order.checkCategoryExists("DESSERT")) {
            discountAmount = calculateWeekEventDiscount(order, "DESSERT");
            OutputView.printweekdayDiscount(discountAmount);
        }
        if (!visitDate.checkWeekday() && order.checkCategoryExists("MAIN_MENU")) {
            discountAmount = calculateWeekEventDiscount(order, "MAIN_MENU");
            OutputView.printweekendDiscount(discountAmount);
        }

        return discountAmount;
    }

    private int calculateWeekEventDiscount(Order order, String discountCategoryName) {
        return WEEK_DISCOUNT_EVENT_PRICE * order.getCategoryMenuAmountByKeys(
                order.getOrderKeysByCategory(discountCategoryName));
    }

    private int getStarDiscount(VisitDate visitDate) {
        int discountAmount = 0;

        if (visitDate.checkStarDay()) {
            discountAmount = STAR_DAY_DISCOUNT_AMOUNT;
            OutputView.printStarDiscount(STAR_DAY_DISCOUNT_AMOUNT);
        }

        return discountAmount;
    }

    private int getGiftBenefit(int totalPrice) {
        int discountAmount = 0;

        if (totalPrice >= GIFT_EVENT_CONDITION) {
            discountAmount = GIFT_EVENT_PRICE;
            OutputView.printgiftDiscount(GIFT_EVENT_PRICE);
        }

        return discountAmount;
    }

    private void createOrderDetails(Order order) {
        OutputView.printOrderDetailsHead();
        order.getOrders().forEach((menu, amount) -> OutputView.printOrderDetails(menu.getName(), amount));
    }
}
