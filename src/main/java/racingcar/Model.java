package racingcar;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private Map<String,Integer> racers;

    // 생성자에서 받고 이름이랑 점수를 설정하자
    public Model(List<String> racersName) {
        racers = new LinkedHashMap<>();
        for(String name:racersName){
            racers.put(name,0); // 초기화 점수
        }
    }
    public Map<String, Integer> getRacers() {
        return racers;
    }
    // 의존성 주입하면 그냥 뽑아올수있을듯
}
