package main.java.data;

import com.google.gson.*;
import main.java.main.Main;
import main.java.utils.Difficulty;
import main.java.utils.DynArray;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import java.io.*;
import java.util.Scanner;

public class FileConfiguration {

    private final File file;

    public FileConfiguration(String file) {
        this.file = new File(file);
        if (!getFile().exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveVocabPackage() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(this.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < Main.vocabpackagelist.getLength(); i++) {
            VocabPackage vocabPackage = (VocabPackage) Main.vocabpackagelist.getItem(i);
            JsonObject jsonPackage = new JsonObject();
            jsonPackage.addProperty("name",vocabPackage.getName());
            JsonArray jsonvocabs = new JsonArray();
            for (int j = 0;j < vocabPackage.getVocablist().getLength(); j++){
                Vocabulary vocabulary = ((Vocabulary) vocabPackage.getVocablist().getItem(j));
                //Frage und Schwierigkeit hinzufügen
                JsonObject vocab = new JsonObject();
                vocab.addProperty("key",vocabulary.getKey());
                vocab.addProperty("difficulty",vocabulary.getDifficulty().toString());
                //Antwort hinzufügen
                JsonArray answers = new JsonArray();
                for(int k = 0;k < ((Vocabulary) vocabPackage.getVocablist().getItem(j)).getValue().getLength(); k++){
                    String answer = (String) ((Vocabulary) vocabPackage.getVocablist().getItem(j)).getValue().getItem(k);
                    answers.add(answer);
                }
                vocab.add("value",answers);
                jsonvocabs.add(vocab);
            }
            jsonPackage.add("vocablist",jsonvocabs);
            jsonArray.add(jsonPackage);
        }
        String beautifier = new GsonBuilder().setPrettyPrinting().create().toJson(jsonArray);
        try {
            fileWriter.write(beautifier);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DynArray loadVocablist(){
        StringBuilder content = new StringBuilder();
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()){
                content.append(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonArray jsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
        DynArray allvocabpackages = new DynArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            VocabPackage vocabPackage = new VocabPackage(new DynArray(),"");
            vocabPackage.setName(jsonArray.get(i).getAsJsonObject().get("name").getAsString());
            DynArray vocabs = new DynArray();
            JsonArray jsonvocabs = jsonArray.get(i).getAsJsonObject().get("vocablist").getAsJsonArray();

            for (int j = 0; j < jsonvocabs.size(); j++) {
                Vocabulary vocab = new Vocabulary("",new DynArray());
                vocab.setKey(jsonvocabs.get(j).getAsJsonObject().get("key").getAsString());
                vocab.setDifficulty(Difficulty.valueOf(jsonvocabs.get(j).getAsJsonObject().get("difficulty").getAsString()));
                DynArray values = new DynArray();
                JsonArray jsonvalues = jsonvocabs.get(j).getAsJsonObject().get("value").getAsJsonArray();

                for (int k = 0; k < jsonvalues.size(); k++) {
                    values.append(jsonvalues.get(k).getAsString());
                }
                vocab.setValue(values);
                vocabs.append(vocab);
            }
            vocabPackage.setVocablist(vocabs);
            allvocabpackages.append(vocabPackage);
        }
        return allvocabpackages;
    }

    public File getFile() {
        return file;
    }

}
