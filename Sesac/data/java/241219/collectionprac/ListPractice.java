package org.example.collectionprac;

import java.util.ArrayList;
import java.util.List;

public class ListPractice {
    public static void main(String[] args) {

        // 1.
//        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        // data 삽입
        list.add(1);
        list.add(3);

        System.out.println(list);

        list.add(1, 100);

        System.out.println(list);

        // data 접근
        System.out.println(list.getFirst());
        System.out.println(list.get(1));

        // data 수정
        list.set(1, 1000);
        System.out.println(list);

        // list 반복 돌 때, "길이"
        System.out.println(list.size());

        // 빈 list인지 확인
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            System.out.println("list is empty");
        }

        // data가 list에 있는지 확인
        System.out.println(list.contains(1));


        /// ////////////////////////////////////////////////////////////////////////////
        // 2.
        List<String> strings = new ArrayList<>();

        // data 삽입
        strings.add("1");
        System.out.println(strings);

        for (Integer i : list) {
            System.out.println(i);
        }

        for (int index = 0; index < list.size(); index++) {
            int value = list.get(index);
            System.out.println(value);
        }

    }
}
