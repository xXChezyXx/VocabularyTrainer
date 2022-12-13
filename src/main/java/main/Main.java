package main;

import gui.MainPage;

import javax.swing.*;

public class Main {

    public static boolean pruefungsmodus = false;

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