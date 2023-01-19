package main.java.gui.frame;

import main.java.gui.panel.VocabularyTablePanel;
import main.java.main.Main;
import main.java.utils.DynArray;
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
        setSize(300,220);
        setLocationRelativeTo(null);

        error = new JLabel("<html>Bitte wähle eine Vokabel mit maximal 25<br>und mindestens 1 Buchstaben aus<br>mit Frage und Antwort!</html>",SwingConstants.CENTER);
        error.setBounds(10,5,280,60);

        JTextField newvockey = new JTextField();
        newvockey.setBounds(50,75,200,30);

        JTextField newvocvalue = new JTextField();
        newvocvalue.setBounds(50,110,200,30);


        JButton confirm = new JButton("Karteikasten erstellen");
        confirm.setBounds(50,145,200,30);
        confirm.addActionListener(e -> createNewVocabulary(newvockey.getText(),newvocvalue.getText()));
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
        pane.add(newvockey);
        pane.add(newvocvalue);
        pane.add(confirm);
        pane.add(error);
    }

    private void createNewVocabulary(String newname,String newvalue){
        if((newname.toCharArray().length > 25 || newname.toCharArray().length == 0) || (newvalue.toCharArray().length > 25 || newvalue.toCharArray().length == 0)){
            error.setForeground(Color.red);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    error.setForeground(Color.black);
                }
            },1000);
            return;
        }
        Vocabulary vocabulary;
        if(newvalue.contains(",")){
            DynArray values = new DynArray();
            String[] valuestr = newvalue.split(",");
            for (String value:valuestr){
                if (value.charAt(0) == ' ') value = value.substring(1);
                values.append(value);
            }
            vocabulary = new Vocabulary(newname,values);
        }else{
            vocabulary = new Vocabulary(newname,newvalue);
        }
        Main.mainframe.getVocabpanel().getVocabpackage().addVocabulary(vocabulary);
        Main.mainframe.setEnabled(true);
        setVisible(false);
        dispose();
        int last = Main.mainframe.getVocabpanel().getVocabpackage().getVocablist().getLength()-1;
        Main.mainframe.getVocabpanel().getForwardbutton().setEnabled(false);
        Main.mainframe.getVocabpanel().setTablePanel(VocabularyTablePanel.VocabularyTable(Main.mainframe.getVocabpanel().getVocabpackage(),last));
        Main.mainframe.getVocabpanel().getSinglevocabpanel().getVocabkey().setText(((Vocabulary) Main.mainframe.getVocabpanel().getVocabpackage().getVocablist().getItem(last)).getKey());
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0;j < ((Vocabulary) Main.mainframe.getVocabpanel().getVocabpackage().getVocablist().getItem(last)).getValue().getLength();j++){
            stringBuilder.append(", ").append(((Vocabulary) Main.mainframe.getVocabpanel().getVocabpackage().getVocablist().getItem(last)).getValue().getItem(j));
        }
        Main.mainframe.getVocabpanel().getSinglevocabpanel().getVocabvalue().setText(stringBuilder.substring(2));
        Main.mainframe.reload();
    }

}
