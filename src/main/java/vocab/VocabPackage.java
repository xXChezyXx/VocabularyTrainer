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
// Es gibt verschiedene Vokabellisten die über ihre Schwierigkeiten definiert werden. Jeder Vokabel wird zu Beginn der Wert "Undefined" zugeordnet. Nach dem ersten bearbeiten wird die Vokabel (wenn man sie falsch übersetzt) der Schwierigkeit falsch zugeordnet, wenn sie jedoch richtig beantwortet wird, gibt es die Möglichkeit sie entweder der Schwierigkeit "Richtig" oder "Hard" je nach dem persönlichem Empfinden zuzuordnen.
    // getVocablist() gibt das gesamte Vokabelliste zurück.
    public DynArray getVocablist() {
        return vocablist;
    }            //durch return wird die aktuelle Vocablist abgerufen

    // getRightVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "richtig" markiert sind.
    public DynArray getRightVocabList() {   //Die Lösungen der Vokabelliste werden durch die RightVocabList abgerufen
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.RICHTIG){  //Eine komplette Liste, mit Vokabeln die den wert Difficulty.Richtig haben, wird zurückgegeben
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    // getWrongVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "falsch" markiert sind.
    public DynArray getWrongVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.FALSCH){   //Eine komplette Liste, mit Vokabeln die den Wert Difficulty.Falsch haben, wird zurückgegeben
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
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.HARD){     //Ein komplette Liste, mit Vokabeln die den Wert Difficulty.Hard haben, wird zurückgegeben
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    //getUndefinedVocabList() gibt eine Liste mit allen Vokabeln zurück, die als "undefiniert" markiert sind.
    public DynArray getUndefinedVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.UNDEFINED){    //Wenn eine neue Vokabel erstellt wird, kommt sie in eine Liste wo Vokabeln den Wert Difficulty.Undifined haben
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
        if(!getUndefinedVocabList().isEmpty()){
            int randomvocab = new Random().nextInt(getUndefinedVocabList().getLength());
            return (Vocabulary) getUndefinedVocabList().getItem(randomvocab);
        }
        setProbability();
        int chance = new Random().nextInt(100)+1;
        if(chance <= richtig){ //Wenn richtig gewählt wurde
            int randomright = new Random().nextInt(getRightVocabList().getLength());
            return (Vocabulary) getRightVocabList().getItem(randomright);
        }else if(chance <= richtig+falsch){ //Wenn falsch gewählt wurde
            int randomwrong = new Random().nextInt(getWrongVocabList().getLength());
            return (Vocabulary) getWrongVocabList().getItem(randomwrong);
        }
        int randomhard = new Random().nextInt(getHardVocabList().getLength()); //Wenn hard gewählt wurde
        return (Vocabulary) getHardVocabList().getItem(randomhard);
    }

    /*  setProbability() berechnet die Wahrscheinlichkeiten, mit denen Vokabeln ausgewählt werden
     , basierend auf den Prozentsätzen, die im Kommentar beschrieben sind.*/
    private void setProbability(){
        richtig = 0;
        falsch = 0;
        if(getRightVocabList().isEmpty() && !getHardVocabList().isEmpty()){
            falsch = 70;
        }else if(getRightVocabList().isEmpty() && getHardVocabList().isEmpty()){
            falsch = 100;
        }else if(getWrongVocabList().isEmpty() && !getHardVocabList().isEmpty()){
            richtig = 20;
        }else if(getWrongVocabList().isEmpty() && getHardVocabList().isEmpty()){
            richtig = 100;
        }else if(getHardVocabList().isEmpty() && !getWrongVocabList().isEmpty() && !getRightVocabList().isEmpty()){
            richtig = 10;
            falsch = 90;
        }else if(getHardVocabList().isEmpty() && getWrongVocabList().isEmpty()){
            richtig = 100;
        }else if(getHardVocabList().isEmpty() && getRightVocabList().isEmpty()){
            falsch = 100;
        }
    }


    //addVocabulary(Vocabulary vocab) fügt eine neue Vokabel zur Liste hinzu.
    public void addVocabulary(Vocabulary vocab){
        for (int i = 0; i < vocablist.getLength(); i++) {
            Vocabulary comparedvocab = (Vocabulary) vocablist.getItem(i);
            if(vocab.getKey().equalsIgnoreCase(comparedvocab.getKey())){
                DynArray values = comparedvocab.getValue();
                for (int j = 0; j < vocab.getValue().getLength(); j++) {
                    boolean check = true;
                    for(int k = 0;k < values.getLength();k++){
                        if (((String) values.getItem(k)).equalsIgnoreCase((String) vocab.getValue().getItem(j))){
                            check = false;
                        }
                    }
                    if(check) values.append(vocab.getValue().getItem(j));
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


    //addValuesTogether() erhöht die Anzahl der richtigen und falschen Antworten um die Werte, die in einer gegebenen Vokabel gespeichert sind.
    private DynArray addValuesTogether(DynArray value1, String value2){
        for (int i = 0;i < value1.getLength();i++){
            if(((String) value1.getItem(i)).equalsIgnoreCase(value2)){
                return value1;
            }
        }
        value1.append(value2);
        return value1;
    }


    // setName(String name) setzt den Namen der Vokabellisten auf den angegebenen Wert.
    public void setName(String name) {
        this.name = name;
    }


    //setVocablist(DynArray vocablist) setzt die Vokabelliste auf die angegebene Liste.
    public void setVocablist(DynArray vocablist) {
        this.vocablist = vocablist;
    }
}
