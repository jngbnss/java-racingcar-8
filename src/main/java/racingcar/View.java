package racingcar;

import java.util.List;
import java.util.Map;

public class View {
    public void inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void attemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }


    public void printRoundResult(Map<String, Integer> cars) {
        for (Map.Entry<String, Integer> entry : cars.entrySet()) {
            System.out.print(entry.getKey() + " : ");
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.print("-");
            }
            System.out.println();

        }
        System.out.println();
    }


    // View.java: 출력만
    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
