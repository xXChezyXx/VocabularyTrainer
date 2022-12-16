package gui;

import gui.statistik.Karteibox;
import main.Main;
import vocab.VocabPackage;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    public MainPage(String title){
        super(title);

        //TODO Die Karteikärtchen im GUI einfügen, sodass die Standartkonfiguration fertig ist

        //GUI
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //TextField
        JTextField searchtext = new JTextField();
        searchtext.setBounds(10,650,890,30);
        searchtext.addActionListener(e -> {
            System.out.println(e.getActionCommand());
        });

        //Label
        JLabel author = new JLabel("<html><center>Erstellt von:<br>Jannis Peschke<br>Malte Wübben<br>" +
                "Leon Phan<br>Luca Kirschstein<br>Cedric Blabla<br>Dominik Schnüll</html>");
        author.setBounds(935,250,160,300);

        JLabel page = new JLabel("Seite 1 von 1",SwingConstants.CENTER);
        page.setBounds(302,610,312,30);

        //Button
        JButton search = new JButton("Suchen");
        search.setBounds(905,650,160,30);
        search.setFocusPainted(false);
        search.addActionListener(e -> {
            System.out.println(searchtext.getText());
        });

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
        previous.addActionListener(e -> {
        });

        JButton next = new JButton("Nächste Seite");
        next.setBounds(614,610,282,30);
        next.setFocusPainted(false);
        next.addActionListener(e -> {
        });

        VocabPackage vocabPackage = new VocabPackage(null,"QWERTZUIOPASDFGH");
        VocabPackage vocabPackage2 = new VocabPackage(null,"Testen");
        VocabPackage vocabPackage3 = new VocabPackage(null,"Kein Bock");
        VocabPackage vocabPackage4 = new VocabPackage(null,"vier");
        VocabPackage vocabPackage5 = new VocabPackage(null,"fünf");
        VocabPackage vocabPackage6 = new VocabPackage(null,"sechs");
        VocabPackage vocabPackage7 = new VocabPackage(null,"sieben");
        VocabPackage vocabPackage8 = new VocabPackage(null,"acht");
        //Karteiboxen
        //TODO dynamisch gestalten, sodass es nicht hardcoded ist
        Karteibox test = new Karteibox(vocabPackage);
        Karteibox test2 = new Karteibox(vocabPackage2);
        Karteibox test3 = new Karteibox(vocabPackage3);
        Karteibox test4 = new Karteibox(vocabPackage4);
        Karteibox test5 = new Karteibox(vocabPackage5);
        Karteibox test6 = new Karteibox(vocabPackage6);
        Karteibox test7 = new Karteibox(vocabPackage7);
        Karteibox test8 = new Karteibox(vocabPackage8);

        //Liste der Karteikasten
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(10,10,888,590);
        jScrollPane.setBackground(Color.black);
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setLayout(null);
        jScrollPane.add(test);
        jScrollPane.add(test2);
        test2.setBounds(302,10,282,106);
        jScrollPane.add(test3);
        test3.setBounds(594,10,282,106);
        jScrollPane.add(test4);
        test4.setBounds(10,126,282,106);
        jScrollPane.add(test5);
        test5.setBounds(10,242,282,106);
        jScrollPane.add(test6);
        test6.setBounds(10,358,282,106);
        jScrollPane.add(test7);
        test7.setBounds(10,474,282,106);




        //ScrollPane

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
        pane.add(jScrollPane);
    }

}
