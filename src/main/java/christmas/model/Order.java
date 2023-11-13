package christmas.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Order {
    private Map<Menu, Integer> order;

    public Order(List<String> orderFromUser) {
        Map<String, Integer> order = splitOrder(orderFromUser);
        this.order = new LinkedHashMap<>();
    }

    private Map<String, Integer> splitOrder(List<String> ordersFromUser) {
        Map<String, Integer> orders = new LinkedHashMap<>();

        for (String orderFromUser : ordersFromUser) {
            String menu = extractMenu(orderFromUser);
            int amount = extractAmount(orderFromUser);
            System.out.println(menu + "/" + amount);
        }

        return orders;
    }

    private String extractMenu(String orderFromUser) {
        return orderFromUser.substring(0, orderFromUser.indexOf("-"));
    }

    private int extractAmount(String orderFromUser) {
        return parseInt(orderFromUser.substring(orderFromUser.indexOf("-") + 1));
    }
}
