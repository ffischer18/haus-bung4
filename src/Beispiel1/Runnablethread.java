package Beispiel1;

import java.util.List;
import java.util.Scanner;

public class Runnablethread implements Runnable{
    private List<Integer> numbers;
    private int divider;

    public Runnablethread(List<Integer> numbers, int divider) {
        this.numbers = numbers;
        this.divider = divider;
    }

    @Override
    public void run() {
        for (int number:numbers) {
            if((number % divider) == 0){
                System.out.println(number);
            }
        }
    }
}
