package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
    private List<String> carNames;
    private Model model;
    private View view;
    private int attemptCount;

    public Controller() {
        this.carNames = new ArrayList<>();
        this.model = new Model(carNames);
        this.view = new View();
    }

    public void initCarNames(){
        view.inputCarNames();
        String input = Console.readLine();
        this.carNames = Arrays.asList(input.split(","));
        model = new Model(carNames);

    }

    public void  inputAttemptCount(){
        view.attemptCount();
        attemptCount= Integer.parseInt(Console.readLine());
    }


    public void startRace(){
        playRace(attemptCount);
        printResult();
        printWinners();
    }


    public void playRace(int n) {
        for (int turn = 0; turn < n; turn++) {
            for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
                String name = entry.getKey();
                int score = entry.getValue();

                int move = Randoms.pickNumberInRange(0, 9);
                System.out.println("move = " + move);
                if (move >= 4) {

                    score += 1;
                    model.getCars().put(name, score);
                }
            }
            // 한 턴이 끝나면 View에 상태 전달
            view.printRoundResult(model.getCars());
            System.out.println();
        }
    }

    private void printResult() {
        for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
            System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
        }
    }

    public void printWinners() {
        int maxScore = 0;
        //1 최대 점수 구하기
        // 1️⃣ 먼저 최대 점수 구하기
        for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
            maxScore = Math.max(maxScore, entry.getValue());
        }

        // 2️⃣ 최대 점수와 같은 이름들 모으기
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
            if (entry.getValue() == maxScore) {
                winners.add(entry.getKey());
            }
        }

        // 3️⃣ View로 출력
        String winnerNames = String.join(", ", winners);
        view.printWinners(winnerNames);
    }
}

