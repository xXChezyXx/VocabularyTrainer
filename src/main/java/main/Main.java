package main;

import gui.MainPage;
import utils.DynArray;
import vocab.VocabPackage;
import vocab.Vocabulary;

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
        Vocabulary vocabulary = new Vocabulary("hello","world");
        DynArray test = new DynArray();
        test.append(vocabulary);
        vocabpackagelist.append(new VocabPackage(new DynArray(),"QWERTZUIOPASDFGH"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Testen"));
        vocabpackagelist.append(new VocabPackage(test,"Kein Bock"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vier"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sechs"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sieben"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Acht"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Neun"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Elf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zwölf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vier"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sechs"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sieben"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Acht"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Neun"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Elf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zwölf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sechszehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Siebzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Kein Bock"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vier"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sechs"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Neun"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Elf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Zwölf"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Dreizehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Vierzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Fünfzehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Sechszehn"));
        vocabpackagelist.append(new VocabPackage(new DynArray(),"Siebzehn"));

        MainPage mainPage = new MainPage("Vokabeltrainer",1);
        mainPage.setVisible(true);
    }

}