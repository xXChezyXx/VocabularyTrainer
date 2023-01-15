package main.java.gui.frame;

import main.java.main.Main;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class CreateVocabularyFrame extends JFrame {

    private final JLabel error;

    public CreateVocabularyFrame(String title){
        super(title);

        //GUI
        setResizable(false);
        setSize(300,180);
        setLocationRelativeTo(null);

        error = new JLabel("<html>Bitte wähle eine Vokabel mit maximal 16<br>und mindestens 1 Buchstaben aus!</html>",SwingConstants.CENTER);
        error.setBounds(10,5,280,60);

        JTextField newvocabulary = new JTextField();
        newvocabulary.setBounds(50,65,200,30);
        newvocabulary.addActionListener(e -> createNewVocabulary(e.getActionCommand()));

        JButton confirm = new JButton("Karteikarte erstellen");
        confirm.setBounds(50,100,200,30);
        confirm.addActionListener(e -> createNewVocabulary(newvocabulary.getText()));
        confirm.setFocusPainted(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Main.mainframe.setEnabled(true);
                setVisible(false);
                dispose();
            }
        });

        //Container
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(newvocabulary);
        pane.add(confirm);
        pane.add(error);
    }

    private void createNewVocabulary(String newname){
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
        Vocabulary vocabulary = new Vocabulary(newname,"");
        Main.mainframe.getVocabpanel().getVocabpackage().addVocabulary(vocabulary);
        Main.mainframe.setEnabled(true);
        setVisible(false);
        dispose();
        Main.mainframe.reload();
    }

}
