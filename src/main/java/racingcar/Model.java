package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Model {
    private final Map<String, Integer> cars;
    int maxScore = 0;
    List<String> winners = new ArrayList<>();

    public Model(List<String> carNames) {
        cars = new LinkedHashMap<>();
        for (String name : carNames) {
            cars.put(name, 0);
        }
    }

    public Map<String, Integer> getCars() {
        return cars;

    }
    // getter가 필요할까? 테스트를 할때 필요한건데 이후에 다시 생각해보자 필요없으면 지우자
    // 현재 컨트롤러에서는 게터를 사용
    // 의존성 주입하면 그냥 뽑아올수있을듯


    public void playTurn() {
        for (String name : cars.keySet()) {
            int move = Randoms.pickNumberInRange(0, 9);
//            System.out.println("move = " + move);
            if (move > 4) {
                moveCar(name);
            }
        }
    }

    public void moveCar(String name) {
        cars.put(name, cars.get(name) + 1);
    }

    public void checkWinners() {
        for (int v : cars.values()) {
            maxScore = Math.max(maxScore, v);
        }
    }

    public void listWinners() {
        for (Entry<String, Integer> entry : cars.entrySet()) {
            if (entry.getValue() == maxScore) {
                winners.add(entry.getKey());
            }
        }
    }

    public String winners() {
        return String.join(", ", winners);
    }

}
