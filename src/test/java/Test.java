package test.java;

import main.java.utils.DynArray;

public class Test {

    public static void main(String[] args) {
        DynArray dynArray = new DynArray();
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);
        dynArray.append(6);
        dynArray.append(7);
        dynArray.append(8);
        dynArray.append(9);
        dynArray.append(10);

        dynArray.delete(4);
        for (int i = 0; i < dynArray.getLength(); i++) {
            System.out.println(dynArray.getItem(i));
        }
    }


    /*

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
        */

}
