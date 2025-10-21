package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private List<String> racersName;
    private Model model;
    private View view;

    public Controller() {
        this.racersName = new ArrayList<>();
        this.model = new Model(racersName);
        this.view = new View(model);
    }
    //멤버변수
    // 일단 입력값 받아서 구분해 놓는거를 주려고하니까
//    private List<String> racersName = new ArrayList<>();
    //생성자 // 뷰를 시작하면 좋겠어

    // 기본 생성자 없어도 되는거아냐?
//    public Controller(List<String> racersNmae) {
//        view.settingPrint();
//        // 여기서 입력값 받는 메서드를 넣는게 좋은가?
//        // 생성자에 메서드까지면 일이 너무 많아진다.
//        // 여기서 뷰단에서 입력받는거 넣으면 좋을것같은데
//
//        this.racersNmae = racersNmae;
//    }
    // 입력값받는걸 생성자에서 하는게 좋을까
    // 생성자가 편하지 않을까?
    // 아니면 메서드에서 받는게 좋을까
    public void initRacers(){
        view.settingPrint();
        String input = Console.readLine();
        this.racersName = Arrays.asList(input.split(","));

        model = new Model(racersName);
        this.view = new View(model);

        view.settingTry();
//        String cnt = Console.readLine();
        int cnt = Integer.parseInt(Console.readLine());
        view.run(cnt);

    }



    //메서드
}

