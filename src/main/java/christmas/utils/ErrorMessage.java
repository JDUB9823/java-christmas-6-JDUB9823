package christmas.utils;

import christmas.view.OutputView;

public class ErrorMessage {
    private static final String NOT_A_NUMBER = "숫자만 입력 가능합니다.";

    public static void numberException() {
        OutputView.printErrorMessage(NOT_A_NUMBER);
    }
}
