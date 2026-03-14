package com.devops;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CourseManagementApplet extends Applet implements ActionListener {

    Label l1, l2, l3, result;
    TextField t1, t2, t3;
    Button addBtn, clearBtn;

    public void init() {

        l1 = new Label("Course ID:");
        l2 = new Label("Course Name:");
        l3 = new Label("Instructor:");

        t1 = new TextField(15);
        t2 = new TextField(15);
        t3 = new TextField(15);

        addBtn = new Button("Add Course");
        clearBtn = new Button("Clear");

        result = new Label("");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(addBtn);
        add(clearBtn);
        add(result);

        addBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {

            String id = t1.getText();
            String name = t2.getText();
            String instructor = t3.getText();

            result.setText("Course Added: " + name + " (Instructor: " + instructor + ")");
        }

        if (e.getSource() == clearBtn) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            result.setText("");
        }
    }
}