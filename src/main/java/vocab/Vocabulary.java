package vocab;

import utils.Difficulty;

public class Vocabulary {

    private String key;
    private String value;
    private Difficulty difficulty;

    public Vocabulary(String key, String value){
        this.key = key;
        this.value = value;
        this.difficulty = Difficulty.UNDEFINED;
    }

    public Vocabulary(String key, String value, Difficulty difficulty){
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

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
