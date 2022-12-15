package gui.statistik;

import vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

public class Karteibox extends JPanel{

    public Karteibox(VocabPackage vocabPackage){ //848 Scrollbar 20 = 828 / 3 = 276
        setBounds(10,10,276,100);
        setBackground(Color.white);
        setLayout(null);
        JButton open = new JButton("Öffnen");
        open.setBounds(0,70,138,30);
        open.setFocusPainted(false);
        JButton delete = new JButton("Löschen");
        delete.setBounds(138,70,138,30);
        JLabel name = new JLabel(vocabPackage.getName(),SwingConstants.CENTER);
        name.setBounds(37,20,200,30);
        add(name);
        add(open);
        add(delete);
    }

}
