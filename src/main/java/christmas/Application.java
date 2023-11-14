package christmas;

import christmas.controller.PromotionController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        PromotionController promotionController = new PromotionController();
        promotionController.initEventPlanner();
    }
}
