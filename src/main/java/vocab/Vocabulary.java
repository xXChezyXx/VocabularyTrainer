package main.java.vocab;

import main.java.utils.Difficulty;
import main.java.utils.DynArray;

public class Vocabulary {

    private String key; //Frage
    private DynArray value; //Antwort
    private Difficulty difficulty;



    public Vocabulary(String key, String value){
        this.key = key;
        DynArray dynArray = new DynArray();
        dynArray.append(value);
        this.value = dynArray;
        this.difficulty = Difficulty.UNDEFINED;
    }

    public Vocabulary(String key, DynArray value){
        this.key = key;
        this.value = value;
        this.difficulty = Difficulty.UNDEFINED;
    }

    public Vocabulary(String key, DynArray value, Difficulty difficulty){
        this.key = key;
        this.value = value;
        this.difficulty = difficulty;
    }


    //getDifficulty() gibt die Schwierigkeit einer Vokabel zurück
    public Difficulty getDifficulty() {
        return difficulty;
    }


    //getKey() gibt die Frage einer Vokabel zurück
    public String getKey() {
        return key;
    }


    //getKey(), gibt die Antwort einer Vokabel zurück
    public DynArray getValue() {
        return value;
    }


    //setKey(String key) erlaubt die Frage einer Vokabel zu setzen
    public void setKey(String key) {
        this.key = key;
    }


    //setValue(DynArray value) erlaubt die Antwort einer Vokabel zu setzen
    public void setValue(DynArray value) {
        this.value = value;
    }


    //setDifficulty(Difficulty difficulty) setzt den difficulty der Vokabel
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
