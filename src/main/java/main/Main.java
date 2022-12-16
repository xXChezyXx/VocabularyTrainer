package main;

import gui.MainPage;
import utils.DynArray;

public class Main {

    public static boolean pruefungsmodus = false;
    public static DynArray vocabpackagelist = new DynArray();

    public static void main(String[] args) {
        // Operation, die ausgeführt wird, wenn die App sich schließt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("App fährt herunter!");
        }));

        // Unter dem Kommentar den Code schreiben
        MainPage mainPage = new MainPage("Vokabeltrainer");
        mainPage.setVisible(true);
    }

}