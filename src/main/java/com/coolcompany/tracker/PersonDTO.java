package com.coolcompany.tracker;

public class PersonDTO implements Comparable {

    private String name;
    private String likes;
    private String dislikes;
    private int Friend_Day;
    private int Friend_Month;

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

    public int getFriend_Day() {
        return Friend_Day;
    }

    public void setFriend_Day(int Friend_Day) {
        this.Friend_Day = Friend_Day;
    }

    public int getFriend_Month() {
        return Friend_Month;
    }

    public void setFriend_Month(int Friend_Month) {
        this.Friend_Month = Friend_Month;
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
        if (otherPerson instanceof PersonDTO) {
            // we can compare the getName() methods against each other
            return CharSequence.compare(this.getName(), ((PersonDTO)otherPerson).getName());
        }

        // return 0 by default. Theoretically this should never happen
        // as we only compare with a String (name field) or a PersonDTO.
        return 0;

    }

}
