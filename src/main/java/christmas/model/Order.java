package christmas.model;

import christmas.utils.ErrorMessage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Order {
    private final Map<Menu, Integer> orders;

    public Order(List<String> ordersFromUser) {
        Map<Menu, Integer> orders = new LinkedHashMap<>();

        for (String orderFromUser : ordersFromUser) {
            String extractedName = extractName(orderFromUser);
            String extractedAmount = extractAmount(orderFromUser);

            validateName(extractedName, orders);
            int amount = validateAmount(extractedAmount);

            orders.put(Menu.getMenuByName(extractedName), amount);
        }
        this.orders = orders;
    }


    private String extractName(String orderFromUser) {
        return orderFromUser.substring(0, orderFromUser.indexOf("-"));
    }

    private String extractAmount(String orderFromUser) {
        return orderFromUser.substring(orderFromUser.indexOf("-") + 1);
    }

    private void validateName(String extractedName, Map<Menu, Integer> orders) {
        validateMenuExists(extractedName);
        validateMenuDuplication(extractedName, orders);
    }

    private void validateMenuExists(String name) {
        if (!Menu.checkMenu(name)) {
            ErrorMessage.orderException();
            throw new IllegalArgumentException();
        }
    }

    private void validateMenuDuplication(String name, Map<Menu, Integer> orders) {
        if (orders.containsKey(Menu.getMenuByName(name))) {
            ErrorMessage.orderException();
            throw new IllegalArgumentException();
        }
    }

    private int validateAmount(String extractedAmount) {
        validateAmountBlank(extractedAmount);
        int amount = validateAmountNumber(extractedAmount);

        return amount;
    }

    private void validateAmountBlank(String amount) {
        if (amount.isBlank()) {
            ErrorMessage.orderException();
            throw new IllegalArgumentException();
        }
    }

    private int validateAmountNumber(String amount) {
        try {
            return parseInt(amount);
        } catch (NumberFormatException e) {
            ErrorMessage.orderException();
            throw new IllegalArgumentException();
        }
    }

}
