package main.java.gui.panel;

import main.java.main.Main;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JButton homebutton;
    private JLabel rightlabel;
    private JLabel wronglabel;
    private JLabel beststreaklabel;
    private JPanel rightbar;
    private JPanel wrongbar;
    private JPanel beststreakbar;

    /**
     * Beim Ergebnis werden die Anzahl der richtig erratende und falsch erratende Vokabeln in Form eines
     * Seitendiagramms dargstellt. Auch wird der maximale Streak angezeigt.
     */
    public ResultPanel(int beststreak,int right, int wrong){

        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        homebutton = new JButton("Zurück zum Menu");
        homebutton.setBounds(905,10,165,30);
        homebutton.setFocusPainted(false);
        homebutton.addActionListener(e -> {
            Main.mainframe.setPanel(0);
            Main.mainframe.reload();
        });

        rightlabel = new JLabel("Richtig", SwingConstants.RIGHT);
        rightlabel.setBounds(150,300,100,30);

        wronglabel = new JLabel("Falsch", SwingConstants.RIGHT);
        wronglabel.setBounds(150,350,100,30);

        beststreaklabel = new JLabel("Beste Streak", SwingConstants.RIGHT);
        beststreaklabel.setBounds(150,400,100,30);

        rightbar = new JPanel();
        rightbar.setBackground(Color.green);
        rightbar.setBounds(275,300,40*right,30);
        JLabel rightcount = new JLabel(""+right);
        rightcount.setBounds(300+40*right,300,50,30);

        wrongbar = new JPanel();
        wrongbar.setBackground(Color.red);
        wrongbar.setBounds(275,350,40*wrong,30);
        JLabel wrongcount = new JLabel(""+wrong);
        wrongcount.setBounds(300+40*wrong,350,50,30);

        beststreakbar = new JPanel();
        beststreakbar.setBackground(Color.orange);
        beststreakbar.setBounds(275,400,40*beststreak,30);
        JLabel streakcount = new JLabel(""+beststreak);
        streakcount.setBounds(300+40*beststreak,400,50,30);

        add(homebutton);
        add(rightlabel);
        add(wronglabel);
        add(beststreaklabel);
        add(rightbar);
        add(wrongbar);
        add(beststreakbar);
        add(rightcount);
        add(wrongcount);
        add(streakcount);
    }
}
