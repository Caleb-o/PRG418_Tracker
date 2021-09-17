package com.coolcompany.tracker;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Search {
	/**
     * @param month
     * @return a list of persondata that is in month
     */
    public static List<PersonData> getPersonMonth(int month) {
        List<PersonData> personMonth = new ArrayList<PersonData>();

        for(PersonData person : Tracker.instance.getPersonData()) {
            if (person.getFriendMonth() == month) {
                personMonth.add(person);
            }
        }

        return personMonth;
    }


    /**
     * @param day
     * @param month
     * @return every person with a birthday on the day and month
     */
    public static List<PersonData> getPersonDayMonth(int day, int month) {
        List<PersonData> personMonth = new ArrayList<PersonData>();

        for(PersonData person : Tracker.instance.getPersonData()) {
            if (person.getFriendDay() == day && person.getFriendMonth() == month) {
                personMonth.add(person);
            }
        }

        return personMonth;
    }


    /**
     * @param name
     * @return person with name
     */
    public static PersonData getWithName(String name) {
        for(PersonData person : Tracker.instance.getPersonData()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }

        return null;
    }


	/**
	 * @param name
	 * @return person with name using a binary search
	 */
	public static PersonData getWithNameBinary(String name) {
		PersonData[] pdata = new PersonData[Tracker.instance.getDataSize()];
		Tracker.instance.getPersonData().toArray(pdata);

		Arrays.sort(pdata);

        // For debugging purposes, to check for sorted names
        for(PersonData person : pdata) {
            System.out.println(person.getName());
        }

		int index = Arrays.binarySearch(pdata, name);
		return pdata[index];
	}
}
