package racingcar;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Controller controller = new Controller();

        controller.initRacers();
        System.out.println("===============");
        controller.getCheck();
        // 이렇게 컨트롤러로 하는게 맞나?
        controller.checkWinners();

    }
}
