package vocab;

import utils.Difficulty;
import utils.DynArray;

public class Vocabulary {

    private String key;
    private DynArray value;
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
