package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private Model model;
    private final View view;
    private final InputHandler inputHandler;
    private int attemptCount;
    private static final String INVALID_ATTEMPT_MSG = "시도 횟수는 1 이상이어야 합니다.";


    public Controller() {
        this.view = new View();
        this.inputHandler = new InputHandler();
    }

    public void run() {
        initCarNames();
        inputAttemptCount();
        startRace();
    }

    public void initCarNames() {
        view.showCarNameInputPrompt();
        List<String> carNames = inputHandler.readCarNames();
        model = new Model(carNames);
    }

    public void inputAttemptCount() {
        view.showAttemptCount();
        this.attemptCount = inputHandler.readAttemptCount();
    }


    // 검증 로직이 단일하여 현재는 Controller에 포함. 추후 검증 항목이 늘어나면 Validator로 분리 예정.
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

