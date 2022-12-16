package gui.statistik;

import vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

public class Karteibox extends JPanel{

    //848 / 3 = 276 Breite
    //530 / 5 = 106 Höhe
    public Karteibox(VocabPackage vocabPackage){
        setBounds(10,10,282,106);
        setBackground(Color.white);
        setLayout(null);
        JButton open = new JButton("Öffnen");
        open.setBounds(0,76,141,30);
        open.setFocusPainted(false);
        JButton delete = new JButton("Löschen");
        delete.setBounds(141,76,142,30);
        JLabel name = new JLabel(vocabPackage.getName(),SwingConstants.CENTER);
        name.setBounds(40,23,200,30);
        add(name);
        add(open);
        add(delete);
    }

}
