package racingcar;

import java.util.Map;

public class View {
    // 1.컨트롤러에서 런을 실행하면 바로 입력값을 받는 로직
    public void settingPrint(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }
    public void settingTry(){
        System.out.println("시도할 횟수는 몇 회인가요?");
    }


    public void showRacers(Map<String, Integer> racers) {
        for (Map.Entry<String, Integer> entry : racers.entrySet()) {
            System.out.print(entry.getKey() + " : ");
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

//    //모델에서 받아올수있는지 체크해볼께
//    public void getCheck(String name,int score){
//        System.out.println(name + " : " + score);
//    }


    //3.최종 우승자 : 하고 그 다음 이름 배열을 넣어주는 뷰단
    public void callWinner(String winners){
        System.out.println("최종 우승자 : "+winners);
    }
}
