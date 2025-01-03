package org.example.streampractice;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPrac {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 짝수만 필터링하여 리스트 반환
        List<Integer> evenList = numbers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenList);

        // 모든 숫자에 2를 곱하여 리스트 반환
        List<Integer> multiply2List = numbers.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(multiply2List);

        // 숫자들의 합계 계산
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);

        // 5보다 큰 숫자의 개수 계산
        long over5Count = numbers.stream()
                .filter(x -> x > 5)
                .count();
        System.out.println(over5Count);

        List<String> words = Arrays.asList("apple", "banana", "cherry", "fineapple", "apple");

        // 길이가 5 이상인 단어들만 필터링하여 리스트 반환
        List<String> length5OverList = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.toList());
        System.out.println(length5OverList);

        // 모든 단어들을 대문자로 변환해 리스트 반환
        List<String> upperList = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperList);

        // 중복된 단어 제거하고 알파벳 순 정렬하여 리스트 반환
        List<String> distictSortedWords = words.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(distictSortedWords);

        // 각 단어의 길이를 리스트로 변환
        List<Integer> lengthWords = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lengthWords);


        List<Product> products = Arrays.asList(
                new Product("notebook", 1200000, "SALE"),
                new Product("mouse", 50000, "SALE"),
                new Product("keyboard", 150000, "SOLD_OUT"),
                new Product("monitor", 350000, "SOLD_OUT"),
                new Product("speaker", 400000, "SALE")

        );

        // 상품들의 이름 리스트 반환
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(productNames);

        // 20만원 이상인 상품들의 이름 리스트 반환
        List<String> over20Names = products.stream()
                .filter(p -> p.getPrice() >= 200000)
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(over20Names);

        // 판매중인 상품의 가격 합
        int salePriceSum = products.stream()
                .filter(p -> p.getStatus().equals("SALE"))
                .mapToInt(Product::getPrice)
                .sum();
        System.out.println(salePriceSum);

        // 판매 중이면서 20만원 이상인 상품들의 이름 리스트 반환
        List<String> saleOver20Names = products.stream()
                .filter(p -> p.getStatus().equals("SALE"))
                .filter(p -> p.getPrice() >= 200000)
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(saleOver20Names);
    }

}
