package christmas;

import christmas.model.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("nonExistMenu")
    void createOrderByNonExistMenu(List<String> nonExistMenu) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(nonExistMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> nonExistMenu() {
        return Stream.of(
                Arguments.of(List.of("토마토파스타-1")),
                Arguments.of(List.of("양송이수프-1", "등심스테이크-1")),
                Arguments.of(List.of("치즈케이크-2", "시저샐러드-3")),
                Arguments.of(List.of("환타-1"))
        );
    }

    @DisplayName("중복된 메뉴를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("duplicateMenu")
    void createOrderByDuplicateMenu(List<String> duplicateMenu) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(duplicateMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> duplicateMenu() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-1", "양송이수프-2")),
                Arguments.of(List.of("양송이수프-1", "티본스테이크-2", "티본스테이크-1")),
                Arguments.of(List.of("제로콜라-1", "시저샐러드-3", "아이스크림-1", "제로콜라-1"))
        );
    }

    @DisplayName("메뉴 개수가 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("blankAmount")
    void createOrderByBlankAmount(List<String> blankAmount) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(blankAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> blankAmount() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-")),
                Arguments.of(List.of("티본스테이크-")),
                Arguments.of(List.of("제로콜라-1", "시저샐러드-")),
                Arguments.of(List.of("아이스크림-"))
        );
    }

    @DisplayName("메뉴 개수가 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("notNumberAmount")
    void createOrderByNotNumberAmount(List<String> notNumberAmount) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(notNumberAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> notNumberAmount() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-a")),
                Arguments.of(List.of("티본스테이크-.")),
                Arguments.of(List.of("제로콜라-!")),
                Arguments.of(List.of("시저샐러드-@")),
                Arguments.of(List.of("아이스크림-+"))
        );
    }

    @DisplayName("메뉴 개수가 음수면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("negativeOrZeroAmount")
    void createOrderByNegativeOrZeroAmount(List<String> negativeOrZeroAmount) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(negativeOrZeroAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> negativeOrZeroAmount() {
        return Stream.of(
                Arguments.of(List.of("양송이수프--1")),
                Arguments.of(List.of("티본스테이크--4.")),
                Arguments.of(List.of("제로콜라-0"))
        );
    }

    @DisplayName("메뉴의 총합이 20개가 넘으면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("overAmount")
    void createOrderByOverAmount(List<String> overAmount) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(overAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> overAmount() {
        return Stream.of(
                Arguments.of(List.of("양송이수프-21")),
                Arguments.of(List.of("티본스테이크-5", "아이스크림-5", "타파스-5", "샴페인-6")),
                Arguments.of(Arrays.asList("양송이수프-1", "티본스테이크-2", "초코케이크-3", "제로콜라-2", "타파스-1", "바비큐립-2", "아이스크림-3",
                        "레드와인-2", "샴페인-1", "해산물파스타-2", "시저샐러드-3", "크리스마스파스타-2"))
        );
    }

    @DisplayName("음료수만 주문된 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("onlyDrinks")
    void createOrderByOnlyDrinks(List<String> onlyDrinks) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        //then
        assertThatThrownBy(() -> new Order(onlyDrinks))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> onlyDrinks() {
        return Stream.of(
                Arguments.of(List.of("제로콜라-1")),
                Arguments.of(List.of("제로콜라-1", "샴페인-1", "레드와인-1"))
        );
    }

    @DisplayName("주문 테스트")
    @ParameterizedTest
    @MethodSource("expectedOrder")
    void createOrderByExpectedOrder(List<String> expectedOrder, int expectedTotalAmountOfOrder) {
        //given nonExistMenu
        //when  new order(nonExistMenu)
        Order order = new Order(expectedOrder);

        //then
        assertThat(order.getTotalPrice()).isEqualTo(expectedTotalAmountOfOrder);
    }

    static Stream<Arguments> expectedOrder() {
        return Stream.of(
                Arguments.of(List.of("티본스테이크-1"), 55000),
                Arguments.of(List.of("타파스-1", "샴페인-1", "아이스크림-3"), 5500 + 25000 + 5000 * 3)
        );
    }
}
