package main.java.gui;

import main.java.main.Main;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class CreateVocabPackageGUI extends JFrame{

    private final JLabel error;

    public CreateVocabPackageGUI(String title){
        super(title);

        //GUI
        setResizable(false);
        setSize(300,180);
        setLocationRelativeTo(null);

        error = new JLabel("<html>Bitte wähle ein Name mit maximal 16<br>und mindestens 1 Buchstaben aus!</html>",SwingConstants.CENTER);
        error.setBounds(10,5,280,60);

        JTextField newvocabpacket = new JTextField();
        newvocabpacket.setBounds(50,65,200,30);
        newvocabpacket.addActionListener(e -> createNewVocabList(e.getActionCommand()));

        JButton confirm = new JButton("Karteikarte erstellen");
        confirm.setBounds(50,100,200,30);
        confirm.addActionListener(e -> createNewVocabList(newvocabpacket.getText()));
        confirm.setFocusPainted(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Main.mainPage.setEnabled(true);
                setVisible(false);
                dispose();
                Main.mainPage.filterVocabulary();
                Main.mainPage.loadVocabPackages();
                Main.mainPage.reload();
            }
        });

        //Container
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(newvocabpacket);
        pane.add(confirm);
        pane.add(error);
    }

    private void createNewVocabList(String newname){
        if(newname.toCharArray().length > 16 || newname.toCharArray().length == 0){
            error.setForeground(Color.red);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    error.setForeground(Color.black);
                }
            },1000);
            return;
        }
        VocabPackage newvocabpackage = new VocabPackage(new DynArray(),newname);
        Main.vocabpackagelist.append(newvocabpackage);
        Main.mainPage.setEnabled(true);
        setVisible(false);
        dispose();
        Main.mainPage.filterVocabulary();
        Main.mainPage.loadVocabPackages();
        Main.mainPage.reload();
    }
}
