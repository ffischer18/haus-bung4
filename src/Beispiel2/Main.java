package Beispiel2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Callablethread> list = new ArrayList<>();
        int number = 0;
        while (true){
            try {
                System.out.print("n> ");
                number = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException ex){
                System.out.println("Wrong input");
                continue;
            }
            // jeder Thread addiert nur 100 Nummern deswegen 1 Thread pro 100 Nummern
            int partial = (int) Math.ceil(number / 100);
            if(partial < 1){
                partial = 1;
            }
            ExecutorService executor = Executors.newFixedThreadPool(partial);

            int max = 0;
            int min = 0;
            // aufteilen von bis
            for (int i = 0; i < number; i+= 100) {
                if((i + 100) < number){
                    max = i + 100;
                    min = i;
                }
                else{
                    max = number + 1;
                    min = i;
                }
                Callablethread ct = new Callablethread(max, min);
                list.add(ct);
            }


            List<Future<Integer>> result = executor.invokeAll(list); // Threads abwarten
            int sum = 0;
            //berechnen
            for (Future<Integer> f:result) {
                try {
                    sum += f.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sum: " + sum);
            break;
        }
    }
}
