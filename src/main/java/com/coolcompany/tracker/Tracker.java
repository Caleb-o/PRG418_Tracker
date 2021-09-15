package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tracker extends Form {
    private FileIO fileIO = new FileIO();

    private JButton btnFind, btnNew, btnSave, btnDelete;
    private JButton btnBinarySearch, btnBirthdayInMonth;
    private JButton btnForward, btnFastForward, btnBack, btnRewind;
    private JTextField textFieldFind;
    private JTextArea largeTextArea;

    public Tracker(boolean isChild) {
        super(isChild);
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

        // add GUI objects
        addBirthdayTrackerTitleLabel();

        addFindLabel();
        addFindTextField();
        addFindButton();
        addLargeTextArea();

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

    private void addBirthdayTrackerTitleLabel() {
        JLabel label = UIComponentLibrary.createJLabel(
                "Birthday Tracker",
                WINDOW_COLUMN_ONE_X,
                10,
                this,
                layout);
        label.setFont(new Font(label.getName(), Font.BOLD, 15));
        label.setForeground(Color.BLACK);
    }

    private void addFindLabel() {
        JLabel label = UIComponentLibrary.createJLabel(
                "Find:    ",
                WINDOW_COLUMN_THREE_X,
                10,
                this,
                layout);

        Font currentFont = label.getFont();
        label.setFont(new Font(label.getName(), Font.BOLD, currentFont.getSize()));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
    }

    private void addFindTextField() {

        textFieldFind = UIComponentLibrary.createJTextField(
                7,
                WINDOW_COLUMN_THREE_X + 40,
                10,
                this,
                layout);
    }

    private void addLargeTextArea() {

        largeTextArea = UIComponentLibrary.createJTextArea(
                7, 30,
                WINDOW_COLUMN_ONE_X + 40,
                60,
                this,
                layout);

        String valueOfSearchString = "5 May";
        String textForLargeTextArea = "Birth Days for Months of: " + valueOfSearchString + "\n";
        textForLargeTextArea = textForLargeTextArea + "\n";
        textForLargeTextArea = textForLargeTextArea + "Person\tLikes\tDislikes\tDay\tMonth\n";


        largeTextArea.setText(textForLargeTextArea);

    }


    private void addFindButton() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                java.util.List<PersonDTO> persons = fileIO.read(textFieldFind.getText());

                if (persons != null && !persons.isEmpty()) {

                    for (PersonDTO person : persons) {
                        JOptionPane.showMessageDialog(windowFrame, person.getName() + " " + person.getLikes());
                    }

                }

                new Tracker(true).run();
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
                layout);
    }



}
