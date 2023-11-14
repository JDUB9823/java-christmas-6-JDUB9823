package christmas.view;

import java.text.DecimalFormat;

public class OutputView {
    public static void printGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printEventPreviewHead() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printOrderDetailsHead() {
        System.out.println();
        System.out.println("<주문 메뉴>");
    }

    public static void printOrderDetails(String menuName, int amount) {
        System.out.println(menuName + " " + amount + "개");
    }

    public static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        printPrice(totalPriceBeforeDiscount);
    }

    public static void printPrice(int price) {
        System.out.println(new DecimalFormat("###,###").format(price) + "원");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
