package main.java.gui.frame;

import main.java.gui.panel.MenuPanel;
import main.java.gui.panel.VocabPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MenuPanel menupanel;

    private VocabPanel vocabpanel;
    private int panel;

    public MainFrame(){
        this.panel = 0;
        //GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        setSize(1080,720);
        setLocationRelativeTo(null);

        //Menu
        this.menupanel = new MenuPanel(1);
        setTitle(menupanel.getTitle());

        this.vocabpanel = new VocabPanel("");

        //Container
        Container pane = getContentPane();
        //pane.setLayout(null);
        pane.add(menupanel);
        pack();
    }

    public void setVocabpanel(VocabPanel vocabpanel) {
        this.vocabpanel = vocabpanel;
    }

    public void reload(){
        getContentPane().removeAll();
        switch (panel){
            case 0:
                getContentPane().add(menupanel);
                setTitle(menupanel.getTitle());
                break;
            case 1:
                getContentPane().add(vocabpanel);
                setTitle(vocabpanel.getTitle());
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