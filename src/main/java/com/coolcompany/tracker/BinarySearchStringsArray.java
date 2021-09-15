package com.coolcompany.tracker;

import java.util.Arrays;

public class BinarySearchStringsArray {

    public static void main(String[] commandLineParameters) {

        System.out.println("commandLineParameters");
        for (String value : commandLineParameters) {
            System.out.println(value);
        }

        String key = "john";
        String[] values = {"albert", "fred", "john", "quark", "phil"};

        System.out.println("values before sorting");
        for (String value : values) {
            System.out.println(value);
        }

        Arrays.sort(values);

        System.out.println("values after sorting");
        for (String value : values) {
            System.out.println(value);
        }

        int index = Arrays.binarySearch(values, key);

        System.out.println("index " + index);
    }

}
