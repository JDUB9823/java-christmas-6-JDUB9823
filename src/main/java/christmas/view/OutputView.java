package christmas.view;

import java.text.DecimalFormat;

public class OutputView {
    public static void printGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printPrecautions() {
        System.out.println("[주의사항] 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("[주의사항] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        System.out.println("[주의사항] 음료만 주문 시, 주문할 수 없습니다.");
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

    public static void printGift(String gift) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(gift);
    }

    public static void printBenefitsHead() {
        System.out.println();
        System.out.println("<혜택 내역>");
    }

    public static void printChristmasDDayDiscount(int discountAmount) {
        System.out.println("크리스마스 디데이 할인: -" + new DecimalFormat("###,###").format(discountAmount) + "원");
    }

    public static void printweekdayDiscount(int discountAmount) {
        System.out.println("평일 할인: -" + new DecimalFormat("###,###").format(discountAmount) + "원");
    }

    public static void printweekendDiscount(int discountAmount) {
        System.out.println("주말 할인: -" + new DecimalFormat("###,###").format(discountAmount) + "원");
    }

    public static void printStarDiscount(int discountAmount) {
        System.out.println("특별 할인: -" + new DecimalFormat("###,###").format(discountAmount) + "원");
    }

    public static void printgiftDiscount(int discountAmount) {
        System.out.println("증정 이벤트: -" + new DecimalFormat("###,###").format(discountAmount) + "원");
    }

    public static void printNone() {
        System.out.println("없음");
    }

    public static void printTotalDiscountAmount(int totalDiscountAmount) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        if(totalDiscountAmount > 0) {
            System.out.println("-" + new DecimalFormat("###,###").format(totalDiscountAmount) + "원");
            return;
        }
        System.out.println(totalDiscountAmount + "원");
    }

    public static void printExpectedBill(int totalAmountAfterDiscount) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(new DecimalFormat("###,###").format(totalAmountAfterDiscount) + "원");
    }

    public static void printBadgeHead() {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
