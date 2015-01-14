package ru.afomina.tetris;

import ru.afomina.tetris.ui.TetrisPanel;

import javax.swing.*;

/**
 * Created by alexandra on 14.01.15.
 */
public class App {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Tetris");
        mainFrame.setSize(TetrisPanel.WINDOW_WIDTH, TetrisPanel.WINDOW_HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(new TetrisPanel());
        mainFrame.setVisible(true);
    }
}
