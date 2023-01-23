package main.java.gui.frame;

import main.java.gui.panel.LearnPanel;
import main.java.gui.panel.MenuPanel;
import main.java.gui.panel.ResultPanel;
import main.java.gui.panel.VocabPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Hauptfenster, wo die ganzen Menüs angezeigt werden.
 */
public class MainFrame extends JFrame {

    /**
     * Hauptmenü, wenn das Programm gestartet wird.
     */
    private MenuPanel menupanel;

    /**
     * In dem Vokabelmenü können Vokabeln erstellt oder gelöscht werden
     * für einen bestimmten Karteikasten.
     */
    private VocabPanel vocabpanel;

    /**
     * Ein Menü zum Lernen, dabei werden 15 Vokabeln abgefragt.
     */
    private LearnPanel learnpanel;

    /**
     * Das Resultat, nachdem die 15 Vokabeln abgefragt wurden.
     */
    private ResultPanel resultPanel;

    /**
     * Die Variable soll darstellen, in welchem Zustand das Hauptfenster sich befindet.
     */
    private int panel;

    /**
     * Das Hauptfenster besitzt eine 1080 x 720 Auflösung, die nicht skalierbar ist.
     */
    public MainFrame(){
        this.panel = 0;
        //GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1080,720);
        setLocationRelativeTo(null);

        //Menu
        this.menupanel = new MenuPanel(1);
        setTitle(menupanel.getTitle());

        //Container
        Container pane = getContentPane();
        pane.add(menupanel);
        pack();
    }

    public VocabPanel getVocabpanel() {
        return vocabpanel;
    }

    public void setVocabpanel(VocabPanel vocabpanel) {
        this.vocabpanel = vocabpanel;
    }

    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public void setLearnpanel(LearnPanel learnpanel) {
        this.learnpanel = learnpanel;
    }

    /**
     * Es gibt verschiedene Modis, die das Hauptfenster annehmen kann:
     * Modus 0: Das Hauptmenü, Modus 1: Das Vokabelmenü, Modul2: Der Prüfungsmodus,
     * Modul 3: Ergebnis der Prüfungsmodus.
     */
    public void reload(){
        getContentPane().removeAll();
        switch (panel){
            case 0:
                getContentPane().add(menupanel);
                setTitle(menupanel.getTitle());
                break;
            case 1:
                getContentPane().add(vocabpanel);
                setTitle(vocabpanel.getVocabpackage().getName());
                break;
            case 2:
                getContentPane().add(learnpanel);
                setTitle(learnpanel.getTitle());
                break;
            case 3:
                getContentPane().add(resultPanel);
                setTitle("Ergebnisse");
                break;
        }
        pack();
        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public MenuPanel getMenupanel() {
        return menupanel;
    }

    public int getPanel() {
        return panel;
    }

    public void setPanel(int panel) {
        this.panel = panel;
    }
}