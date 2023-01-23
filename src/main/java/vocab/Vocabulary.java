package main.java.vocab;

import main.java.utils.Difficulty;
import main.java.utils.DynArray;

public class Vocabulary {

    /**
     * Frage der Vokabel.
     */
    private String key;

    /**
     * Antwort der Vokabel
     */
    private DynArray value;

    /**
     * Schwierigkeitsgrad der Vokabel
     */
    private Difficulty difficulty;



    /**
     * Eine Vokabel besteht aus einer Frage, einer Antwort und eines Schwierigkeitsgrades.
     */
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getKey() {
        return key;
    }

    public DynArray getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(DynArray value) {
        this.value = value;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
