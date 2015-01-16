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
    public static final int GAME_WIDTH = 12;
    public static final int GAME_HEIGHT = 20;

    public static final int ONE_CUBE_SIZE = 10;
    public static final int FIELD_WIDTH = ONE_CUBE_SIZE * GAME_WIDTH;
    public static final int FIELD_HEIGHT = ONE_CUBE_SIZE * GAME_HEIGHT;
    public static final int FIELD_X = (WINDOW_WIDTH - FIELD_WIDTH) / 2;
    public static final int FIELD_Y = 0;

    private static final int SPEED = 500;

    private Figure figure = new Figure();
    private int figureX = FIELD_X + FIELD_WIDTH / 2 - figure.getWidth() / 2;
    private int figureY = 0;

    private boolean[][] game = new boolean[GAME_HEIGHT][GAME_WIDTH];

    public TetrisPanel() {
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT :
                        figureX += ONE_CUBE_SIZE;
                        if (figureX > FIELD_X + FIELD_WIDTH - figure.getWidth()) {
                            figureX = FIELD_X + FIELD_WIDTH - figure.getWidth();
                        }
                        break;
                    case  KeyEvent.VK_LEFT :
                        figureX -= ONE_CUBE_SIZE;
                        if (figureX < FIELD_X) {
                            figureX = FIELD_X;
                        }
                        break;
                }
                reload();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        Timer timer = new Timer(SPEED, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawBorder(graphics2D);
        g.setColor(Color.MAGENTA);
        for (int y = 0; y < GAME_HEIGHT; y++) {
            for (int x = 0; x < GAME_WIDTH; x++) {
                if (game[y][x]) {
                    g.fillRect(FIELD_X + x * ONE_CUBE_SIZE, FIELD_Y + y * ONE_CUBE_SIZE, ONE_CUBE_SIZE, ONE_CUBE_SIZE);
                }
            }
        }
        figure.paint(figureX, figureY, graphics2D);
    }

    private void reload() {
        validate();
        repaint();
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int x = FIELD_X + ONE_CUBE_SIZE; x < FIELD_X + FIELD_WIDTH; x += ONE_CUBE_SIZE) {
            g.drawLine(x, FIELD_Y, x, FIELD_Y + FIELD_HEIGHT);
        }
        for (int y = FIELD_Y + ONE_CUBE_SIZE; y < FIELD_Y + FIELD_HEIGHT; y += ONE_CUBE_SIZE) {
            g.drawLine(FIELD_X, y, FIELD_X + FIELD_WIDTH, y);
        }

        g.setColor(Color.BLACK);
        g.drawRect(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        figureY += ONE_CUBE_SIZE;
        if (figureY > FIELD_Y + FIELD_HEIGHT - ONE_CUBE_SIZE) {
            figureY = FIELD_Y + FIELD_HEIGHT - ONE_CUBE_SIZE;

            for (int x = (figureX - FIELD_X) / ONE_CUBE_SIZE; x < (figureX - FIELD_X + figure.getWidth()) / ONE_CUBE_SIZE; x++) {
                game[(figureY - FIELD_Y) / ONE_CUBE_SIZE][x] = true;
            }
            newFigure();
        }
        reload();
    }

    private void newFigure() {
        figure = new Figure();
        figureX = FIELD_X + FIELD_WIDTH / 2 - figure.getWidth() / 2;
        figureY = 0;
    }
}
