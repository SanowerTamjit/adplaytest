package com.santam.adplaytest.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class test {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 2};
        System.out.println("Array Size: "+arr.length);
        List<Integer> arrList = IntStream.of(arr).boxed().collect(Collectors.toList());
//        arr = arrList.stream()
//                .distinct()
//                .collect(Collectors.toList())
//                .stream().mapToInt(Integer::intValue).toArray();
//        System.out.println("Array Size: "+arr.length);

        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }

        arr = new int[set.size()];
        int i = 0;
        for (Integer integer : set) {
            arr[i++] = integer;
        }

        System.out.println("Array Size: "+arr.length);

//
//
//        List<Integer> listWithoutDuplicates = listWithDuplicates.stream()
//                .distinct()
//                .collect(Collectors.toList());

    }


}
