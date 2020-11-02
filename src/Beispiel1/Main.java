package Beispiel1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    private static List<Integer> numbers = new ArrayList<>();
    private static int chunks = 0;
    private static int divider = 0;

    public static void main(String[] args) {
        // "number.csv" einlesen und in ArrayList adden. Wenn keine Zahl dann nicht lesen
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("numbers.csv"))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(":");
                try {
                    for (int i = 0; i < splitted.length; i++) {
                        numbers.add(Integer.parseInt(splitted[i]));
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//----------------------------------------------------------------------------------------------------------------------
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("chunks> ");
                chunks = Integer.parseInt(sc.nextLine());
                System.out.print("divider> ");
                divider = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input!");
            }

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(chunks);
            int size = (int) Math.ceil((double) numbers.size() / (double) chunks);
            List<Integer> partialList;

            for (int i = 0; i < numbers.size(); i += size) {
                if (i + size < numbers.size()) {
                    partialList = numbers.subList(i, (i + size));// Teil einer Liste
                } else {
                    partialList = numbers.subList(i, numbers.size());// Rest der Liste
                }
                Runnablethread rt = new Runnablethread(partialList, divider);
                executor.execute(rt);
            }

            executor.shutdown();
            return;
        }
    }
}
