package main.java.gui.panel;

import main.java.gui.frame.CreateVocabularyFrame;
import main.java.main.Main;
import main.java.vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

public class VocabPanel extends JPanel {

    private VocabPackage vocabpackage;
    private JScrollPane tablepanel;
    private JPanel singlevocabpanel;
    private JButton homebutton;
    private JButton newvocabbutton;
    private JButton backbutton;
    private JButton forwardbutton;
    private JButton deletebutton;
    private JButton searchbutton;
    private JTextField searchtext;

    public VocabPanel(VocabPackage vocabpackage){
        this.vocabpackage = vocabpackage;

        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        singlevocabpanel = new JPanel(); //TODO Einfach eine Klassen machen, geht somit einfacher
        singlevocabpanel.setBounds(10,10,700,620);
        singlevocabpanel.setBackground(Color.black);

        tablepanel = VocabularyTablePanel.VocabularyTable(this.vocabpackage);

        newvocabbutton = new JButton("Vokabel erstellen");
        newvocabbutton.setBounds(730,10,165,30);
        newvocabbutton.setFocusPainted(false);
        newvocabbutton.addActionListener(e -> {
            CreateVocabularyFrame createVocabularyFrame = new CreateVocabularyFrame("Neue Vokabel erstellen");
            createVocabularyFrame.setVisible(true);
            Main.mainframe.setEnabled(false);
        });

        homebutton = new JButton("Zurück zum Menu");
        homebutton.setBounds(905,10,165,30);
        homebutton.setFocusPainted(false);
        homebutton.addActionListener(e -> {
            Main.mainframe.setPanel(0);
            Main.mainframe.reload();
        });

        backbutton = new JButton("Voherige Vokabel");
        backbutton.setBounds(10,640,224,30);
        backbutton.setFocusPainted(false);
        backbutton.addActionListener(e -> {

        });

        deletebutton = new JButton("Vokabel löschen");
        deletebutton.setBounds(248,640,224,30);
        deletebutton.setFocusPainted(false);
        deletebutton.addActionListener(e -> {

        });

        forwardbutton = new JButton("Nächste Vokabel");
        forwardbutton.setBounds(486,640,224,30);
        forwardbutton.setFocusPainted(false);
        forwardbutton.addActionListener(e -> {

        });

        searchbutton = new JButton("Vokabel suchen");
        searchbutton.setBounds(572,680,138,30);
        searchbutton.setFocusPainted(false);
        searchbutton.addActionListener(e -> {

        });

        searchtext = new JTextField();
        searchtext.setBounds(10,680,552,30);
        searchtext.addActionListener(e -> {

        });

        add(homebutton);
        add(tablepanel);
        add(singlevocabpanel);
        add(backbutton);
        add(newvocabbutton);
        add(forwardbutton);
        add(deletebutton);
        add(searchbutton);
        add(searchtext);
    }

    public VocabPackage getVocabpackage() {
        return vocabpackage;
    }

    public void setTablepanel(JScrollPane tablepanel) {
        for (int i = 0;i < getComponents().length;i++){
            try {
                if (getComponent(i).getName().equalsIgnoreCase("SCROLL")) {
                    remove(i);
                    break;
                }
            }catch (NullPointerException ignored){}
        }
        add(tablepanel);
    }
}