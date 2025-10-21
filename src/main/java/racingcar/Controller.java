package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
    private List<String> racersName;
    private Model model;
    private View view;
    private int mx;

    public Controller() {
        this.racersName = new ArrayList<>();
        this.model = new Model(racersName);
        this.view = new View();
    }

    public void initRacers(){
        view.settingPrint();
        String input = Console.readLine();
        this.racersName = Arrays.asList(input.split(","));

        model = new Model(racersName);
        this.view = new View();

        view.settingTry();
        int cnt = Integer.parseInt(Console.readLine());

        playRace(cnt);
        printResult();
        printWinners();

    }



    public void playRace(int n) {
        for (int turn = 0; turn < n; turn++) {
            for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {
                String name = entry.getKey();
                int score = entry.getValue();

                int move = Randoms.pickNumberInRange(0, 9);
                System.out.println("move = " + move);
                if (move >= 4) {

                    score += 1;
                    model.getRacers().put(name, score);
                }
            }
            // 한 턴이 끝나면 View에 상태 전달
            view.showRacers(model.getRacers());
            System.out.println();
        }
    }

    private void printResult() {
        for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {
            System.out.print(entry.getKey()+" : "+entry.getValue()+" ");
        }
    }

    public void printWinners(){
        int maxScore =0;
        //1 최대 점수 구하기
        // 1️⃣ 먼저 최대 점수 구하기
        for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {
            maxScore = Math.max(maxScore, entry.getValue());
        }

        // 2️⃣ 최대 점수와 같은 이름들 모으기
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : model.getRacers().entrySet()) {
            if (entry.getValue() == maxScore) {
                winners.add(entry.getKey());
            }
        }

        // 3️⃣ View로 출력
        String winnerNames = String.join(", ", winners);
        view.callWinner(winnerNames);
    }
}

