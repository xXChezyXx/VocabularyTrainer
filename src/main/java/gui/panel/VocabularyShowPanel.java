package main.java.gui.panel;

import main.java.main.Main;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;

public class VocabularyShowPanel extends JPanel {

    JPanel vocabkeypanel;
    JPanel vocabvaluepanel;
    JTextField vocabkey;
    JTextField vocabvalue;

    public VocabularyShowPanel(Vocabulary vocabulary){
        setBounds(10,10,700,620);
        setBackground(Color.black);
        setLayout(null);

        vocabkey = new JTextField("",50);
        vocabvalue = new JTextField("",50);

        vocabkeypanel = new JPanel();
        vocabkeypanel.setBounds(50,50,600,235);
        vocabkeypanel.setBackground(Color.white);
        vocabkeypanel.setLayout(new GridBagLayout());
        vocabkey.setText(vocabulary.getKey());
        vocabkey.setBorder(BorderFactory.createLineBorder(Color.black));
        vocabkeypanel.add(vocabkey);

        vocabvaluepanel = new JPanel();
        vocabvaluepanel.setBounds(50,335,600,235);
        vocabvaluepanel.setBackground(Color.white);
        vocabvaluepanel.setLayout(new GridBagLayout());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < vocabulary.getValue().getLength();i++){
            stringBuilder.append(", ").append(vocabulary.getValue().getItem(i));
        }
        vocabvalue.setText(stringBuilder.substring(2));
        vocabvalue.setBorder(BorderFactory.createLineBorder(Color.black));
        vocabvaluepanel.add(vocabvalue);

        vocabkey.addActionListener(e -> {
            //TODO Einstellen, dass man die Fragen und Antworten umbenennen kann
        });

        vocabvalue.addActionListener(e -> {

        });

        add(vocabkeypanel);
        add(vocabvaluepanel);
    }

    public JTextField getVocabkey() {
        return vocabkey;
    }

    public JTextField getVocabvalue() {
        return vocabvalue;
    }
}
