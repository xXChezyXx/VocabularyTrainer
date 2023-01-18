package main.java.gui.panel;

import main.java.main.Main;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VocabularyTablePanel {

    public static JScrollPane VocabularyTable(VocabPackage vocabPackage,int row){

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

        JTable table = new JTable();
        table.setBounds(0,0,340,660);
        table.setRowHeight(58);
        table.setFillsViewportHeight(true);
        DefaultTableModel tableModel = new DefaultTableModel(vocabularys,header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(tableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(row >= 0) table.getSelectionModel().setSelectionInterval(row, row);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    Main.mainframe.getVocabpanel().getSinglevocabpanel().getVocabkey().setText((String) table.getValueAt(row,0));
                    Main.mainframe.getVocabpanel().getSinglevocabpanel().getVocabvalue().setText(((String) table.getValueAt(row,1)).replaceAll("<html>","").replaceAll("</html>","").replaceAll("<br>",", "));
                    Main.mainframe.getVocabpanel().getBackbutton().setEnabled(true);
                    Main.mainframe.getVocabpanel().getForwardbutton().setEnabled(true);
                    if(row == 0){
                        Main.mainframe.getVocabpanel().getBackbutton().setEnabled(false);
                    }
                    if(row == Main.mainframe.getVocabpanel().getVocabpackage().getVocablist().getLength()-1){
                        Main.mainframe.getVocabpanel().getForwardbutton().setEnabled(false);
                    }
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(730,50,340,660);
        jScrollPane.setName("SCROLL");
        return jScrollPane;
    }

}
