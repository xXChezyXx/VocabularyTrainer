package main.java.gui.panel;

import main.java.main.Main;
import main.java.vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Ein Kartikastenmenü, das zum Hauptmenü gehört.
 */
public class KarteiboxPanel extends JPanel{

    //848 / 3 = 276 Breite
    //530 / 5 = 106 Höhe
    private final JButton open;

    /**
     * Das Menü besitzt 2 Knöpfe. Einer davon ist zum Löschen da.
     * Der Andere kann zum Öffnen oder zum Lernen benutzt werden.
     * Das kommt auf den Wert des Prüfungsmodus an.
     */
    public KarteiboxPanel(VocabPackage vocabPackage){
        setBackground(Color.white);
        setLayout(null);

        open = new JButton("Öffnen");
        open.setBounds(0,76,141,30);
        open.setFocusPainted(false);
        open.addActionListener(e -> {
            if (open.getText().equalsIgnoreCase("Öffnen")) {
                VocabPanel vocabpanel = new VocabPanel(vocabPackage);
                Main.mainframe.setVocabpanel(vocabpanel);
                Main.mainframe.setPanel(1);
                Main.mainframe.reload();
                return;
            }
            LearnPanel learnpanel = new LearnPanel(vocabPackage);
            Main.mainframe.setLearnpanel(learnpanel);
            Main.mainframe.setPanel(2);
            Main.mainframe.reload();
        });

        JButton delete = new JButton("Löschen");
        delete.setFocusPainted(false);
        delete.setBounds(141,76,142,30);
        delete.addActionListener(e -> {
            for (int i = 0; i < Main.vocabpackagelist.getLength(); i++) {
                if (Main.vocabpackagelist.getItem(i) == vocabPackage){
                    Main.vocabpackagelist.delete(i);
                    Main.mainframe.getMenupanel().filterVocabulary();
                    Main.mainframe.getMenupanel().loadVocabPackages();
                    Main.mainframe.reload();
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

    public JButton getOpen() {
        return open;
    }
}
