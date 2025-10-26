package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    @DisplayName("자동차 이름관련 예외 테스트")
    class 이름_입력_예외_테스트 {
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
    class 시도_횟수_입력_예외_테스트 {
        @Test
        void 시도횟수_0_입력_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "0"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 시도횟수_음수_입력_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "-3"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 시도횟수_숫자아닌문자열_입력_예외() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "abc"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

    }

    @Nested
    @DisplayName("게임 로직 테스트")
    class 게임_로직_테스트 {
        @Test
        @DisplayName("전진_조건_테스트")
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
                        assertThat(output()).contains("최종 우승자 : pobi, woni");
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
                        assertThat(output()).contains("pobi : ---", "woni : -");
                    },
                    MOVING_FORWARD, STOP, MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD
            );
        }

    }

    @Nested
    @DisplayName("자동차 이름 추가 예외 테스트")
    class 이름_입력_추가_예외_테스트 {


        @Test
        @DisplayName("이름 1자 입력 허용")
        void 이름_1자_허용() {
            assertSimpleTest(() -> run("p,a", "1"));
            assertThat(output()).contains("p : ", "a : ");
        }

        @Test
        @DisplayName("이름 5자 입력 허용")
        void 이름_5자_허용() {
            assertSimpleTest(() -> run("abcde,fghij", "1"));
            assertThat(output()).contains("abcde : ", "fghij : ");
        }

    }

    @Nested
    @DisplayName("시도 횟수 추가 예외 테스트")
    class 시도_횟수_추가_예외_테스트 {

        @Test
        @DisplayName("최대 시도 횟수 경계값 테스트")
        void 최대_시도횟수_테스트() {
            assertSimpleTest(() -> run("pobi,woni", "1000"));
            assertThat(output()).contains("pobi", "woni");
        }
    }


}
