package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIComponentLibrary
{
    public static JButton createJButton(String name,
                                        int sizeX,
                                        int sizeY,
                                        int posX,
                                        int posY,
                                        ActionListener listener,
                                        Frame frame,
                                        SpringLayout layout)
    {
        JButton button = new JButton(name); //Creates button and sets text
        button.addActionListener(listener); //Adds action listener to register click events
        button.setPreferredSize(new Dimension(sizeX,sizeY)); //Sets button size

        layout.putConstraint(SpringLayout.WEST, button, posX, SpringLayout.WEST, frame); //Sets button's X Coordinates
        layout.putConstraint(SpringLayout.NORTH, button, posY, SpringLayout.NORTH, frame); //Sets button's Y Coordinates

        frame.add(button); //Adds button to frame
        return button; //Returns completed button to caller.
    }
    
    /*
        textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setEditable(false);
    */
    public static JTextField createJTextField(int size,
                                               int posX,
                                               int posY,
                                               Frame frame,
                                               SpringLayout layout)
    {
        JTextField textField = new JTextField(size); //Creates JTextField and sets size

        layout.putConstraint(SpringLayout.WEST, textField, posX, SpringLayout.WEST, frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH, textField, posY, SpringLayout.NORTH, frame);//Sets text field's Y Coordinates

        frame.add(textField); //Adds text field to frame
        return textField; //Returns completed text field to caller
    }

    public static JTextArea createJTextArea(int rows, int columns,
                                              int posX,
                                              int posY,
                                              Frame frame,
                                              SpringLayout layout)
    {
        JTextArea textArea = new JTextArea(rows, columns); //Creates JTextField and sets size
        textArea.setEditable(false);

        layout.putConstraint(SpringLayout.WEST, textArea, posX, SpringLayout.WEST, frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH, textArea, posY, SpringLayout.NORTH, frame);//Sets text field's Y Coordinates

        frame.add(textArea); //Adds text field to frame
        return textArea; //Returns completed text field to caller
    }

    public static JCheckBox createJCheckBox(String text,
                                            int posX,
                                            int posY,
                                            boolean selected,
                                            ActionListener listener,
                                            Frame frame,
                                            SpringLayout layout)
    {
        JCheckBox checkBox = new JCheckBox(text, selected);
        checkBox.addActionListener(listener);

        layout.putConstraint(SpringLayout.WEST, checkBox, posX, SpringLayout.WEST, frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH, checkBox, posY, SpringLayout.NORTH, frame);//Sets text field's Y Coordinates

        frame.add(checkBox);
        return checkBox;
    }


    public static JLabel createJLabel(String text,
                                       int posX,
                                       int posY,
                                       Frame frame,
                                       SpringLayout layout)
    {
        JLabel label = new JLabel(text); //Creates label and sets text

        layout.putConstraint(SpringLayout.WEST, label, posX, SpringLayout.WEST, frame);//Sets labels's X Coordinates
        layout.putConstraint(SpringLayout.NORTH, label, posY, SpringLayout.NORTH, frame);//Sets label's Y Coordinates

        frame.add(label); // Adds label to frame
        return label; //Returns completed label to caller.
    }
}

