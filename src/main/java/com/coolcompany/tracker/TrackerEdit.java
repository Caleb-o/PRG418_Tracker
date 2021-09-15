package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TrackerEdit extends Form {
    private JButton btnFind, btnNew, btnSave, btnDelete;
    private JButton btnBinarySearch, btnBirthdayInMonth;
    private JButton btnForward, btnFastForward, btnBack, btnRewind;

    private List<PersonData> pdata;


    public TrackerEdit(String title, List<PersonData> personData) {
        super(true, title);
        this.pdata = personData;
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
        // textFieldFind = UIComponentLibrary.createJTextField(
        //     7,
        //     WINDOW_COLUMN_THREE_X + 40,
        //     10,
        //     this,
        //     layout
        // );
    }


    private void addButtons() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                java.util.List<PersonData> persons = FileIO.read();

                if (persons != null && !persons.isEmpty()) {
                    for (PersonData person : persons) {
                        JOptionPane.showMessageDialog(windowFrame, person.getName() + " " + person.getLikes());
                    }
                }

                // TODO: Add Edit window here - New, Save, Delete
                // new Tracker(true, "Other Window").run();
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
    }
}