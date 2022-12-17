package main.java.main;

import main.java.data.FileConfiguration;
import main.java.gui.MainPage;
import main.java.utils.DynArray;

public class Main {

    public static boolean pruefungsmodus = false;
    public static DynArray vocabpackagelist = new DynArray();

    public static MainPage mainPage;

    public static void main(String[] args) {

        FileConfiguration fileConfiguration = new FileConfiguration("C:\\TryHackMe_Material\\Vokabeln.json");

        // Operation, die ausgeführt wird, wenn die App sich schließt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            fileConfiguration.saveVocabPackage();
            System.out.println("App fährt herunter!");
        }));

        // Unter dem Kommentar den Code schreiben
        //TODO Prüfungsmodus einstellen
        //TODO Karteikasten öffnen
        //TODO Vokabeln speichern

        vocabpackagelist = fileConfiguration.loadVocablist();

        mainPage = new MainPage("Vokabeltrainer",1);
        mainPage.setVisible(true);
    }

}