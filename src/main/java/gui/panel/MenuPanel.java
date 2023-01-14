package main.java.gui.panel;

import main.java.gui.frame.CreateVocabPackageFrame;
import main.java.main.Main;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public String title = "Vokabeltrainer";
    private int maxpage;
    private DynArray vocabpackage;
    private int currentpage;
    private JPanel vocablistpanel;
    private JLabel page;
    private JButton previous;
    private JButton next;
    private JTextField searchtext;

    public MenuPanel(int currenpage){
        this.currentpage = currenpage;
        vocabpackage = Main.vocabpackagelist;
        maxpage = Math.max((int) Math.ceil(vocabpackage.getLength()/15d),1);

        if(this.currentpage > maxpage)return;

        previous = new JButton("Vorherige Seite");
        previous.setBounds(10,610,292,30);
        previous.setFocusPainted(false);

        next = new JButton("Nächste Seite");
        next.setBounds(614,610,282,30);
        next.setFocusPainted(false);

        //Label
        JLabel author = new JLabel("<html><center>Erstellt von:<br>Jannis Peschke<br>Malte Wübben<br>" +
                "Leon Phan<br>Luca Kirschstein<br>Cedric Blabla<br>Dominik Schnüll</html>");
        author.setBounds(935,250,160,300);

        page = new JLabel("Seite "+currentpage+" von "+maxpage,SwingConstants.CENTER);
        page.setBounds(302,610,312,30);

        //Panel für die Karteikarten
        vocablistpanel = new JPanel();
        vocablistpanel.setBounds(10,10,888,590);
        vocablistpanel.setBackground(Color.black);
        vocablistpanel.setLayout(null);
        loadVocabPackages();

        //GUI
        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        //Button

        JButton modus = new JButton("<html><center>Prüfungsmodus<br>aktivieren</html>");
        modus.setBounds(905,10,160,60);
        modus.setFocusPainted(false);
        modus.addActionListener(e -> {
            if(Main.pruefungsmodus) {
                modus.setText("<html><center>Prüfungsmodus<br>aktivieren</html>");
                Main.pruefungsmodus = false;
                return;
            }
            modus.setText("<html><center>Prüfungsmodus<br>deaktivieren</html>");
            Main.pruefungsmodus = true;
        });

        JButton createkarteikasten = new JButton("<html><center>Karteikasten<br>erstellen</html>");
        createkarteikasten.setBounds(905,100,160,60);
        createkarteikasten.setFocusPainted(false);
        createkarteikasten.addActionListener(e -> {
            CreateVocabPackageFrame createVocabPackage = new CreateVocabPackageFrame("Neue Karteikarte erstellen");
            createVocabPackage.setVisible(true);
            Main.mainframe.setEnabled(false);
        });

        //Vorherige Seite
        previous.addActionListener(e -> {
            this.currentpage--;
            loadVocabPackages();
            Main.mainframe.reload();
        });

        //Nächste Seite
        next.addActionListener(e -> {
            this.currentpage++;
            loadVocabPackages();
            Main.mainframe.reload();
        });

        //TextField
        searchtext = new JTextField();
        searchtext.setBounds(10,650,890,30);
        searchtext.addActionListener(e -> {
            filterVocabulary();
            this.currentpage = 1;
            loadVocabPackages();
            previous.setEnabled(false);
            if(this.currentpage < maxpage){
                next.setEnabled(true);
            }
            Main.mainframe.reload();
        });

        JButton search = new JButton("Suchen");
        search.setBounds(905,650,160,30);
        search.setFocusPainted(false);
        search.addActionListener(e -> {
            filterVocabulary();
            this.currentpage = 1;
            maxpage = Math.max((int) Math.ceil(vocabpackage.getLength()/15d),1);
            loadVocabPackages();
            previous.setEnabled(false);
            if(this.currentpage < maxpage){
                next.setEnabled(true);
            }
            Main.mainframe.reload();
        });

        //Container
        add(search);
        add(page);
        add(searchtext);
        add(modus);
        add(previous);
        add(next);
        add(createkarteikasten);
        add(author);
        add(vocablistpanel);
    }

    public void loadVocabPackages(){
        maxpage = Math.max((int) Math.ceil(vocabpackage.getLength()/15d),1);
        previous.setEnabled(true);
        next.setEnabled(true);
        if(currentpage >= maxpage){
            next.setEnabled(false);
            if(currentpage > maxpage){
                currentpage = maxpage;
            }
        }
        if(currentpage == 1){
            previous.setEnabled(false);
        }
        if(page != null) {
            page.setText("Seite " + currentpage + " von " + maxpage);
        }
        vocablistpanel.removeAll();
        int height = 10;
        for(int i = 15*currentpage-15; i < Math.min(15*currentpage, vocabpackage.getLength()); i++){
            KarteiboxPanel karteibox = new KarteiboxPanel(((VocabPackage) vocabpackage.getItem(i)));
            switch (i % 3){
                case 0:
                    karteibox.setBounds(10,height,282,106);
                    break;
                case 1:
                    karteibox.setBounds(302,height,282,106);
                    break;
                case 2:
                    karteibox.setBounds(594,height,282,106);
                    height += 116;
                    break;
            }
            vocablistpanel.add(karteibox);
        }
    }

    public void filterVocabulary(){
        String[] strings = searchtext.getText().split(" ");
        DynArray sortedvocabpackage = new DynArray();
        vocabpackage = Main.vocabpackagelist;
        for(String s:strings){
            for(int i = 0; i < vocabpackage.getLength(); i++){
                if(((VocabPackage) vocabpackage.getItem(i)).getName().toLowerCase().contains(s.toLowerCase())){
                    sortedvocabpackage.append(vocabpackage.getItem(i));
                    continue;
                }
                DynArray vocablist = ((VocabPackage) vocabpackage.getItem(i)).getVocablist();
                for(int j = 0;j < vocablist.getLength();j++){
                    Vocabulary vocab = (Vocabulary) vocablist.getItem(j);
                    if(vocab.getKey().toLowerCase().contains(s.toLowerCase())){
                        sortedvocabpackage.append(vocabpackage.getItem(i));
                        break;
                    }
                    boolean check = false;
                    for(int k = 0;k < vocab.getValue().getLength();k++){
                        if(((String) vocab.getValue().getItem(k)).toLowerCase().contains(s.toLowerCase())){
                            sortedvocabpackage.append(vocabpackage.getItem(i));
                            check = true;
                            break;
                        }
                    }
                    if (check){
                        break;
                    }
                }
            }
        }
        vocabpackage = sortedvocabpackage;
    }

    public String getTitle() {
        return title;
    }
}
