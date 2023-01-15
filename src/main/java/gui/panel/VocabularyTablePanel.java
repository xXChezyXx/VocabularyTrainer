package main.java.gui.panel;

import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;

public class VocabularyTablePanel {

    public static JScrollPane VocabularyTable(VocabPackage vocabPackage){

        String[][] vocabularys = new String[vocabPackage.getVocablist().getLength()][2];
        String[] header = {"Frage","Antwort"};

        for (int i = 0;i < vocabPackage.getVocablist().getLength();i++){
            Vocabulary vocabulary = ((Vocabulary) vocabPackage.getVocablist().getItem(i));
            vocabularys[i][0] = vocabulary.getKey();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html>");
            for (int j = 0; j < vocabulary.getValue().getLength();j++){
                stringBuilder.append((String) vocabulary.getValue().getItem(j));
                if(j<vocabulary.getValue().getLength()-1) stringBuilder.append("<br>");
            }
            stringBuilder.append("</html>");
            vocabularys[i][1] = stringBuilder.toString();
        }

        JTable table = new JTable(vocabularys,header);
        table.setBounds(0,0,340,660);
        table.setRowHeight(58);
        table.setFillsViewportHeight(true);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(730,50,340,660);
        jScrollPane.setName("SCROLL");
        return jScrollPane;
    }

}
