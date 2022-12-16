package gui;

import main.Main;
import utils.DynArray;
import vocab.VocabPackage;
import vocab.Vocabulary;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPage extends JFrame {

    private int maxpage;
    private DynArray vocabPackage;

    public MainPage(String title,int cp){
        super(title);

        AtomicInteger currentpage = new AtomicInteger(cp);
        vocabPackage = Main.vocabpackagelist;
        int vocablength = vocabPackage.getLength();
        maxpage = vocablength/15+1;

        if(currentpage.get() > maxpage)return;

        //Label
        JLabel author = new JLabel("<html><center>Erstellt von:<br>Jannis Peschke<br>Malte Wübben<br>" +
                "Leon Phan<br>Luca Kirschstein<br>Cedric Blabla<br>Dominik Schnüll</html>");
        author.setBounds(935,250,160,300);

        JLabel page = new JLabel("Seite "+currentpage+" von "+maxpage,SwingConstants.CENTER);
        page.setBounds(302,610,312,30);

        //Panel für die Karteikarten
        JPanel jPanel = new JPanel();
        jPanel.setBounds(10,10,888,590);
        jPanel.setBackground(Color.black);
        jPanel.setLayout(null);
        loadVocabPackages(null, jPanel, currentpage.get());

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
        });

        JButton previous = new JButton("Vorherige Seite");
        previous.setBounds(10,610,292,30);
        previous.setFocusPainted(false);

        JButton next = new JButton("Nächste Seite");
        next.setBounds(614,610,282,30);
        next.setFocusPainted(false);

        //Vorherige Seite
        if (currentpage.get() == 1){
            previous.setEnabled(false);
        }
        previous.addActionListener(e -> {
            currentpage.addAndGet(-1);
            loadVocabPackages(page, jPanel, currentpage.get());
            if (currentpage.get() == 1){
                previous.setEnabled(false);
            }
            if(currentpage.get() < maxpage){
                next.setEnabled(true);
            }
            reload(this);
        });

        //Nächste Seite
        if (currentpage.get() == maxpage){
            next.setEnabled(false);
        }
        next.addActionListener(e -> {
            currentpage.addAndGet(+1);
            loadVocabPackages(page, jPanel, currentpage.get());
            if (currentpage.get() == maxpage){
                next.setEnabled(false);
            }
            if (currentpage.get() != 1){
                previous.setEnabled(true);
            }
            previous.setEnabled(true);
            reload(this);
        });

        //TextField
        JTextField searchtext = new JTextField();
        searchtext.setBounds(10,650,890,30);
        searchtext.addActionListener(e -> {
            filterVocabulary(e.getActionCommand());
            currentpage.set(1);
            maxpage = vocabPackage.getLength()/15+1;
            loadVocabPackages(page,jPanel,currentpage.get());
            previous.setEnabled(false);
            if(currentpage.get() < maxpage){
                next.setEnabled(true);
            }
            reload(this);
        });

        JButton search = new JButton("Suchen");
        search.setBounds(905,650,160,30);
        search.setFocusPainted(false);
        search.addActionListener(e -> {
            filterVocabulary(searchtext.getText());
            currentpage.set(1);
            maxpage = vocabPackage.getLength()/15+1;
            loadVocabPackages(page,jPanel,currentpage.get());
            previous.setEnabled(false);
            if(currentpage.get() < maxpage){
                next.setEnabled(true);
            }
            reload(this);
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

    private void loadVocabPackages(JLabel pagetext, JPanel jPanel,int currentpage){
        if(pagetext != null) {
            pagetext.setText("Seite " + currentpage + " von " + maxpage);
        }
        jPanel.removeAll();
        int height = 10;
        for(int i = 15*currentpage-15;i < Math.min(15*currentpage, vocabPackage.getLength());i++){
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

    private void reload(Component component){
        SwingUtilities.updateComponentTreeUI(component);
        component.invalidate();
        component.validate();
        component.repaint();
    }

    private void filterVocabulary(String search){
        String[] strings = search.split(" ");
        DynArray sortedvocabpacket = new DynArray();
        vocabPackage = Main.vocabpackagelist;
        for(String s:strings){
            for(int i = 0;i < vocabPackage.getLength();i++){
                if(((VocabPackage) vocabPackage.getItem(i)).getName().toLowerCase().contains(s.toLowerCase())){
                    sortedvocabpacket.append(vocabPackage.getItem(i));
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
