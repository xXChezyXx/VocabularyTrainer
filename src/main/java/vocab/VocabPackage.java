package main.java.vocab;

import main.java.main.Main;
import main.java.utils.Difficulty;
import main.java.utils.DynArray;

import java.util.Random;


/*Dieser Code definiert eine Klasse mit dem Namen "VocabPackage",
die einen Namen, eine Liste von Vokabeln (die in einem DynArray gespeichert sind)
und verschiedene Zähler für richtige und falsche Antworten enthält.*/

public class VocabPackage {

    private String name;
    private DynArray vocablist;
    private int richtig;
    private int falsch;

    public VocabPackage(DynArray vocablist, String name){
        this.vocablist = vocablist;
        this.name = name;
    }

    // getVocablist() gibt das gesamte Vokabelliste zurück.
    public DynArray getVocablist() {
        return vocablist;
    }

    // getRightVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "richtig" markiert sind.
    public DynArray getRightVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.RICHTIG){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    // getWrongVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "falsch" markiert sind.
    public DynArray getWrongVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.FALSCH){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }


    // getName() gibt den Namen des Vokabellisten zurück.
    public String getName() {
        return name;
    }

    //getHardVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "schwer" markiert sind.
    public DynArray getHardVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.HARD){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    //getUndefinedVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "undefiniert" markiert sind.
    public DynArray getUndefinedVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.UNDEFINED){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    /*
    Aufteilung für die Vokabeln:        Wenn RICHTIG leer ist:          Wenn FALSCH leer ist:           Wenn HARD leer ist:
    RICHTIG=5%                          FALSCH=70%                      RICHTIG=20%                     RICHTIG=10%
    FALSCH=70%                          HARD=30%                        HARD=80%                        FALSCH=90%
    HARD=25%
                                           ------ Dabei hat Undefined Vorrang ------
    */
    /* getRandomVocab() gibt eine zufällige Vokabel aus der Liste zurück.
    Wenn der Prüfungsmodus aktiviert ist, wird eine zufällige Vokabel aus der gesamten Liste ausgewählt.
    Andernfalls werden die Vokabeln nach ihrer Schwierigkeit ausgewählt, wobei die "undefinierten" Vokabeln Vorrang haben.*/
    public Vocabulary getRandomVocab(){
        if(Main.pruefungsmodus){
            int randomvocab = new Random().nextInt(vocablist.getLength());
            return (Vocabulary) vocablist.getItem(randomvocab);
        }
        if(!getUndefinedVocabList().isEmpty()){
            int randomvocab = new Random().nextInt(getUndefinedVocabList().getLength());
            return (Vocabulary) getUndefinedVocabList().getItem(randomvocab);
        }
        setProbability();
        int chance = new Random().nextInt(100)+1;
        if(chance <= richtig){ //Wenn richtig gewählt wurde
            int randomright = new Random().nextInt(getRightVocabList().getLength());
            return (Vocabulary) getUndefinedVocabList().getItem(randomright);
        }else if(chance <= richtig+falsch){ //Wenn falsch gewählt wurde
            int randomwrong = new Random().nextInt(getWrongVocabList().getLength());
            return (Vocabulary) getUndefinedVocabList().getItem(randomwrong);
        }
        int randomhard = new Random().nextInt(getHardVocabList().getLength()); //Wenn hard gewählt wurde
        return (Vocabulary) getUndefinedVocabList().getItem(randomhard);
    }

    /*  setProbability() berechnet die Wahrscheinlichkeiten, mit denen Vokabeln ausgewählt werden
     , basierend auf den Prozentsätzen, die im Kommentar beschrieben sind.*/
    private void setProbability(){
        richtig = 0;
        falsch = 0;
        if(getRightVocabList().isEmpty()){
            falsch = 70;
        }else if(getRightVocabList().isEmpty() && getHardVocabList().isEmpty()){
            falsch = 100;
        }else if(getWrongVocabList().isEmpty()){
            richtig = 20;
        }else if(getWrongVocabList().isEmpty() && getHardVocabList().isEmpty()){
            richtig = 100;
        }else if(getHardVocabList().isEmpty()){
            richtig = 10;
            falsch = 90;
        }else if(getHardVocabList().isEmpty() && getWrongVocabList().isEmpty()){
            richtig = 100;
        }else if(getHardVocabList().isEmpty() && getRightVocabList().isEmpty()){
            falsch = 100;
        }
    }

    public void addVocabulary(Vocabulary vocab){
        for (int i = 0; i < vocablist.getLength(); i++) {
            Vocabulary comparedvocab = (Vocabulary) vocablist.getItem(i);
            if(vocab.getKey().toLowerCase().equals(comparedvocab.getKey())){
                DynArray values = comparedvocab.getValue();
                for (int j = 0; j < vocab.getValue().getLength(); j++) {
                    values.append(vocab.getValue().getItem(j));
                }
                return;
            }
            for (int j = 0; j < vocab.getValue().getLength(); j++) {
                if (comparedvocab.getKey().toLowerCase().equals(vocab.getValue().getItem(j))){
                    vocab.getValue().delete(j);
                    comparedvocab.setValue(addValuesTogether(comparedvocab.getValue(),vocab.getKey()));
                    return;
                }
            }
        }
        vocablist.append(vocab);
    }

    private DynArray addValuesTogether(DynArray value1, String value2){
        value1.append(value2);
        return value1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVocablist(DynArray vocablist) {
        this.vocablist = vocablist;
    }
}
