package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Tracker extends Form {

    public static Tracker instance;

    private JTextField tfFind;
    private JTextArea viewTextArea;

    private JCheckBox chkShowAll;

    private List<PersonData> pdata;


    public Tracker(String title) {
        super(title);
        instance = this;
    }

    
    /**
     * Run the form
     */
    @Override
    public void run() {
        windowFrame = this;
        setup();
    }


    /**
     * Setup the Form with GUI elements and content loading
     */
    @Override
    protected void setup() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load all data
        pdata = FileIO.read();

        // add GUI objects
        addTitleLabel();
        
        addCheckBox();
        addTextFields();
        addButtons();
        addTextArea();

        // apply layout to Form
        Container contentPane = this.getContentPane();
        contentPane.setLayout(layout);

        // put window in middle of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfScreenHeight = screenSize.height / 2;
        int halfScreenWidth = screenSize.width / 2;

        this.setLocation(
                halfScreenWidth - (WINDOW_SIZE_WIDTH / 2),
                halfScreenHeight - (WINDOW_SIZE_HEIGHT / 2)
        );

        this.setResizable(false);
        this.setPreferredSize(new Dimension(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT));
        this.pack();
        this.setVisible(true);

        // Check for birthdays today
        List<PersonData> today = Search.getPersonDayMonth(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());

        // There are birthdays today!
        if (today.size() > 0) {
            String names = "";

            int maxSize = today.size();

            for(int i = 0; i < maxSize; i++) {
                names += today.get(i).getName();

                // Better formatting for many members
                if (i < maxSize - 2) {
                    names += ", ";
                } else if (maxSize > 1 && i == maxSize - 2) {
                    names += " and ";
                }
            }

            // Present to user
            JOptionPane.showMessageDialog(null, String.format("\"%s\" have their birthday today!", names), "Birthday Today!", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * @return PersonData list field
     */
    public List<PersonData> getPersonData() {
        return pdata;
    }


    /**
     * @return size of PersonData list
     */
    public int getDataSize() {
        return pdata.size();
    }


    /**
     * Adds a title label element to the form
     */
    private void addTitleLabel() {
        JLabel label = UIComponentLibrary.createJLabel(
            "Birthday Tracker",
            WINDOW_COLUMN_ONE_X,
            5,
            this,
            layout
        );
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.BLACK);
    }


    /**
     * Add all text field elements to the form
     */
    private void addTextFields() {
        tfFind = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X,
            WINDOW_SIZE_HEIGHT - 64,
            this,
            layout
        );
    }


    /**
     * Add the text area element to the form
     */
    private void addTextArea() {
        viewTextArea = UIComponentLibrary.createJTextArea(
            10, 42,
            WINDOW_COLUMN_ONE_X,
            32,
            this,
            layout
        );

        updateTextViewAll(true);
    }


    /**
     * Add the checkbox element to the form
     */
    private void addCheckBox() {
        ActionListener showAllAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextViewAll(!chkShowAll.isSelected());
            }
        };
        
        chkShowAll = UIComponentLibrary.createJCheckBox(
            "Show All",
            WINDOW_COLUMN_ONE_X, 
            200,
            false,
            showAllAction,
            this,
            layout
        );
    }


    /**
     * Add button elements to the form
     */
    private void addButtons() {
        // ==== Action Listeners ====
        ActionListener findAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String text = tfFind.getText();

                if (text.length() == 0) {
                    return;
                }

                PersonData person = Search.getWithName(text);

                if (person == null) {
                    JOptionPane.showMessageDialog(null, String.format("Could not find anyone with name: \"%s\"", text), "Search Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateTextViewPerson(person);
            }
        };

        ActionListener searchAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String text = tfFind.getText();

                if (text.length() == 0) {
                    return;
                }

                PersonData person = Search.getWithNameBinary(text);

                if (person == null) {
                    JOptionPane.showMessageDialog(null, String.format("Could not find anyone with name: \"%s\"", text), "Search Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateTextViewPerson(person);
            }
        };

        ActionListener refreshAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextViewAll(true);
            }
        };

        ActionListener editAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new TrackerEdit("Edit Entries").run();
            }
        };


        // ==== Button Elements ====
        UIComponentLibrary.createJButton(
            "Find",
            BUTTON_SIZE_WIDTH - 20,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X + (20 + BUTTON_SIZE_WIDTH),
            WINDOW_SIZE_HEIGHT - 64,
            findAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Search",
            BUTTON_SIZE_WIDTH - 20,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X + (10 + BUTTON_SIZE_WIDTH) * 2 - 10,
            WINDOW_SIZE_HEIGHT - 64,
            searchAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Refresh",
            BUTTON_SIZE_WIDTH - 10,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X + 40,
            10,
            refreshAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Edit",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X + 30,
            WINDOW_SIZE_HEIGHT - 64,
            editAction,
            this,
            layout
        );
    }


    /**
     * Writes the base text to the text area element
     */
    private void updateTextViewBase() {
        LocalDate now = LocalDate.now();

        String valueOfSearchString = String.format("%s - %s", now.getDayOfMonth(), now.getMonth());
        String textForLargeTextArea = "Birthdays for this month: " + valueOfSearchString + "\n\n";
        textForLargeTextArea += "Person\tLikes\tDislikes\tDay    Month\n";
        textForLargeTextArea += "--------------------------------------------------------------------------------\n";

        viewTextArea.setText(textForLargeTextArea);
    }


    /**
     * Clear the text area and rewrites the base message, adding a single person data to the message
     * @param person
     */
    public void updateTextViewPerson(final PersonData person) {
        updateTextViewBase();

        String textForLargeTextArea = viewTextArea.getText();

        textForLargeTextArea += String.format(
            "%s\t%s\t%s\t%d\t\t%d\n",
            person.getName(),
            person.getLikes(),
            person.getDislikes(),
            person.getFriendDay(),
            person.getFriendMonth()
        );

        viewTextArea.setText(textForLargeTextArea);
    }


    /**
     * Clears the text area and rewrites the base message, writing all person data that is in thisMonth
     * @param thisMonth
     */
    public void updateTextViewAll(boolean thisMonth) {
        updateTextViewBase();

        String textForLargeTextArea = viewTextArea.getText();

        List<PersonData> people;

        // Check if we have set for entries this month to be viewed
        // It is overridden if the Show All option is selected
        if (thisMonth && !chkShowAll.isSelected()) {
            people = Search.getPersonMonth(LocalDate.now().getMonthValue());
        } else {
            people = pdata;
        }

        // Populate view from startup if the date matches this month
        for(PersonData person : people) {
            textForLargeTextArea += String.format(
                "%s\t%s\t%s\t%d    %d\n",
                person.getName(),
                person.getLikes(),
                person.getDislikes(),
                person.getFriendDay(),
                person.getFriendMonth()
            );
        }

        if (people.isEmpty()) {
            textForLargeTextArea += "No birthdays to show";
        }

        viewTextArea.setText(textForLargeTextArea);
    }
}