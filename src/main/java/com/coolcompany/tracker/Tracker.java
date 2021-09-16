package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Tracker extends Form {

    public static Tracker instance;

    private JButton btnFind, btnEdit;
    private JButton btnBinarySearch, btnBirthdayInMonth;

    private JTextField tfFind;
    private JTextArea viewTextArea;

    private JCheckBox chkShowAll;

    private List<PersonData> pdata;


    public Tracker(String title) {
        super(title);
        instance = this;
    }

    public void run() {
        windowFrame = this;
        setup();
    }

    @Override
    protected void setup() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load all data
        pdata = FileIO.read();

        // add GUI objects
        addTitleLabel();

        addLabels();
        addTextFields();
        addButtons();
        addTextArea();
        addCheckBox();

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

    public List<PersonData> getPersonData() {
        return pdata;
    }

    public int getDataSize() {
        return pdata.size();
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
            WINDOW_COLUMN_ONE_X,
            WINDOW_SIZE_HEIGHT - 64,
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
        tfFind = UIComponentLibrary.createJTextField(
            7,
            WINDOW_COLUMN_ONE_X + 40,
            WINDOW_SIZE_HEIGHT - 64,
            this,
            layout
        );
    }


    private void addTextArea() {
        viewTextArea = UIComponentLibrary.createJTextArea(
            10, 40,
            WINDOW_COLUMN_ONE_X,
            32,
            this,
            layout
        );

        updateTextViewAll(true);
    }


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


    private void addButtons() {
        // Find action
        ActionListener findAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String text = tfFind.getText();

                if (text.length() == 0) {
                    return;
                }

                PersonData person = getWithName(text);

                if (person == null) {
                    JOptionPane.showMessageDialog(null, String.format("Could not find anyone with name: \"%s\"", text), "Search Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateTextViewPerson(person);
            }
        };

        ActionListener editAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new TrackerEdit("Edit Entries").run();
            }
        };

        
        btnFind = UIComponentLibrary.createJButton(
            "Find",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_TWO_X - 24,
            WINDOW_SIZE_HEIGHT - 64,
            findAction,
            this,
            layout
        );

        btnEdit = UIComponentLibrary.createJButton(
            "Edit",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            WINDOW_SIZE_HEIGHT - 64,
            editAction,
            this,
            layout
        );
    }


    private void updateTextViewBase() {
        LocalDate now = LocalDate.now();

        String valueOfSearchString = String.format("%s - %s", now.getDayOfMonth(), now.getMonth());
        String textForLargeTextArea = "Birthdays for this month: " + valueOfSearchString + "\n\n";
        textForLargeTextArea += "Person\tLikes\tDislikes\tDay  Month\n";
        textForLargeTextArea += "-------------------------------------------------------------------------------\n";

        viewTextArea.setText(textForLargeTextArea);
    }

    public void updateTextViewPerson(final PersonData person) {
        updateTextViewBase();

        String textForLargeTextArea = viewTextArea.getText();

        textForLargeTextArea += String.format(
            "%s\t%s\t%s\t%d    %d\n",
            person.getName(),
            person.getLikes(),
            person.getDislikes(),
            person.getFriendDay(),
            person.getFriendMonth()
        );

        viewTextArea.setText(textForLargeTextArea);
    }


    public void updateTextViewAll(boolean thisMonth) {
        updateTextViewBase();

        String textForLargeTextArea = viewTextArea.getText();

        List<PersonData> people;

        if (thisMonth) {
            people = getPersonMonth(LocalDate.now().getMonthValue());
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
     * @return person with name
     */
    private PersonData getWithName(String name) {
        for(PersonData person : pdata) {
            if (person.getName() == name) {
                return person;
            }
        }

        return null;
    }
}