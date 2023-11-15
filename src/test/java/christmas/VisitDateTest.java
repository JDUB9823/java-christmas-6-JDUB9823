package christmas;

import christmas.model.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class VisitDateTest {
    @DisplayName("방문 날짜가 1 ~ 31일이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "100", "-1"})
    void createVisitDateWithInvalidDate(String expectedErrorDate) {
        //given expectedErrorDate
        //when  new VisitDate(expectedErrorDate)
        //then
        assertThatThrownBy(() -> new VisitDate(expectedErrorDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜는 1 ~ 31 사이의 숫자이어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "15", "25", "31"})
    void createVisitDate(String expectedDate) {
        //given expectedDate
        //when
        VisitDate visitDate = new VisitDate(expectedDate);

        //then
        assertThat(visitDate.getDate()).isEqualTo(parseInt(expectedDate));
    }

    @DisplayName("크리스마스 디데이 할인 기간은 1일부터 25일까지이다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "15", "25"})
    void checkChristmasDDayDate(String expectedEventDate) {
        //given expectedDate
        //when
        VisitDate visitDate = new VisitDate(expectedEventDate);

        //then
        assertThat(visitDate.checkChristmasDDay()).isEqualTo(true);
    }

    @DisplayName("방문 날짜 평일 식별 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "6", "11", "19", "28", "31"})
    void checkWeekday(String expectedEventDate) {
        //given expectedDate
        //when
        VisitDate visitDate = new VisitDate(expectedEventDate);

        //then
        assertThat(visitDate.checkWeekday()).isEqualTo(true);
    }

    @DisplayName("방문 날짜 주말 식별 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "15", "16", "29", "30"})
    void checkWeekend(String expectedEventDate) {
        //given expectedDate
        //when
        VisitDate visitDate = new VisitDate(expectedEventDate);

        //then
        assertThat(visitDate.checkWeekday()).isEqualTo(false);
    }

    @DisplayName("달력에 별이 있는 방문 날짜 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    void checkStar(String expectedEventDate) {
        //given expectedDate
        //when
        VisitDate visitDate = new VisitDate(expectedEventDate);

        //then
        assertThat(visitDate.checkStarDay()).isEqualTo(true);
    }
}
