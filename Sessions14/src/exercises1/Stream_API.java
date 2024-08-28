package exercises1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Stream_API {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 4, 3, 5, 2, 6, 7, 9, 8, 8, 69, 89, 8, 9);
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");

        int maxNumber = numbers.stream()
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("Số lớn nhất: " + maxNumber);

        List<Integer> number_even = numbers.stream().filter(num-> num%2==0).toList();
        System.out.println("Danh sách các số chẵn: "+number_even);

        System.out.println("số nhâp vào là:");
        Scanner sc= new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        List<Integer> greaterNum = numbers.stream().filter(num1 -> num1>num).toList();
        System.out.println("Số lớn hơn số nhập vào num: "+greaterNum);

        int sum= numbers.stream().reduce(0, Integer::sum);
        System.out.println("Tổng các số trong mảng: "+sum);

        boolean result= numbers.stream().anyMatch(s ->s%2==0);
        System.out.println("mảng có chứa ít nhất 1 số chẵn hay không: "+result);

        int x = 1;
        int y = 10;
        List<Integer> rangeList = IntStream.rangeClosed(x, y).boxed().toList();
        System.out.println("Danh sách các số từ " + x + " đến " + y + ": " + rangeList);

        System.out.println("Danh sách các số thoe thứ tự tăng dần");
        numbers.stream().sorted().forEach(item->{
            System.out.print(item+" ");
        });

        System.out.println("Chuyển các chuỗi thành chữ in hoa: ");
        strings.stream().map(String::toUpperCase).forEach(System.out::println);


    }
}
