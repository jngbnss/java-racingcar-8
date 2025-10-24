package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private Model model;
    private final View view;
    private int attemptCount;
    private static final String INVALID_ATTEMPT_MSG = "시도 횟수는 1 이상이어야 합니다.";

    public Controller() {
        this.view = new View();
    }

    public void run() {
        initCarNames();
        inputAttemptCount();
        startRace();
    }


    public void initCarNames() {
        view.showCarNameInputPrompt();//여기확인
        String input = Console.readLine();
        List<String> carNames = Arrays.asList(input.split(","));
        model = new Model(carNames);

    }

    public void inputAttemptCount() {
        view.showAttemptCount();
        String input = Console.readLine();
        int attempt = parserAttemptCount(input); // 문자열->int 변환
        validateAttemptCount(attempt); //0 이하면 예외 발생
        this.attemptCount = attempt;
    }
    private void validateAttemptCount(int attmept) {
        if (attmept < 1) {
            throw new IllegalArgumentException(INVALID_ATTEMPT_MSG);
        }
    }

    private int parserAttemptCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자로 입력해야 합니다.");
        }
    }


    public void startRace() {
        view.showRaceStart();

        for (int turn = 0; turn < attemptCount; turn++) {
            model.playTurn(); //Model이 자체적으로 상태 관리
            view.printRoundResult(model.getCurrentState()); //여기확인
        }
        showWinners();
    }

    public void showWinners() {
        List<String> winners = model.getWinnersList();
        view.showWinners(winners);
    }

}

