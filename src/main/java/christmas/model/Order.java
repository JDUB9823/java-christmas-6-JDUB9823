package christmas.model;

import christmas.utils.ErrorMessage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Order {
    private static final int MAXIMUM_NUMBER_OF_ORDERS = 20;

    private final Map<Menu, Integer> orders;

    public Order(List<String> ordersFromUser) {
        Map<Menu, Integer> orders = new LinkedHashMap<>();

        for (String orderFromUser : ordersFromUser) {
            validateName(extractName(orderFromUser), orders);
            int amount = validateAmount(extractAmount(orderFromUser));

            orders.put(Menu.getMenuByName(extractName(orderFromUser)), amount);
        }
        checkMaximumNumberOfOrders(getTotalNumberOfMenu(orders));
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
        validateAmountPositive(amount);

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

    private void validateAmountPositive(int amount) {
        if (amount < 1) {
            ErrorMessage.orderException();
            throw new IllegalArgumentException();
        }
    }

    private void checkMaximumNumberOfOrders(int numberOfOrders) {
        if (numberOfOrders > MAXIMUM_NUMBER_OF_ORDERS) {
            ErrorMessage.orderLimitException();
            throw new IllegalArgumentException();
        }
    }

    private int getTotalNumberOfMenu(Map<Menu, Integer> orders) {
        return orders.values().stream().mapToInt(Integer::intValue).sum();
    }
}
