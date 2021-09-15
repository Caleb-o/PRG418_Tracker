package com.coolcompany.tracker;

import javax.swing.*;
import java.awt.*;

public abstract class Form extends JFrame {

    protected static final int BUTTON_SIZE_WIDTH = 120;
    protected static final int BUTTON_SIZE_HEIGHT = 20;

    protected static final int WINDOW_SIZE_WIDTH = 450;
    protected static final int WINDOW_SIZE_HEIGHT = 300;

    protected static final int WINDOW_COLUMN_ONE_X = 10;
    protected static final int WINDOW_COLUMN_TWO_X = 10;
    protected static final int WINDOW_COLUMN_THREE_X = 300;

    final SpringLayout layout = new SpringLayout();
    protected Frame windowFrame;
    protected boolean isChild;

    public Form(boolean isChild) { 
        this.isChild = isChild;
    }

    public abstract void run();
    protected abstract void setup();
}