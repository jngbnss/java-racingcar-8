package racingcar;

public class Validator {
    public static void validateAttemptCount(int attmept){
        if(attmept<=0)
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
    }
}
