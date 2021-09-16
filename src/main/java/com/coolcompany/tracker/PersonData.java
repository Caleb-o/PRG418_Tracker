package com.coolcompany.tracker;

import java.time.LocalDate;

public class PersonData implements Comparable<Object> {

    private String name;
    private String likes;
    private String dislikes;
    private int friendDay;
    private int friendMonth;

    public PersonData(String name) {
        this(name, "None", "None", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
    }

    public PersonData(String name, String likes, String dislikes) {
        this(name, likes, dislikes, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
    }

    // PersonData constructor for CSV data (Expects 5 args)
    public PersonData(String[] data) {
        this(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4].substring(0, data[4].length() - 1)));
    }

    public PersonData(String name, String likes, String dislikes, int friendDay, int friendMonth) {
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
        this.friendDay = friendDay;
        this.friendMonth = friendMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public int getFriendDay() {
        return friendDay;
    }

    public void setFriendDay(int friendDay) {
        this.friendDay = friendDay;
    }

    public int getFriendMonth() {
        return friendMonth;
    }

    public void setFriendMonth(int friendMonth) {
        this.friendMonth = friendMonth;
    }

    /**
     * Generate a string in a CSV format
     * @return CSV format with persondata values
     */
    public String getCSVData() {
        return String.format("%s, %s, %s, %d, %d,", name, likes, dislikes, friendDay, friendMonth);
    }

    @Override
    public int compareTo(Object otherPerson) {

        // the 'instanceof' lets you check if a variable is of a
        // particular type.

        // Arrays.BinarySearch will call 'compareTo' with the 'key' as per the java doc.
        if (otherPerson instanceof String) {
            // the otherPerson variable is a String. So compare the String agisnt this.getName()
            return CharSequence.compare(this.getName(), (String)otherPerson);
        }

        // Arrays.sort will call 'compareTo' with a PersonDTO as 'otherPerson'
        if (otherPerson instanceof PersonData) {
            // we can compare the getName() methods against each other
            return CharSequence.compare(this.getName(), ((PersonData)otherPerson).getName());
        }

        // return 0 by default. Theoretically this should never happen
        // as we only compare with a String (name field) or a PersonDTO.
        return 0;

    }
}