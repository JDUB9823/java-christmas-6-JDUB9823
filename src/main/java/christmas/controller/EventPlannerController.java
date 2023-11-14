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
    private static final int STAR_BADGE_CONDITION = 5000;
    private static final int TREE_BADGE_CONDITION = 10000;
    private static final int SANTA_BADGE_CONDITION = 20000;

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
        OutputView.printGift(getGift(order.getTotalPrice()));

        OutputView.printBenefitsHead();
        int totalDisCountAMount = checkBenefits(order, visitDate);

        OutputView.printTotalDiscountAmount(totalDisCountAMount);

        OutputView.printExpectedBill(order.getTotalPrice() - totalDisCountAMount);

        OutputView.printBadgeHead();
        OutputView.printBadge(getBadge(totalDisCountAMount));
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
        discountAmount += getChristmasDDayDiscount(visitDate, order.getTotalPrice());
        discountAmount += getWeekEventDiscount(visitDate, order);
        discountAmount += getStarDiscount(visitDate, order.getTotalPrice());
        discountAmount += getGiftBenefit(order.getTotalPrice());

        if (discountAmount == 0) {
            OutputView.printNone();
        }

        return discountAmount;
    }

    private int getChristmasDDayDiscount(VisitDate visitDate, int totalPrice) {
        int discountAmount = 0;

        if (visitDate.checkChristmasDDay() && checkDiscountCondition(totalPrice)) {
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
        if (visitDate.checkWeekday() && order.checkCategoryExists("DESSERT")
                && checkDiscountCondition(order.getTotalPrice())) {
            discountAmount = calculateWeekEventDiscount(order, "DESSERT");
            OutputView.printweekdayDiscount(discountAmount);
        }
        if (!visitDate.checkWeekday() && order.checkCategoryExists("MAIN_MENU")
                && checkDiscountCondition(order.getTotalPrice())) {
            discountAmount = calculateWeekEventDiscount(order, "MAIN_MENU");
            OutputView.printweekendDiscount(discountAmount);
        }

        return discountAmount;
    }

    private int calculateWeekEventDiscount(Order order, String discountCategoryName) {
        return WEEK_DISCOUNT_EVENT_PRICE * order.getCategoryMenuAmountByKeys(
                order.getOrderKeysByCategory(discountCategoryName));
    }

    private int getStarDiscount(VisitDate visitDate,int totalPrice) {
        int discountAmount = 0;

        if (visitDate.checkStarDay() && checkDiscountCondition(totalPrice)) {
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

    private String getBadge(int totalDiscountAmount) {
        if (totalDiscountAmount > STAR_BADGE_CONDITION) {
            return getBadgeOver5k(totalDiscountAmount);
        }

        return "없음";
    }

    private String getBadgeOver5k(int totalDiscountAmount) {
        if (totalDiscountAmount > TREE_BADGE_CONDITION) {
            return getBadgeOver10k(totalDiscountAmount);
        }

        return "별";
    }

    private String getBadgeOver10k(int totalDiscountAmount) {
        if (totalDiscountAmount > SANTA_BADGE_CONDITION) {
            return "산타";
        }

        return "트리";
    }

    private void createOrderDetails(Order order) {
        OutputView.printOrderDetailsHead();
        order.getOrders().forEach((menu, amount) -> OutputView.printOrderDetails(menu.getName(), amount));
    }

    private boolean checkDiscountCondition(int totalPrice) {
        return totalPrice >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT;
    }
}