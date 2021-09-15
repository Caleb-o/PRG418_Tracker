package com.coolcompany.tracker;

import java.util.Arrays;
import java.util.List;

public class FileIO {

    /**
     *
     * @param name is the name of the person/s being searched for
     * @return a List of PersonDTO for each matching person OR NULL if not found
     */
    public List<PersonDTO> read(final String name) {

        if (name.equals("bob")) {
            return null;
        }

        // this should go to the CSV and find something
        // for now just return a List with a single PersonDTO
        PersonDTO person = new PersonDTO();
        person.setName(name);
        person.setLikes("Java");
        person.setDislikes("csharp");

        return Arrays.asList(person);
    }

    /**
     * Create a new Person record in the MyFriednData.csv
     * @param person
     */
    public void create(final PersonDTO person) {
    }

    public void update(final PersonDTO person) {

    }

    public void delete(final PersonDTO person) {

    }

}
