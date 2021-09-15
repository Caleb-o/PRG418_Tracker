package com.coolcompany.tracker;

import java.time.LocalDate;
import java.util.Arrays;

public class BinarySearchDateArray {

    public static void main(String[] args) {

        LocalDate today = LocalDate.parse("2021-09-08");
        LocalDate yesterday = LocalDate.parse("2021-09-07");
        LocalDate tomorrow = LocalDate.parse("2021-09-09");

        LocalDate[] localDates = {today, yesterday, tomorrow};

        System.out.println("unsorted");
        for(LocalDate ld : localDates) {
            System.out.println(ld);
        }

        Arrays.sort(localDates);

        System.out.println("sorted");
        for(LocalDate ld : localDates) {
            System.out.println(ld);
        }

        LocalDate key = LocalDate.parse("2021-09-09");
        int index = Arrays.binarySearch(localDates, key);

        System.out.println(index);

    }

}
