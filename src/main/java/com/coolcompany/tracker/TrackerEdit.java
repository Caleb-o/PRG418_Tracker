package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class TrackerEdit extends Form {
    private static TrackerEdit instance;
    
    private JTextField tfFind;
    private JTextField tfName, tfLikes, tfDisklikes, tfDay, tfMonth;
    private JLabel countLabel;

    private int index = 0;
    private int maxIndex;


    public TrackerEdit(String title) {
        super(title);

        if (instance != null) {
            instance.exit();
        }
        instance = this;

        maxIndex = Tracker.instance.getDataSize();
    }

    public void run() {
        windowFrame = this;
        setup();
        setFields();
    }

    @Override
    protected void setup() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // add GUI objects
        addTitleLabel();

        addLabels();
        addTextFields();
        addButtons();
        addNavigationButtons();

        resetFields();

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
            "Birthday Tracker Editor",
            WINDOW_COLUMN_ONE_X,
            10,
            this,
            layout
        );
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.BLACK);
    }

    private void addLabels() {
        JLabel findLabel = UIComponentLibrary.createJLabel(
            "Find:    ",
            WINDOW_COLUMN_ONE_X,
            WINDOW_SIZE_HEIGHT - 64,
            this,
            layout
        );

        JLabel nameLabel = UIComponentLibrary.createJLabel(
            "New:       ",
            WINDOW_COLUMN_ONE_X,
            30,
            this,
            layout
        );

        JLabel likesLabel = UIComponentLibrary.createJLabel(
            "Likes:     ",
            WINDOW_COLUMN_ONE_X,
            50,
            this,
            layout
        );

        JLabel dislikesLabel = UIComponentLibrary.createJLabel(
            "Dislikes:  ",
            WINDOW_COLUMN_ONE_X,
            70,
            this,
            layout
        );

        JLabel dayLabel = UIComponentLibrary.createJLabel(
            "Day:       ",
            WINDOW_COLUMN_ONE_X,
            90,
            this,
            layout
        );

        JLabel monthLabel = UIComponentLibrary.createJLabel(
            "Month:     ",
            WINDOW_COLUMN_ONE_X,
            110,
            this,
            layout
        );

        countLabel = UIComponentLibrary.createJLabel(
            String.format("%d/%d", index + 1, maxIndex),
            WINDOW_COLUMN_ONE_X + 5 + BUTTON_NAV_SIZE_WIDTH * 2,
            130,
            this,
            layout
        );


        Font currentFont = nameLabel.getFont();

        findLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        findLabel.setForeground(Color.WHITE);
        findLabel.setOpaque(true);
        findLabel.setBackground(Color.BLUE);

        nameLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLUE);

        likesLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        likesLabel.setForeground(Color.WHITE);
        likesLabel.setOpaque(true);
        likesLabel.setBackground(Color.BLUE);

        dislikesLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        dislikesLabel.setForeground(Color.WHITE);
        dislikesLabel.setOpaque(true);
        dislikesLabel.setBackground(Color.BLUE);

        dayLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setOpaque(true);
        dayLabel.setBackground(Color.BLUE);

        monthLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        monthLabel.setForeground(Color.WHITE);
        monthLabel.setOpaque(true);
        monthLabel.setBackground(Color.BLUE);

        countLabel.setFont(new Font(nameLabel.getName(), Font.BOLD, currentFont.getSize()));
        countLabel.setForeground(Color.BLACK);
        countLabel.setOpaque(true);
    }

    private void addTextFields() {
        tfFind = UIComponentLibrary.createJTextField(
            7,
            WINDOW_COLUMN_ONE_X + 40,
            WINDOW_SIZE_HEIGHT - 64,
            this,
            layout
        );


        tfName = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X + 60,
            30,
            this,
            layout
        );

        tfLikes = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X + 60,
            50,
            this,
            layout
        );

        tfDisklikes = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X + 60,
            70,
            this,
            layout
        );

        tfDay = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X + 60,
            90,
            this,
            layout
        );

        tfMonth = UIComponentLibrary.createJTextField(
            10,
            WINDOW_COLUMN_ONE_X + 60,
            110,
            this,
            layout
        );
    }


    private void addButtons() {
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

                fieldsFromPerson(person);
            }
        };

        ActionListener newAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resetFields();
            }
        };

        ActionListener createAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (isMissingFields()) {
                    JOptionPane.showMessageDialog(null, "All fields are required to create an entry", "Creation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PersonData person = personFromFields();
                Tracker.instance.getPersonData().add(person);

                FileIO.create(person);

                JOptionPane.showMessageDialog(null, String.format("\"%s\" created successfully!", person.getName()), "Success!", JOptionPane.INFORMATION_MESSAGE);

                resetFields();

                Tracker.instance.updateTextViewAll(true);

                maxIndex = Tracker.instance.getDataSize();
                index = maxIndex - 1;
                setFields();
            }
        };

        ActionListener updateAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (isMissingFields()) {
                    JOptionPane.showMessageDialog(null, "All fields are required to update an entry", "Update Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updatePerson();

                maxIndex = Tracker.instance.getDataSize();
            }
        };

        ActionListener deleteUpdateAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (tfName.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "A name is required to delete an entry", "Delete Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] options = new String[] { "Yes", "No" };

                int option = JOptionPane.showOptionDialog(
                    null, 
                    "Are you sure you want to delete this entry?", 
                    "Delete Entry", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[1]
                );

                if (option == 0) {
                    removePerson();
                    maxIndex = Tracker.instance.getDataSize();

                    if (index >= maxIndex) {
                        index = 0;
                    }

                    setFields();
                }
            }
        };


        UIComponentLibrary.createJButton(
            "Find",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_TWO_X - 24,
            WINDOW_SIZE_HEIGHT - 64,
            findAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "New",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            32,
            newAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Create",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            64,
            createAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Update",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            96,
            updateAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "Delete",
            BUTTON_SIZE_WIDTH,
            BUTTON_SIZE_HEIGHT,
            WINDOW_COLUMN_THREE_X,
            128,
            deleteUpdateAction,
            this,
            layout
        );
    }


    private void addNavigationButtons() {
        ActionListener rewindAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                index = 0;
                setFields();
            }
        };

        ActionListener backAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (--index < 0) {
                    index = maxIndex - 1;
                }

                setFields();
            }
        };
        
        ActionListener forwardAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (++index >= maxIndex) {
                    index = 0;
                }

                setFields();
            }
        };

        ActionListener fastForwardAction = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                index = maxIndex - 1;
                setFields();
            }
        };


        UIComponentLibrary.createJButton(
            "<<",
            BUTTON_NAV_SIZE_WIDTH,
            BUTTON_NAV_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X,
            150,
            rewindAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            "<",
            BUTTON_NAV_SIZE_WIDTH,
            BUTTON_NAV_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X + (10 + BUTTON_NAV_SIZE_WIDTH),
            150,
            backAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            ">",
            BUTTON_NAV_SIZE_WIDTH,
            BUTTON_NAV_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X + (10 + BUTTON_NAV_SIZE_WIDTH) * 2,
            150,
            forwardAction,
            this,
            layout
        );

        UIComponentLibrary.createJButton(
            ">>",
            BUTTON_NAV_SIZE_WIDTH,
            BUTTON_NAV_SIZE_HEIGHT,
            WINDOW_COLUMN_ONE_X + (10 + BUTTON_NAV_SIZE_WIDTH) * 3,
            150,
            fastForwardAction,
            this,
            layout
        );
    }


    private void setFields() {
        if (maxIndex > 0) {
            countLabel.setText(String.format("%d/%d", index + 1, maxIndex));
            fieldsFromPerson(Tracker.instance.getPersonData().get(index));
        } else {
            countLabel.setText(String.format("%d/%d", 0, maxIndex));
            resetFields();
        }
    }


    private void fieldsFromPerson(final PersonData person) {
        tfName.setText(person.getName());
        tfLikes.setText(person.getLikes());
        tfDisklikes.setText(person.getDislikes());
        tfDay.setText(String.format("%d", person.getFriendDay()));
        tfMonth.setText(String.format("%d", person.getFriendMonth()));
    }

    
    private PersonData personFromFields() {
        return new PersonData(
            tfName.getText(),
            tfLikes.getText(),
            tfDisklikes.getText(),
            Integer.parseInt(tfDay.getText()),
            Integer.parseInt(tfMonth.getText())
        );
    }


    private boolean isMissingFields() {
        return tfName.getText().length() == 0 || tfLikes.getText().length() == 0 || tfDisklikes.getText().length() == 0 || tfDay.getText().length() == 0 || tfMonth.getText().length() == 0;
    }


    private void resetFields() {
        tfName.setText("");
        tfLikes.setText("");
        tfDisklikes.setText("");
        tfDay.setText(String.format("%d", LocalDate.now().getDayOfMonth()));
        tfMonth.setText(String.format("%d", LocalDate.now().getMonthValue()));
    }


    private void updatePerson() {
        String personName = tfName.getText();
        PersonData person = null;

        for(PersonData per : Tracker.instance.getPersonData()) {
            System.out.println(per.getName() + " == " + personName + " = " + (per.getName() == personName));

            if (per.getName().equals(personName)) {
                person = per;
                break;
            }
        }

        // Person not found, add a new one to the list
        if (person == null) {
            String[] options = new String[] { "Yes", "No" };

            int option = JOptionPane.showOptionDialog(
                null, 
                String.format("\"%s\" does not exist. Would you like to add it?", tfName.getText()), 
                "Update Entry", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]
            );

            if (option == 0) {
                Tracker.instance.getPersonData().add(personFromFields());
            }
        } else {
            // Update existing
            person.setLikes(tfLikes.getText());
            person.setDislikes(tfDisklikes.getText());
            person.setFriendDay(Integer.parseInt(tfDay.getText()));
            person.setFriendMonth(Integer.parseInt(tfMonth.getText()));
        }

        // Update on disk
        FileIO.update();
        Tracker.instance.updateTextViewAll(true);
    }


    private void removePerson() {
        String personName = tfName.getText();

        for(PersonData person : Tracker.instance.getPersonData()) {
            if (person.getName().equals(personName)) {
                // Person has been found
                Tracker.instance.getPersonData().remove(person);
                break;
            }
        }

        // Update on disk
        FileIO.update();
        Tracker.instance.updateTextViewAll(true);
    }
}