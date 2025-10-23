package racingcar;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Controller controller = new Controller();

        controller.initCarNames();
        controller.inputAttemptCount();
        controller.startRace();
    }
}
