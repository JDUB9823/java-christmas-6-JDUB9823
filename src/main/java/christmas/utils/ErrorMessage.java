package christmas.utils;

import christmas.view.OutputView;

public class ErrorMessage {
    private static final String NOT_A_NUMBER = "숫자만 입력 가능합니다. 다시 입력해 주세요.";
    private static final String INVALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String EXCEED_MAXIMUM_ORDERS = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    private static final String UNAVAILABLE_ORDER_DRINKS = "음료만 주문 시, 주문할 수 없습니다.";

    public static void numberException() {
        OutputView.printErrorMessage(NOT_A_NUMBER);
    }

    public static void dateException() {
        OutputView.printErrorMessage(INVALID_DATE);
    }

    public static void orderException() {
        OutputView.printErrorMessage(INVALID_ORDER);
    }

    public static void orderLimitException() {
        OutputView.printErrorMessage(EXCEED_MAXIMUM_ORDERS);
    }

    public static void drinksOnlyException() {
        OutputView.printErrorMessage(UNAVAILABLE_ORDER_DRINKS);
    }
}
