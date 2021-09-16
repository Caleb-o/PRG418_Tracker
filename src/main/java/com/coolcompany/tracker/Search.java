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

		int index = Arrays.binarySearch(pdata, name);
		return pdata[index];
	}
}
