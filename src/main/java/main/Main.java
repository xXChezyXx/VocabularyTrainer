package main;

import gui.MainPage;
import utils.DynArray;
import vocab.VocabPackage;

public class Main {

    public static boolean pruefungsmodus = false;
    public static DynArray vocabpackagelist = new DynArray();

    public static void main(String[] args) {
        // Operation, die ausgeführt wird, wenn die App sich schließt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("App fährt herunter!");
        }));

        // Unter dem Kommentar den Code schreiben

        //TestVokabeln
        vocabpackagelist.append(new VocabPackage(null,"QWERTZUIOPASDFGH"));
        vocabpackagelist.append(new VocabPackage(null,"Testen"));
        vocabpackagelist.append(new VocabPackage(null,"Kein Bock"));
        vocabpackagelist.append(new VocabPackage(null,"Vier"));
        vocabpackagelist.append(new VocabPackage(null,"Fünf"));
        vocabpackagelist.append(new VocabPackage(null,"Sechs"));
        vocabpackagelist.append(new VocabPackage(null,"Sieben"));
        vocabpackagelist.append(new VocabPackage(null,"Acht"));
        vocabpackagelist.append(new VocabPackage(null,"Neun"));
        vocabpackagelist.append(new VocabPackage(null,"Zehn"));
        vocabpackagelist.append(new VocabPackage(null,"Elf"));
        vocabpackagelist.append(new VocabPackage(null,"Zwölf"));
        vocabpackagelist.append(new VocabPackage(null,"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(null,"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Vier"));
        vocabpackagelist.append(new VocabPackage(null,"Fünf"));
        vocabpackagelist.append(new VocabPackage(null,"Sechs"));
        vocabpackagelist.append(new VocabPackage(null,"Sieben"));
        vocabpackagelist.append(new VocabPackage(null,"Acht"));
        vocabpackagelist.append(new VocabPackage(null,"Neun"));
        vocabpackagelist.append(new VocabPackage(null,"Zehn"));
        vocabpackagelist.append(new VocabPackage(null,"Elf"));
        vocabpackagelist.append(new VocabPackage(null,"Zwölf"));
        vocabpackagelist.append(new VocabPackage(null,"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(null,"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Sechszehn"));
        vocabpackagelist.append(new VocabPackage(null,"Siebzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Kein Bock"));
        vocabpackagelist.append(new VocabPackage(null,"Vier"));
        vocabpackagelist.append(new VocabPackage(null,"Fünf"));
        vocabpackagelist.append(new VocabPackage(null,"Sechs"));
        vocabpackagelist.append(new VocabPackage(null,"Neun"));
        vocabpackagelist.append(new VocabPackage(null,"Zehn"));
        vocabpackagelist.append(new VocabPackage(null,"Elf"));
        vocabpackagelist.append(new VocabPackage(null,"Zwölf"));
        vocabpackagelist.append(new VocabPackage(null,"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(null,"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(null,"Sechszehn"));
        vocabpackagelist.append(new VocabPackage(null,"Siebzehn"));

        MainPage mainPage = new MainPage("Vokabeltrainer",1);
        mainPage.setVisible(true);
    }

}