package main.java.gui.panel;

import main.java.main.Main;
import main.java.utils.Difficulty;
import main.java.vocab.VocabPackage;
import main.java.vocab.Vocabulary;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class LearnPanel extends JPanel {

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

    public LearnPanel(VocabPackage vocabpackage){

        this.randomvocab = vocabpackage.getRandomVocab();

        this.title = "Prüfungsmodus || " + vocabpackage.getName();
        setPreferredSize(new Dimension(1080,720));
        setLayout(null);

        question = new JLabel("Frage 1/15");
        question.setBounds(30,660,100,30);

        streak = new JLabel("0x Streak",SwingConstants.RIGHT);
        streak.setBounds(950,660,100,30);

        key = new JLabel("",SwingConstants.CENTER);
        key.setText("Frage: " + randomvocab.getKey());
        key.setBounds(160,95,300,30);
        key.setBorder(BorderUIResource.getBlackLineBorderUIResource());

        difficulty = new JLabel("",SwingConstants.CENTER);
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
        difficulty.setBounds(390,50,300,30);
        difficulty.setBorder(BorderUIResource.getBlackLineBorderUIResource());

        solution = new JLabel("",SwingConstants.CENTER);
        solution.setName("SOLUTION");
        solution.setBounds(390,190,300,30);

        value = new JTextField();
        value.setBounds(620,95,300,30);

        confirmbutton = new JButton("Bestätigen");
        confirmbutton.setBounds(390, 150,300,30);
        confirmbutton.addActionListener(e -> {
            if (confirmbutton.getText().equalsIgnoreCase("Bestätigen")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < randomvocab.getValue().getLength(); i++) {
                    stringBuilder.append(", ");
                    stringBuilder.append(randomvocab.getValue().getItem(i));
                    if (value.getText().equalsIgnoreCase((String) randomvocab.getValue().getItem(i))) {
                        updateSolution(solution, "Die Antwort ist richtig!");
                        //TODO War es schwierig?
                        Main.mainframe.reload();
                        return;
                    }
                }
                updateSolution(solution, "Falsch, die richtige Antwort wäre: " + stringBuilder.substring(2) + "!");
                confirmbutton.setText("Nächste Vokabel");
                Main.mainframe.reload();
                return;
            }
            randomvocab = vocabpackage.getRandomVocab();
            confirmbutton.setText("Bestätigen");

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
}