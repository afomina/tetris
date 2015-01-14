package ru.afomina.tetris.ui;

import ru.afomina.tetris.figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by alexandra on 14.01.15.
 */
public class TetrisPanel extends JPanel implements ActionListener {

    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 400;
    private static final int FIGURE_X = WINDOW_WIDTH / 2;
    private static int FIGURE_Y = 0;
    public static final int ONE_CUBE_SIZE = 5;

    private Figure figure = new Figure();

    public TetrisPanel() {
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("right");
                    figure.right();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("left");
                    figure.left();
                }
                figure.move();
                reload();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        /*ActionListener fallListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("fall!");
                figure.down();
                reload();
            }

        };
        Timer fallTimer = new Timer(500, fallListener);
        fallTimer.start();*/
        Timer timer = new Timer(500, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        figure.paint(FIGURE_X, FIGURE_Y, graphics2D);
    }

    public void reload() {
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FIGURE_Y += ONE_CUBE_SIZE;
        if (FIGURE_Y > WINDOW_HEIGHT) {
            FIGURE_Y = WINDOW_HEIGHT;
        }
        reload();
    }
}
