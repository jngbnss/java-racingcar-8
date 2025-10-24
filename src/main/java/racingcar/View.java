package racingcar;

import java.util.List;
import java.util.Map;

public class View {
    public void showCarNameInputPrompt() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void showAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public void showRaceStart() {
        System.out.println("\n실행 결과");
    }


    public void printRoundResult(Map<String, Integer> cars) {
        for (Map.Entry<String, Integer> entry : cars.entrySet()) {
            String name = entry.getKey();
            Integer distance = entry.getValue();
            System.out.print(name + " : ");
            for (int i = 0; i < distance; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }


    // View.java: 출력만
    public void showWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
