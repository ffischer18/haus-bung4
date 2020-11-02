package Beispiel2;

import java.util.concurrent.Callable;

public class Callablethread implements Callable<Integer> {
    private int max;
    private int min;

    public Callablethread(int max, int min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = min; i < max; i++) {
            sum += i;
        }
        return sum;
    }
}
