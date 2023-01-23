package main.java.main;

import main.java.data.FileConfiguration;
import main.java.gui.frame.MainFrame;
import main.java.utils.DynArray;

public class Main {

    /**
     * Prüfungsmodus kann angeschaltet und ausgeschaltet werden.
     */
    public static boolean pruefungsmodus = false;

    /**
     * Liste der Karteikasten, die geladen werden.
     */
    public static DynArray vocabpackagelist = new DynArray();

    /**
     * Damit wir ein Fenster MainFrame erstellt, was das Hauptmenü des Programms sein soll.
     */
    public static MainFrame mainframe;

    public static void main(String[] args) {

        FileConfiguration fileConfiguration = new FileConfiguration("./Vokabeln.json");

        // Operation, die ausgeführt wird, wenn die App sich schließt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            fileConfiguration.saveVocabPackage();
            System.out.println("App fährt herunter!");
        }));

        // Unter dem Kommentar den Code schreiben

        vocabpackagelist = fileConfiguration.loadVocablist();

        mainframe = new MainFrame();
        mainframe.setVisible(true);
    }

}