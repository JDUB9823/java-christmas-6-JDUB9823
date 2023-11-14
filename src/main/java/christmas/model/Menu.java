package christmas.model;

public enum Menu {      //메뉴판
    MUSHROOM_SOUP("양송이수프", 6000, "APPETIZER"),
    TAPAS("타파스", 5500, "APPETIZER"),
    CAESAR_SALAD("시저샐러드", 8000, "APPETIZER"),
    T_BONE_STAKE("티본스테이크", 55000, "MAIN_MENU"),
    BARBEQUE_RIB("바비큐립", 54000, "MAIN_MENU"),
    SEAFOOD_PASTA("해산물파스타", 35000, "MAIN_MENU"),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, "MAIN_MENU"),
    CHOCOLATE_CAKE("초코케이크", 15000, "DESSERT"),
    ICECREAM("아이스크림", 5000, "DESSERT"),
    ZERO_COKE("제로콜라", 3000, "DRINKS"),
    RED_WINE("레드와인", 60000, "DRINKS"),
    CHAMPAGNE("샴페인", 25000, "DRINKS"),
    NOTHING("", 0, "");

    private final String name;
    private final int price;
    private final String category;

    Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Boolean checkMenu(String name) {  //메뉴판 존재 여부 반환
        return !getMenuByName(name).equals(NOTHING);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }

        return NOTHING;
    }

    public Boolean checkMenuCategory(String category) {
        return this.getCategory().equals(category);
    }
}
