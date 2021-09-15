package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Tracker extends Form {
    private JButton btnFind, btnNew, btnSave, btnDelete;
    private JButton btnBinarySearch, btnBirthdayInMonth;
    private JButton btnForward, btnFastForward, btnBack, btnRewind;

    private JTextField textFieldFind;
    private JTextArea viewTextArea;

    private List<PersonData> pdata;


    public Tracker(String title) {
        super(false, title);
    }

    public void run() {
        windowFrame = this;
        setup();
    }

    @Override
    protected void setup() {
        if (isChild) {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        // Load all data
        pdata = FileIO.read();

        // add GUI objects
        addTitleLabel();

        addLabels();
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
    }

    private void addTitleLabel() {
        JLabel label = UIComponentLibrary.createJLabel(
            "Birthday Tracker",
            WINDOW_COLUMN_ONE_X,
            10,
            this,
            layout
        );
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.BLACK);
    }

    private void addLabels() {
        JLabel label = UIComponentLibrary.createJLabel(
            "Find:    ",
            WINDOW_COLUMN_THREE_X,
            10,
            this,
            layout
        );

        Font currentFont = label.getFont();
        label.setFont(new Font(label.getName(), Font.BOLD, currentFont.getSize()));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
    }

    private void addTextFields() {
        textFieldFind = UIComponentLibrary.createJTextField(
            7,
            WINDOW_COLUMN_THREE_X + 40,
            10,
            this,
            layout
        );
    }

    private void addTextArea() {

        viewTextArea = UIComponentLibrary.createJTextArea(
            7, 30,
            WINDOW_COLUMN_ONE_X,
            60,
            this,
            layout
        );

        LocalDate now = LocalDate.now();

        String valueOfSearchString = String.format("%s - %s", now.getDayOfMonth(), now.getMonth());
        String textForLargeTextArea = "Birthdays for this month: " + valueOfSearchString + "\n\n";
        textForLargeTextArea += "Person\tLikes\tDislikes\tDay  Month\n";
        textForLargeTextArea += "-------------------------------------------------------------------------------";

        // Populate view from startup if the date matches this month
        for(PersonData person : getPersonMonth(now.getMonthValue())) {
            textForLargeTextArea += String.format(
                "%s\t%s\t%s\t%d  %d\n",
                person.getName(),
                person.getLikes(),
                person.getDislikes(),
                person.getFriendDay(),
                person.getFriendMonth()
            );
        }

        viewTextArea.setText(textForLargeTextArea);
    }


    private void addButtons() {
        // Find action
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO: Set text area to show all results and set Dialog to use results count
                JOptionPane.showMessageDialog(windowFrame, String.format("%d results found", pdata.size()));
            }
        };

        btnFind = UIComponentLibrary.createJButton(
            "Find",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            32,
            al,
            this,
            layout
        );

        // TODO: Add this to an edit button
        // new TrackerEdit("Edit Entries", pdata).run();
    }


    /**
     * @param month
     * @return a list of persondata that is in month
     */
    private List<PersonData> getPersonMonth(int month) {
        List<PersonData> personMonth = new ArrayList<PersonData>();

        for(PersonData person : pdata) {
            if (person.getFriendMonth() == month) {
                personMonth.add(person);
            }
        }

        return personMonth;
    }


    /**
     * @param day
     * @param month
     * @return a list of persondata that is in day - month
     */
    private List<PersonData> getPersonDayMonth(int day, int month) {
        List<PersonData> personDayMonth = new ArrayList<PersonData>();

        for(PersonData person : pdata) {
            if (person.getFriendDay() == day && person.getFriendMonth() == month) {
                personDayMonth.add(person);
            }
        }

        return personDayMonth;
    }


    /**
     * @param name
     * @return all persondata with name
     */
    private List<PersonData> getWithName(String name) {
        List<PersonData> personsName = new ArrayList<PersonData>();

        for(PersonData person : pdata) {
            if (person.getName() == name) {
                personsName.add(person);
            }
        }

        return personsName;
    }
}