package Beispiel3;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JavaStreamsTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<String> strings = Arrays.asList("Good", " ", "luck", "", "and", "", "have", "", "fun");

        while (true){
            System.out.println("1 - getCountEmptyString");
            System.out.println("2 - getCountLength3");
            System.out.println("3 - deleteEmptyString");
            System.out.println("4 - getMergedString");
            System.out.println("5 - getSquares");
            System.out.println("6 - getMax");
            System.out.println("7 - getMin");
            System.out.println("8 - getSum");
            System.out.println("9 - getAverage");
            System.out.println("0 - exit");
            System.out.print("-> ");
            int input;
            try {
                input = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException ex){
                System.out.println("Wrong input \n");
                continue;
            }

            switch (input){
                case 1:
                    System.out.println("Empty Strings: " + getCountEmptyString(strings) + "\n");
                    break;

                case 2:
                    System.out.println("Count Lenght[3]: " + getCountLength3(strings) + "\n");
                    break;

                case 3:
                    System.out.println("deleted... \n new List:");
                    deleteEmptyString(strings).forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Seperator -> ");
                    System.out.println("Merged String: " + getMergedString(strings, sc.nextLine()) + "\n");
                    break;

                case 5:
                    System.out.println("calculated... \n new List:");
                    getSquares(numbers).forEach(System.out::println);
                    break;

                case 6:
                    try {
                        System.out.println("Highest Integer: " + getMax(numbers) + "\n");
                    } catch (NoSuchElementException ex) {
                        System.out.println("couldn't find");
                    }
                    break;

                case 7:
                    try {
                        System.out.println("Lowest Integer: " + getMin(numbers) + "\n");
                    } catch (NoSuchElementException ex) {
                        System.out.println("couldn't find");
                    }
                    break;

                case 8:
                    System.out.println(("Sum: " + getSum(numbers)) + "\n");
                    break;

                case 9:
                    System.out.println("Average: " + getAverage(numbers) + "\n");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Wrong input \n");
                    break;
            }
        }
    }

    private static int getCountEmptyString(List<String> strings) {
        return (int) (strings.stream().filter(String::isEmpty).count());
    }

    private static int getCountLength3(List<String> strings) {
        return (int) (strings.stream().filter(s -> s.length() == 3)).count();
    }

    private static List<String> deleteEmptyString(List<String> strings) {
        return strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String sepereator) {
        return String.join(sepereator, strings);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().map(i -> i = i * i).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).max().orElseThrow(NoSuchElementException::new);
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).min().orElseThrow(NoSuchElementException::new);
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream().reduce(0, Integer::sum)/numbers.size();
    }

}
