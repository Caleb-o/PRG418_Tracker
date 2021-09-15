package com.coolcompany.tracker;

import java.util.Arrays;

public class BinarySearchPersonDTOArray {

    public static void main(String[] args) {

        PersonDTO person1 = new PersonDTO();
        person1.setName("xavier");
        person1.setLikes("cricket");
        person1.setDislikes("footy");
        person1.setFriend_Day(1); // first day of month
        person1.setFriend_Month(10); // oct


        PersonDTO person2 = new PersonDTO();
        person2.setName("albert");
        person2.setLikes("cake");
        person2.setDislikes("coffee");
        person2.setFriend_Day(4); // fourth day of month
        person2.setFriend_Month(9); // september

        PersonDTO person3 = new PersonDTO();
        person3.setName("oliver");
        person3.setLikes("cars");
        person3.setDislikes("the ocean");
        person3.setFriend_Day(9); // ninth day of month
        person3.setFriend_Month(3); // march

        PersonDTO[] persons = {person1, person2, person3};

        System.out.println("persons before sorted");
        for(PersonDTO person : persons) {
            System.out.println(person.getName());
        }

        Arrays.sort(persons);

        System.out.println("persons after sorted");
        for(PersonDTO person : persons) {
            System.out.println(person.getName());
        }

        int index = Arrays.binarySearch(persons, "oliver");
        System.out.println(index);

    }

}
