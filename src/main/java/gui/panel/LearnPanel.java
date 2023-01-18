package main.java.gui.panel;

import main.java.main.Main;
import main.java.utils.Difficulty;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class LearnPanel extends JPanel {

    private int maxstreakcount;
    private int rightcount;
    private int wrongcount;
    private int hardcount;
    private int questioncount;
    private int streakcount;
    private Vocabulary randomvocab;
    private String title;
    private JLabel key;
    private JLabel difficulty;
    private JLabel solution;
    private JLabel streak;
    private JLabel question;
    private JTextField value;
    private JButton homebutton;
    private JButton confirmbutton;
    private JButton yesbutton;
    private JButton nobutton;

    public LearnPanel(VocabPackage vocabpackage){

        maxstreakcount = 0;
        rightcount = 0;
        wrongcount = 0;
        hardcount = 0;
        streakcount = 0;
        questioncount = 1;

        this.randomvocab = vocabpackage.getRandomVocab();

        this.title = "Prüfungsmodus || " + vocabpackage.getName();
        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        question = new JLabel();
        question.setBounds(30,660,100,30);

        streak = new JLabel("",SwingConstants.RIGHT);
        streak.setBounds(950,660,100,30);

        key = new JLabel("",SwingConstants.CENTER);
        key.setText("Frage: " + randomvocab.getKey());
        key.setBounds(160,95,300,30);
        key.setBorder(BorderUIResource.getBlackLineBorderUIResource());

        difficulty = new JLabel("",SwingConstants.CENTER);
        difficulty.setBounds(390,50,300,30);
        difficulty.setBorder(BorderUIResource.getBlackLineBorderUIResource());

        solution = new JLabel("",SwingConstants.CENTER);
        solution.setName("SOLUTION");
        solution.setBounds(390,190,300,30);

        value = new JTextField();
        value.setBounds(620,95,300,30);

        yesbutton = new JButton("Ja");
        yesbutton.setName("YES");
        yesbutton.setBounds(390, 150,150,30);
        yesbutton.setVisible(false);
        yesbutton.addActionListener(e -> {
            randomvocab.setDifficulty(Difficulty.HARD);
            randomvocab = vocabpackage.getRandomVocab();
            confirmbutton.setVisible(true);
            yesbutton.setVisible(false);
            nobutton.setVisible(false);
            hardcount++;
            updatePanel();
            Main.mainframe.reload();
        });

        nobutton = new JButton("Nein");
        nobutton.setName("NO");
        nobutton.setBounds(540, 150,150,30);
        nobutton.setVisible(false);
        nobutton.addActionListener(e -> {
            randomvocab.setDifficulty(Difficulty.RICHTIG);
            randomvocab = vocabpackage.getRandomVocab();
            confirmbutton.setVisible(true);
            yesbutton.setVisible(false);
            nobutton.setVisible(false);
            rightcount++;
            updateButtons(yesbutton,nobutton);
            updatePanel();
            Main.mainframe.reload();
        });


        confirmbutton = new JButton("Bestätigen");
        confirmbutton.setBounds(390, 150,300,30);
        confirmbutton.addActionListener(e -> {
            if (confirmbutton.getText().equalsIgnoreCase("Bestätigen")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < randomvocab.getValue().getLength(); i++) {
                    stringBuilder.append(", ");
                    stringBuilder.append(randomvocab.getValue().getItem(i));
                    if (value.getText().equalsIgnoreCase((String) randomvocab.getValue().getItem(i))) {
                        updateSolution(solution, "Die Antwort ist richtig! War es schwer?");
                        confirmbutton.setVisible(false);
                        yesbutton.setVisible(true);
                        nobutton.setVisible(true);
                        questioncount++;
                        streakcount++;
                        if (streakcount > maxstreakcount){
                            maxstreakcount = streakcount;
                        }
                        updateButtons(yesbutton,nobutton);
                        Main.mainframe.reload();
                        return;
                    }
                }
                updateSolution(solution, "Falsch, die richtige Antwort wäre: \"" + stringBuilder.substring(2) + "\"!");
                if(questioncount > 15){
                    confirmbutton.setText("Ergebnisse ansehen");
                }else {
                    confirmbutton.setText("Nächste Vokabel");
                }
                Main.mainframe.reload();
                return;
            }
            randomvocab.setDifficulty(Difficulty.FALSCH);
            randomvocab = vocabpackage.getRandomVocab();
            questioncount++;
            streakcount = 0;
            wrongcount++;
            updatePanel();
            Main.mainframe.reload();
        });

        homebutton = new JButton("Zurück zum Menu");
        homebutton.setBounds(905,10,165,30);
        homebutton.setFocusPainted(false);
        homebutton.addActionListener(e -> {
            Main.mainframe.setPanel(0);
            Main.mainframe.reload();
        });

        add(key);
        add(value);
        add(difficulty);
        add(solution);
        add(streak);
        add(question);
        add(homebutton);
        add(confirmbutton);
        updatePanel();
    }

    public String getTitle() {
        return title;
    }

    public void updateSolution(JLabel solution, String text){
        for (int i = 0;i < getComponents().length;i++){
            try {
                if (getComponent(i).getName().equalsIgnoreCase("SOLUTION")) {
                    remove(i);
                    break;
                }
            }catch (NullPointerException ignored){}
        }
        solution.setText(text);
        add(solution);
    }

    public void updatePanel(){
        if(questioncount > 15){
            Main.mainframe.setResultPanel(new ResultPanel(maxstreakcount,rightcount,wrongcount,hardcount));
            Main.mainframe.setPanel(3);
            Main.mainframe.reload();
            return;
        }
        switch (randomvocab.getDifficulty()){
            case UNDEFINED:
                difficulty.setText("Schwierigkeit: Noch nicht gelernt");
                break;
            case RICHTIG:
                difficulty.setText("Schwierigkeit: Einfach");
                break;
            case HARD:
                difficulty.setText("Schwierigkeit: Mittel");
                break;
            case FALSCH:
                difficulty.setText("Schwierigkeit: Schwer");
                break;
        }
        key.setText("Frage: " + randomvocab.getKey());
        solution.setText("");
        value.setText("");
        question.setText("Frage "+questioncount+"/15");
        streak.setText(streakcount+"x Streak");
        confirmbutton.setText("Bestätigen");
    }

    public void updateButtons(JButton yes, JButton no){
        for (int i = 0;i < getComponents().length;i++){
            try {
                if (getComponent(i).getName().equalsIgnoreCase("YES") || getComponent(i).getName().equalsIgnoreCase("NO")) {
                    remove(i);
                    break;
                }
            }catch (NullPointerException ignored){}
        }
        add(yes);
        add(no);
    }
}