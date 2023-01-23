package main.java.gui.panel;

import main.java.gui.frame.CreateVocabularyFrame;
import main.java.main.Main;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;

public class VocabPanel extends JPanel {

    private final VocabPackage vocabpackage;
    private final JScrollPane tablepanel;
    private final VocabularyShowPanel singlevocabpanel;
    private final JButton homebutton;
    private final JButton newvocabbutton;
    private JButton backbutton;
    private JButton forwardbutton;
    private final JButton deletebutton;

    /**
     * Das Vokabelmenü besitzt eine Tabelle mit allen Vokabeln, einen Zurückknopf ins Hauptmenü, einen Knopf,
     * um neue Vokabeln zu erstellen und einen Knopf zum Löschen einer Vokabel.
     */
    public VocabPanel(VocabPackage vocabpackage){
        this.vocabpackage = vocabpackage;

        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        singlevocabpanel = new VocabularyShowPanel(vocabpackage.getVocablist().isEmpty()?new Vocabulary("",""):(Vocabulary) vocabpackage.getVocablist().getItem(0));

        tablepanel = VocabularyTablePanel.VocabularyTable(this.vocabpackage,0);

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
        backbutton.setEnabled(false);
        backbutton.addActionListener(e -> {
            if(!getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase("")) {
                forwardbutton.setEnabled(true);
                for (int i = 0;i<this.vocabpackage.getVocablist().getLength();i++){
                    if (getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase(((Vocabulary) this.vocabpackage.getVocablist().getItem(i)).getKey())){
                        try{
                            getSinglevocabpanel().getVocabkey().setText(((Vocabulary) this.vocabpackage.getVocablist().getItem(i-1)).getKey());
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0;j < ((Vocabulary) this.vocabpackage.getVocablist().getItem(i-1)).getValue().getLength();j++){
                                stringBuilder.append(", ").append(((Vocabulary) this.vocabpackage.getVocablist().getItem(i-1)).getValue().getItem(j));
                            }
                            getSinglevocabpanel().getVocabvalue().setText(stringBuilder.substring(2));
                            setTablePanel(VocabularyTablePanel.VocabularyTable(this.vocabpackage,i-1));
                            if(i-1 == 0){
                                backbutton.setEnabled(false);
                            }
                        }catch (NullPointerException ignored){}
                        Main.mainframe.reload();
                        return;
                    }
                }
            }
        });

        deletebutton = new JButton("Vokabel löschen");
        deletebutton.setBounds(248,640,224,30);
        deletebutton.setFocusPainted(false);
        deletebutton.addActionListener(e -> {
            if(!getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase("")) {
                for (int i = 0;i<this.vocabpackage.getVocablist().getLength();i++){
                    if (getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase(((Vocabulary) this.vocabpackage.getVocablist().getItem(i)).getKey())){
                        this.vocabpackage.getVocablist().delete(i);
                        int count = i;
                        if (count == this.vocabpackage.getVocablist().getLength()){
                            count--;
                        }
                        try {
                            getSinglevocabpanel().getVocabkey().setText(((Vocabulary) this.vocabpackage.getVocablist().getItem(count)).getKey());
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0; j < ((Vocabulary) this.vocabpackage.getVocablist().getItem(count)).getValue().getLength(); j++) {
                                stringBuilder.append(", ").append(((Vocabulary) this.vocabpackage.getVocablist().getItem(count)).getValue().getItem(j));
                            }
                            getSinglevocabpanel().getVocabvalue().setText(stringBuilder.substring(2));
                            setTablePanel(VocabularyTablePanel.VocabularyTable(this.vocabpackage, count));
                            Main.mainframe.reload();
                            return;
                        }catch (NullPointerException exception){
                            getSinglevocabpanel().getVocabkey().setText("");
                            getSinglevocabpanel().getVocabvalue().setText("");
                            setTablePanel(VocabularyTablePanel.VocabularyTable(this.vocabpackage, -1));
                            Main.mainframe.reload();

                        }
                    }
                }
            }
        });

        forwardbutton = new JButton("Nächste Vokabel");
        forwardbutton.setBounds(486,640,224,30);
        forwardbutton.setFocusPainted(false);
        forwardbutton.setEnabled(true);
        forwardbutton.addActionListener(e -> {
            if(!getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase("")) {
                backbutton.setEnabled(true);
                for (int i = 0;i<this.vocabpackage.getVocablist().getLength();i++){
                    if (getSinglevocabpanel().getVocabkey().getText().equalsIgnoreCase(((Vocabulary) this.vocabpackage.getVocablist().getItem(i)).getKey())){
                        try{
                            getSinglevocabpanel().getVocabkey().setText(((Vocabulary) this.vocabpackage.getVocablist().getItem(i+1)).getKey());
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0;j < ((Vocabulary) this.vocabpackage.getVocablist().getItem(i+1)).getValue().getLength();j++){
                                stringBuilder.append(", ").append(((Vocabulary) this.vocabpackage.getVocablist().getItem(i+1)).getValue().getItem(j));
                            }
                            getSinglevocabpanel().getVocabvalue().setText(stringBuilder.substring(2));
                            setTablePanel(VocabularyTablePanel.VocabularyTable(this.vocabpackage,i+1));
                            if(i+1 == this.vocabpackage.getVocablist().getLength()-1){
                                forwardbutton.setEnabled(false);
                            }
                        }catch (NullPointerException ignored){}
                        Main.mainframe.reload();
                        return;
                    }
                }
            }
        });

        add(homebutton);
        add(tablepanel);
        add(singlevocabpanel);
        add(backbutton);
        add(newvocabbutton);
        add(forwardbutton);
        add(deletebutton);
    }

    public VocabularyShowPanel getSinglevocabpanel() {
        return singlevocabpanel;
    }

    public VocabPackage getVocabpackage() {
        return vocabpackage;
    }

    /**
     * Tabelle aktualisiert sich damit.
     */
    public void setTablePanel(JScrollPane tablepanel) {
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

    public JButton getBackbutton() {
        return backbutton;
    }

    public JButton getForwardbutton() {
        return forwardbutton;
    }
}
