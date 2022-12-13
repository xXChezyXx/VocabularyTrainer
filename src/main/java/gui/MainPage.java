package gui;

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
        author.setBounds(935,280,160,300);

        //Button
        JButton search = new JButton("Suchen");
        search.setBounds(905,650,160,30);
        search.addActionListener(e -> {
            System.out.println(searchtext.getText());
        });

        JButton modus = new JButton("<html><center>Prüfungsmodus<br>aktivieren</html>");
        modus.setBounds(905,10,160,60);
        modus.setFocusPainted(false);
        modus.addActionListener(e -> {
            if(modus.getText().contains("deaktivieren")) {
                modus.setText("<html><center>Prüfungsmodus<br>aktivieren</html>");
                return;
            }
            modus.setText("<html><center>Prüfungsmodus<br>deaktivieren</html>");
        });

        JButton createkarteikasten = new JButton("<html><center>Karteikasten<br>erstellen</html>");
        createkarteikasten.setBounds(905,100,160,60);
        createkarteikasten.setFocusPainted(false);
        createkarteikasten.addActionListener(e -> {
        });

        JButton deletekarteikasten = new JButton("<html><center>Karteikasten<br>löschen</html>");
        deletekarteikasten.setBounds(905,190,160,60);
        deletekarteikasten.setFocusPainted(false);
        deletekarteikasten.addActionListener(e -> {
        });

        //Container
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(search);
        pane.add(searchtext);
        pane.add(modus);
        pane.add(createkarteikasten);
        pane.add(deletekarteikasten);
        pane.add(author);
    }

}
