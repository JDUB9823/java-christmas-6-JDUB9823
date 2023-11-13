package christmas.utils;

import christmas.view.OutputView;

public class ErrorMessage {
    private static final String NOT_A_NUMBER = "숫자만 입력 가능합니다. 다시 입력해 주세요.";
    private static final String INVALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static void numberException() {
        OutputView.printErrorMessage(NOT_A_NUMBER);
    }

    public static void dateException() {
        OutputView.printErrorMessage(INVALID_DATE);
    }
}
