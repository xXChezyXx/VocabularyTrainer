package main.java.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileConfiguration {

    private File file;
    private FileWriter fileWriter;

    private JsonArray jsonArray = new JsonArray();
    public FileConfiguration(String file) throws IOException {
        this.file = new File(file);
        fileWriter = new FileWriter(this.file);
        this.file.createNewFile();
    }

    public void addVocabPackage(VocabPackage vocabPackage) throws IOException {
        String[] content = Files.readAllLines(file.toPath()).toArray(new String[0]);
        System.out.println(content);
        JsonObject jsonPackage = new JsonObject();
        jsonPackage.addProperty("name",vocabPackage.getName());
        JsonArray jsonvocabs = new JsonArray();
        for (int i = 0;i < vocabPackage.getVocablist().getLength(); i++){
            Vocabulary vocabulary = ((Vocabulary) vocabPackage.getVocablist().getItem(i));
            //Frage und Schwierigkeit hinzufügen
            JsonObject vocab = new JsonObject();
            vocab.addProperty("key",vocabulary.getKey());
            vocab.addProperty("difficulty",vocabulary.getDifficulty().toString());
            //Antwort hinzufügen
            JsonArray answers = new JsonArray();
            for(int j = 0;j < ((Vocabulary) vocabPackage.getVocablist().getItem(i)).getValue().getLength(); j++){
                String answer = (String) ((Vocabulary) vocabPackage.getVocablist().getItem(i)).getValue().getItem(j);
                answers.add(answer);
            }
            vocab.add("value",answers);
            jsonvocabs.add(vocab);
        }
        jsonPackage.add("vocablist",jsonvocabs);
        fileWriter.write(jsonPackage.toString());
        fileWriter.close();
    }

    public File getFile() {
        return file;
    }

    public String getPath(){
        return getFile().getAbsolutePath();
    }
    /*
    Tipps für Malte:
        Um Daten speichern zu können, braucht man sogenannten Path (zum Beispiel: user.malte.development.DATA)
        Dafür braucht man folgende Methoden:
        addVocabPackage(VocabPackage vocab, String path):void (Soll auch überschreiben, wenn es schon existiert)
        getVocabPackage(String path):VocabPackage
        getVocabPackagePath(String path):String
        removeVocabPackage(String path):void
        setDirSave(String path):void
        getDirSave():path
     */

}
