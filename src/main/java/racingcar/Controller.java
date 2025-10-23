package racingcar;

import camp.nextstep.edu.missionutils.Console;
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

    //1
    public void initCarNames() {
        view.inputCarNames();
        String input = Console.readLine();
        this.carNames = Arrays.asList(input.split(","));
        model = new Model(carNames);

    }

    //2
    public void inputAttemptCount() {
        view.attemptCount();
        attemptCount = Integer.parseInt(Console.readLine());
    }


    //3
    public void startRace() {
        playRace(attemptCount);
        //5
        printResult();
        //6
        showWinners();
    }


    //4
    public void playRace(int n) {
        for (int turn = 0; turn < n; turn++) {
            model.playTurn();
            view.printRoundResult(model.getCars());
        }
    }

    //5 뷰로 넘겨
    private void printResult() {
        for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
            System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
        }
        System.out.println();
    }

    //6
    public void showWinners() {
        List<String> winners = model.getWinnersList();
        view.printWinners(winners);
    }

}

