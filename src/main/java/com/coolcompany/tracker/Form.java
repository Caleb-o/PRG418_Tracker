package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public abstract class Form extends JFrame {

    protected static final int BUTTON_SIZE_WIDTH = 96;
    protected static final int BUTTON_SIZE_HEIGHT = 20;

    protected static final int BUTTON_NAV_SIZE_WIDTH = 48;
    protected static final int BUTTON_NAV_SIZE_HEIGHT = 20;

    protected static final int WINDOW_SIZE_WIDTH = 450;
    protected static final int WINDOW_SIZE_HEIGHT = 300;

    protected static final int WINDOW_COLUMN_ONE_X = 10;
    protected static final int WINDOW_COLUMN_TWO_X = 150;
    protected static final int WINDOW_COLUMN_THREE_X = 300;

    final SpringLayout layout = new SpringLayout();
    protected Frame windowFrame;
    protected boolean isChild;

    /**
     * Simple Constructor that sets Title to "Window"
     */
    public Form() {
        this("Window");
    }

    /**
     * Constructor that gives the form a title
     * @param title
     */
    public Form(String title) {
        this.setTitle(title);
        windowFrame = this;
    }

    /**
     * Send an event to the Window to close the form
     */
    public void exit() {
        this.dispatchEvent(new WindowEvent(windowFrame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Abstract run function to override
     */
    public abstract void run();

    /**
     * Abstract setup function to override
     */
    protected abstract void setup();
}