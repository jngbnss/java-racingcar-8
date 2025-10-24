package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private final Map<String, Integer> cars;
    private static final String INVALID_NAME_MSG = "자동차 이름은 1~5자여야 합니다.";
    private static final int MIN_NUMBER_TO_MOVE = 4;

    public Model(List<String> carNames) {
        cars = new LinkedHashMap<>();
        for (String name : carNames) {
            validateName(name);

            cars.put(name, 0);
        }
    }


    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > 5) {
            throw new IllegalArgumentException(INVALID_NAME_MSG);
        }
    }


    public void playTurn() {
        cars.keySet().forEach(name -> {
            if (Randoms.pickNumberInRange(0, 9) >= MIN_NUMBER_TO_MOVE) {
                moveCar(name);
            }
        });
    }

    public void moveCar(String name) {
        cars.put(name, cars.get(name) + 1);
    }

    public List<String> getWinnersList() {
        int maxScore = Collections.max(cars.values());
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cars.entrySet()) {
            if (entry.getValue() == maxScore) {
                winners.add(entry.getKey());
            }
        }
        return winners;
    }

    public Map<String, Integer> getCurrentState() {
        return Collections.unmodifiableMap(cars);
    }
}
