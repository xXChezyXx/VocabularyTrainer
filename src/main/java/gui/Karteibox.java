package gui;

import main.Main;
import utils.DynArray;
import vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

public class Karteibox extends JPanel{

    //848 / 3 = 276 Breite
    //530 / 5 = 106 Höhe
    public Karteibox(VocabPackage vocabPackage){
        setBackground(Color.white);
        setLayout(null);

        JButton open = new JButton("Öffnen");
        open.setBounds(0,76,141,30);
        open.setFocusPainted(false);
        open.addActionListener(e -> {

        });

        JButton delete = new JButton("Löschen");
        delete.setFocusPainted(false);
        delete.setBounds(141,76,142,30);
        delete.addActionListener(e -> {
            for (int i = 0; i < Main.vocabpackagelist.getLength(); i++) {
                if (Main.vocabpackagelist.getItem(i) == vocabPackage){
                    Main.vocabpackagelist.delete(i);
                    Main.mainPage.filterVocabulary();
                    Main.mainPage.loadVocabPackages();
                    Main.mainPage.reload();
                    break;
                }
            }
        });

        JLabel name = new JLabel(vocabPackage.getName(),SwingConstants.CENTER);
        name.setBounds(40,23,200,30);
        add(name);
        add(open);
        add(delete);
    }

}
