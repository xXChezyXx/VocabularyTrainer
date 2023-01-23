package main.java.vocab;

import main.java.main.Main;
import main.java.utils.Difficulty;
import main.java.utils.DynArray;

import java.util.Random;


/**
 * Karteikasten, die eine Liste von Vokabeln enthält.
 */
public class VocabPackage {

    /**
     * Name der Karteikasten.
     */
    private String name;

    /**
     * Inhalt vom Karteikasten.
     */
    private DynArray vocablist;

    /**
     * Wahrscheinlichkeit für Vokabeln, die schon richtig erraten wurden.
     */
    private int richtig;

    /**
     * Wahrscheinlichkeit für Vokabeln, die schon falsch erraten wurden.
     */
    private int falsch;

    public VocabPackage(DynArray vocablist, String name){
        this.vocablist = vocablist;
        this.name = name;
    }

    public DynArray getVocablist() {
        return vocablist;
    }

    /**
     * Gibt eine Liste mit allen Vokabeln zurück, die als "richtig" markiert sind.
     */
    public DynArray getRightVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.RICHTIG){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    /**
     * Gibt eine Liste mit allen Vokabeln zurück, die als "falsch" markiert sind.
     */
    public DynArray getWrongVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.FALSCH){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    public String getName() {
        return name;
    }

    /**
     * Gibt eine Liste mit allen Vokabeln zurück, die als "schwer" markiert sind.
     */
    public DynArray getHardVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.HARD){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    /**
     * Gibt eine Liste mit allen Vokabeln zurück, die als "undefined" markiert sind.
     */
    public DynArray getUndefinedVocabList() {
        DynArray dynArray = new DynArray();
        for (int i = 0;i < vocablist.getLength();i++){
            if(((Vocabulary) vocablist.getItem(i)).getDifficulty() == Difficulty.UNDEFINED){
                dynArray.append(vocablist.getItem(i));
            }
        }
        return dynArray;
    }

    /**
     * Gibt eine zufällige Vokabel zurück, dabei wird die Operation für den Prüfungsmodus verwendet.
     * Die Wahrscheinlichkeiten ergeben sich folgendermaßen:
     * Aufteilung für die Vokabeln: RICHTIG=5%  FALSCH=70%  HARD=25%
     * Wenn RICHTIG leer ist:                   FALSCH=70%  HARD=30%
     * Wenn FALSCH leer ist:        RICHTIG=20%             HARD=80%
     * Wenn HARD leer ist:          RICHTIG=10% FALSCH=90%.
     * Dabei hat Undefined Vorrang
     */
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

    /**
     * Verteilt die Wahrscheinlichkeit basierend auf die Schwierigkeitsgrade der Vokabeln.
     */
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


    /**
     * Fügt eine Vokabel im Karteikasten hinzu. Bei Duplikationen werden die bestimmte Vokabeln überschrieben.
     */
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


    /**
     * Gibt eine Liste der Antworten zurück für das Überschreiben der Vokabel.
     */
    private DynArray addValuesTogether(DynArray value1, String value2){
        for (int i = 0;i < value1.getLength();i++){
            if(((String) value1.getItem(i)).equalsIgnoreCase(value2)){
                return value1;
            }
        }
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
