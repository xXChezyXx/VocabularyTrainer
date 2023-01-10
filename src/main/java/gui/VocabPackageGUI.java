package main.java.gui;

import main.java.vocab.VocabPackage;

import javax.swing.*;

public class VocabPackageGUI extends JFrame {

    public VocabPackageGUI(String title, VocabPackage vocabPackage){
        super(title);

        //GUI
        setResizable(false);
        setSize(1080,720);
        setLocationRelativeTo(null);
    }

}
