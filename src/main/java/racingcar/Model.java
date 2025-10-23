package racingcar;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private final Map<String, Integer> cars;

    public Model(List<String> carNames) {
        cars = new LinkedHashMap<>();
        for (String name : carNames) {
            cars.put(name, 0);
        }
    }

    public Map<String, Integer> getCars() {
        // return racers;
        return Collections.unmodifiableMap(cars);
    }
    // getter가 필요할까? 테스트를 할때 필요한건데 이후에 다시 생각해보자 필요없으면 지우자
    // 의존성 주입하면 그냥 뽑아올수있을듯
}
