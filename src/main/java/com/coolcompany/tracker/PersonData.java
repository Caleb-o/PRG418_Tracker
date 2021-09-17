package com.coolcompany.tracker;

import java.time.LocalDate;

public class PersonData {

    private String name;
    private String likes;
    private String dislikes;
    private int friendDay;
    private int friendMonth;

    /**
     * Create a person only using name
     * @param name
     */
    public PersonData(String name) {
        this(name, "None", "None", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
    }

    /**
     * Create a person with string values
     * @param name
     * @param likes
     * @param dislikes
     */
    public PersonData(String name, String likes, String dislikes) {
        this(name, likes, dislikes, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
    }

    /**
     * PersonData constructor for CSV data (Expects 5 args)
     * @param data
     */
    public PersonData(String[] data) {
        this(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4].substring(0, data[4].length() - 1)));
    }

    /**
     * Base constructor to set all fields
     * @param name
     * @param likes
     * @param dislikes
     * @param friendDay
     * @param friendMonth
     */
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
}