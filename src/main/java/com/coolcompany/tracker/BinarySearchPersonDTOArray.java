package com.coolcompany.tracker;

import java.util.Arrays;

public class BinarySearchPersonDTOArray {

    public static void main(String[] args) {

        PersonData person1 = new PersonData("xavier", "cricket", "footy");
        person1.setFriendDay(1); // first day of month
        person1.setFriendMonth(10); // oct


        PersonData person2 = new PersonData("albert", "cake", "coffee");
        person2.setFriendDay(4); // fourth day of month
        person2.setFriendMonth(9); // september

        PersonData person3 = new PersonData("oliver", "cars", "the ocean");
        person3.setFriendDay(9); // ninth day of month
        person3.setFriendMonth(3); // march

        PersonData[] persons = {person1, person2, person3};

        System.out.println("persons before sorted");
        for(PersonData person : persons) {
            System.out.println(person.getName());
        }

        Arrays.sort(persons);

        System.out.println("persons after sorted");
        for(PersonData person : persons) {
            System.out.println(person.getName());
        }

        int index = Arrays.binarySearch(persons, "oliver");
        System.out.println(index);

    }

}
