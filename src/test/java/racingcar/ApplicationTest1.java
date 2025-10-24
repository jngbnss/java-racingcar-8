package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest1 extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    // --------------------------
    // 게임 로직 테스트
    // --------------------------
    @Test
    @DisplayName("전진 조건 테스트")
    void 전진_조건() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("단독 우승자 계산")
    void 단독_우승자() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("공동 우승자 계산")
    void 공동_우승자() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("최종 우승자 : pobi, woni");
                },
                MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    @DisplayName("모든 자동차 이동하지 않은 경우")
    void 모든자동차_정지() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : ", "woni : ", "최종 우승자 : pobi, woni");
                },
                STOP, STOP
        );
    }

    @Test
    @DisplayName("여러 회 경주 시 상태 변화 확인")
    void 여러회_경주() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    // 각 차 위치 누적 표시 확인
                    assertThat(output()).contains("pobi : ---", "woni : --");
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    // --------------------------
    // 예외 테스트
    // --------------------------
    @Nested
    @DisplayName("자동차 이름관련 예외 테스트")
    class 이름_입력_예외_테스트{
        @Test
        @DisplayName("자동차 이름 5자 초과 입력 예외")
        void 자동차_이름_5자_초과_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("123456,정상", "5"))
                            .isInstanceOf(IllegalArgumentException.class));
        }

        @Test
        void 자동차_이름_빈_문자열_입력_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException(",a", "1"))
                            .isInstanceOf(IllegalArgumentException.class));
        }

        @Test
        void 자동차_이름_공백_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException(" ,a", "1"))
                            .isInstanceOf(IllegalArgumentException.class));
        }

        @Test
        void 자동차_이름_잘못된구분자_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("abc;def", "5"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }
    }

    @Nested
    @DisplayName("시도 횟수 관련 예외 테스트")
    class 시도_횟수_입력_예외_테스트{
        @Test
        void 시도횟수_0_입력_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "0"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 시도횟수_음수_입력_예외(){
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni","-3"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 시도횟수_숫자아닌문자열_입력_예외(){
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni","abc"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }
    }

    // --------------------------
    // 메인 실행
    // --------------------------
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}


