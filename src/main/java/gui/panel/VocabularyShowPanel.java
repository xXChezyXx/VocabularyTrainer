package main.java.gui.panel;

import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;

public class VocabularyShowPanel extends JPanel {

    JPanel vocabkeypanel;
    JPanel vocabvaluepanel;
    JLabel vocabkey;
    JLabel vocabvalue;

    public VocabularyShowPanel(Vocabulary vocabulary){
        setBounds(10,10,700,620);
        setBackground(Color.black);
        setLayout(null);

        vocabkey = new JLabel();
        vocabvalue = new JLabel();

        vocabkeypanel = new JPanel();
        vocabkeypanel.setBounds(50,50,600,235);
        vocabkeypanel.setBackground(Color.white);
        vocabkeypanel.setLayout(new GridBagLayout());
        vocabkey.setText(vocabulary.getKey());
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
        vocabvaluepanel.add(vocabvalue);

        add(vocabkeypanel);
        add(vocabvaluepanel);
    }

    public JLabel getVocabkey() {
        return vocabkey;
    }

    public JLabel getVocabvalue() {
        return vocabvalue;
    }
}
