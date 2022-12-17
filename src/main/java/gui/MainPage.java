package main.java.gui;

import main.java.main.Main;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPage extends JFrame {

    private int maxpage;
    private DynArray vocabPackage;
    private AtomicInteger currentpage;
    private JPanel jPanel;
    private JLabel page;
    private JButton previous;
    private JButton next;

    private JTextField searchtext;

    public MainPage(String title,int currentpage){
        super(title);

        this.currentpage = new AtomicInteger(currentpage);
        vocabPackage = Main.vocabpackagelist;
        maxpage = Math.max((int) Math.ceil(vocabPackage.getLength()/15d),1);

        if(this.currentpage.get() > maxpage)return;

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
        jPanel = new JPanel();
        jPanel.setBounds(10,10,888,590);
        jPanel.setBackground(Color.black);
        jPanel.setLayout(null);
        loadVocabPackages();

        //GUI
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

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
            CreateVocabPackage createVocabPackage = new CreateVocabPackage("Neue Karteikarte erstellen");
            createVocabPackage.setVisible(true);
            setEnabled(false);
        });

        //Vorherige Seite
        previous.addActionListener(e -> {
            this.currentpage.addAndGet(-1);
            loadVocabPackages();
            reload();
        });

        //Nächste Seite
        next.addActionListener(e -> {
            this.currentpage.addAndGet(+1);
            loadVocabPackages();
            reload();
        });

        //TextField
        searchtext = new JTextField();
        searchtext.setBounds(10,650,890,30);
        searchtext.addActionListener(e -> {
            filterVocabulary();
            this.currentpage.set(1);
            loadVocabPackages();
            previous.setEnabled(false);
            if(this.currentpage.get() < maxpage){
                next.setEnabled(true);
            }
            reload();
        });

        JButton search = new JButton("Suchen");
        search.setBounds(905,650,160,30);
        search.setFocusPainted(false);
        search.addActionListener(e -> {
            filterVocabulary();
            this.currentpage.set(1);
            maxpage = Math.max((int) Math.ceil(vocabPackage.getLength()/15d),1);
            loadVocabPackages();
            previous.setEnabled(false);
            if(this.currentpage.get() < maxpage){
                next.setEnabled(true);
            }
            reload();
        });

        //Container
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(search);
        pane.add(page);
        pane.add(searchtext);
        pane.add(modus);
        pane.add(previous);
        pane.add(next);
        pane.add(createkarteikasten);
        pane.add(author);
        pane.add(jPanel);

    }

    public void loadVocabPackages(){
        maxpage = Math.max((int) Math.ceil(vocabPackage.getLength()/15d),1);
        previous.setEnabled(true);
        next.setEnabled(true);
        if(currentpage.get() >= maxpage){
            next.setEnabled(false);
            if(currentpage.get() > maxpage){
                currentpage.set(maxpage);
            }
        }
        if(currentpage.get() == 1){
            previous.setEnabled(false);
        }
        if(page != null) {
            page.setText("Seite " + currentpage + " von " + maxpage);
        }
        jPanel.removeAll();
        int height = 10;
        for(int i = 15*currentpage.get()-15;i < Math.min(15*currentpage.get(), vocabPackage.getLength());i++){
            Karteibox karteibox = new Karteibox(((VocabPackage) vocabPackage.getItem(i)));
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
            jPanel.add(karteibox);
        }
    }

    public void reload(){
        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void filterVocabulary(){
        String[] strings = searchtext.getText().split(" ");
        DynArray sortedvocabpacket = new DynArray();
        vocabPackage = Main.vocabpackagelist;
        for(String s:strings){
            for(int i = 0;i < vocabPackage.getLength();i++){
                if(((VocabPackage) vocabPackage.getItem(i)).getName().toLowerCase().contains(s.toLowerCase())){
                    sortedvocabpacket.append(vocabPackage.getItem(i));
                    continue;
                }
                DynArray vocablist = ((VocabPackage) vocabPackage.getItem(i)).getVocablist();
                for(int j = 0;j < vocablist.getLength();j++){
                    Vocabulary vocab = (Vocabulary) vocablist.getItem(j);
                    if(vocab.getKey().toLowerCase().contains(s.toLowerCase())){
                        sortedvocabpacket.append(vocabPackage.getItem(i));
                        break;
                    }
                    boolean check = false;
                    for(int k = 0;k < vocab.getValue().getLength();k++){
                        if(((String) vocab.getValue().getItem(k)).toLowerCase().contains(s.toLowerCase())){
                            sortedvocabpacket.append(vocabPackage.getItem(i));
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
        vocabPackage = sortedvocabpacket;
    }

}
