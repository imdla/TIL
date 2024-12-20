package org.example.streampractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        integers.add(1);
//        integers.add(2);
//        integers.add(3);
//        integers.add(4);

//        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> list = new ArrayList<Integer>(List.of(3, 1, 2, 2, 4));
        System.out.println(list);

        List<Integer> over1List = list.stream() // List를 stream으로 변경
                .filter(x -> x > 1)     // filter 적용
                .collect(Collectors.toList());// List로 변경

        System.out.println(over1List);
    }
}
