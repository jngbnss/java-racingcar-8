package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
    private List<String> carNames;
    private Model model;
    private final View view;
    private int attemptCount;

    public Controller() {
        this.carNames = new ArrayList<>();
        this.model = new Model(carNames);
        this.view = new View();
    }

    public void run() {
        initCarNames();
        inputAttemptCount();
        startRace();
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
        String input = Console.readLine();

        int attempt = parserAttemptCount(input); // 문자열->int 변환
        Validator.validateAttemptCount(attempt); //0 이하면 예외 발생
        this.attemptCount = attempt;
    }

    private int parserAttemptCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자로 입력해야 합니다.");
        }
    }


    //3
    public void startRace() {
        view.printRaceStartMessage();
        playRace(attemptCount);
        //5
        //printResult(); // 잘나오는데? 뭔가 꼬이는데?

        //6
        showWinners();
    }


    //4
    public void playRace(int n) {
        for (int turn = 0; turn < n; turn++) {
            model.playTurn();
            view.printRoundResult(model.getCars()); //여기서만 사용하나?
            // 매개변수를 던지는 명령이 들어오면 모델에서 다뤄야하나?
        }
    }

    //5 뷰로 넘겨
//    private void printResult1() {
//
//        for (Map.Entry<String, Integer> entry : model.getCars().entrySet()) {
//            System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
//
//        }
//        System.out.println();
//    }
//    private void printResult(){
//        view.printFinalResult(model.getCars());
//    }

    //6
    public void showWinners() {
        List<String> winners = model.getWinnersList();
        view.printWinners(winners);
    }

}

