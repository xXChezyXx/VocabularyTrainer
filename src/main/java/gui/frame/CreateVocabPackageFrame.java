package main.java.gui.frame;

import main.java.main.Main;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fenster, wo ein neuer Karteikasten erstellt wird.
 */
public class CreateVocabPackageFrame extends JFrame{

    /**
     * Fehlermeldung, falls der Name ungültig ist.
     */
    private final JLabel error;

    public CreateVocabPackageFrame(String title){
        super(title);

        //GUI
        setResizable(false);
        setSize(300,180);
        setLocationRelativeTo(null);

        error = new JLabel("<html>Bitte wähle ein Name mit maximal 25<br>und mindestens 1 Buchstaben aus!</html>",SwingConstants.CENTER);
        error.setBounds(10,5,280,60);

        JTextField newvocabpackage = new JTextField();
        newvocabpackage.setBounds(50,65,200,30);
        newvocabpackage.addActionListener(e -> createNewVocabList(e.getActionCommand()));

        JButton confirm = new JButton("Karteikasten erstellen");
        confirm.setBounds(50,100,200,30);
        confirm.addActionListener(e -> createNewVocabList(newvocabpackage.getText()));
        confirm.setFocusPainted(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Main.mainframe.setEnabled(true);
                setVisible(false);
                dispose();
                Main.mainframe.reload();
            }
        });

        //Container
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(newvocabpackage);
        pane.add(confirm);
        pane.add(error);
    }

    /**
     * Falls der Knopf betätigt wurde, wird überprüft, ob der name passend ist.
     * Passend definiert ist in diesem Fall: Nicht länger als 25 Zeichen und mindestens
     * ein Zeichen. Falls die Bedingung übereinstimmt, wird ein neuer Karteikasten erstellt.
     */
    private void createNewVocabList(String newname){
        if(newname.toCharArray().length > 25 || newname.toCharArray().length == 0){
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
        Main.mainframe.setEnabled(true);
        setVisible(false);
        dispose();
        Main.mainframe.getMenupanel().filterVocabulary();
        Main.mainframe.getMenupanel().loadVocabPackages();
        Main.mainframe.reload();
    }
}
