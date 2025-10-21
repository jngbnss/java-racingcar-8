package racingcar;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.Map;

public class View {
    private final Model model;
//    Model model = new Model();
    private String winners;
    private int mx;



    public View(Model model) {
        this.model = model;
    }

    // 1.컨트롤러에서 런을 실행하면 바로 입력값을 받는 로직
    public void settingPrint(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }
    public void settingTry(){
        System.out.println("시도할 횟수는 몇 회인가요?");
    }
    // 2. 실행결과 보여주는 로직
    //  이름을 받고 조건에 따라서 한줄 추가해주기
    // 실행 결과는 한줄만 보여주는 건데 그냥 그 밑에서 출력을 해야겠다.
    public void run(int n){
        System.out.println("실행 결과");
        while(n>0) {
            for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {// 이게 무슨 문법이지
                String name = entry.getKey();
                Integer score = entry.getValue();

                int move = Randoms.pickNumberInRange(0,9);
                if(move>=4){
                    score+=1;
                    model.getRacers().put(name,score);
                }

                System.out.print(name + " : ");

                for (int i = 0; i < score; i++) {
                    System.out.print("-");
                }

                System.out.println();

            }
            System.out.println();
            n--;
        }
    }

    //모델에서 받아올수있는지 체크해볼께
    public void getCheck(){
        for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {// 이게 무슨 문법이지
            String name = entry.getKey();
            Integer score = entry.getValue();
            System.out.println(name + " : " + score);

        }
    }


    //3.최종 우승자 : 하고 그 다음 이름 배열을 넣어주는 뷰단
    public void callWinner(String winners){
        // 아니면 아예 세팅해서 넘겨버리지 뭐
        // pobi, jun 이렇게
        getCheck();

        System.out.println("최종 우승자 : "+winners);
        //뷰단에도 적절하게 들어가는게 맞는것같은데
    }
}
