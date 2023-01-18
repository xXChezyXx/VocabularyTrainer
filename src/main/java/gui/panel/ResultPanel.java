package main.java.gui.panel;

import main.java.main.Main;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JButton homebutton;

    public ResultPanel(int beststreak,int right, int wrong, int hard){

        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        homebutton = new JButton("Zurück zum Menu");
        homebutton.setBounds(905,10,165,30);
        homebutton.setFocusPainted(false);
        homebutton.addActionListener(e -> {
            Main.mainframe.setPanel(0);
            Main.mainframe.reload();
        });

        add(homebutton);
    }
}
