package racingcar;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Model {
    //레이서가 제한이 없으니까 동적 배열로 받고
    // 전체 사이즈를 미리 받아놔야겠다 그래야 랜덤으로 주사위를 돌리고
    // n회만큼 진행하니까
//    Array<String> racers = new ArrayList<>();
    //List<String> racersName = new ArrayList<>();
    private Map<String,Integer> racers;
    //생성자로 만들어야하나
//    뷰는 보여주기만하고 컨트롤러에서 입력을 받아서 넘어왔다
//    그럼 컨트롤러에서 생성자로 넘겨 받아야겠다.




    // 생성자에서 받고 이름이랑 점수를 설정하자
    public Model(List<String> racersName) {
        //this.racersName = racersName;
        racers = new LinkedHashMap<>();
        for(String name:racersName){
            racers.put(name,0); // 초기화 점수
        }
    }
    // 컨트롤러에서 구분짓고 넘겨주는 로직이어야겠다.
    // 그럼 각레이서별 점수는 어떻게 정하지?
    // 맵으로 각각 점수를 줘야하나?
//    모델 초기값은 이정도면 되지 않나?
    // 근데 위너 선정할때 제일 값이 큰사람이 필요할것같은데
    //벨류 멕시멈일때 키값을 가져오게

    public Map<String, Integer> getRacers() {
        return racers;
    }
    // 의존성 주입하면 그냥 뽑아올수있을듯
}
