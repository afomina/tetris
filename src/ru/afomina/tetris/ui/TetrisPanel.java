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
    private int figureX = FIELD_X + FIELD_WIDTH / 2 - ONE_CUBE_SIZE;
    private int figureY = 0;

    private boolean[][] game = new boolean[GAME_HEIGHT + 2][GAME_WIDTH + 2];

    private Timer timer;

    public TetrisPanel() {
        for (int i = 0; i < GAME_WIDTH + 2; i ++) {
            game[0][i] = true;
            game[GAME_HEIGHT + 1][i] = true;
        }
//        for (int i = 0; i < GAME_HEIGHT; i++) {
//            game[i][0] = true;
//            game[i][GAME_WIDTH + 1] = true;
//        }

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
                        if (figureX + ONE_CUBE_SIZE <= FIELD_X + FIELD_WIDTH - figure.getWidth() * ONE_CUBE_SIZE) {
                            figureX += ONE_CUBE_SIZE;
                        }
                        break;
                    case  KeyEvent.VK_LEFT :
                        if (figureX - ONE_CUBE_SIZE >= FIELD_X) {
                            figureX -= ONE_CUBE_SIZE;
                        }
                        break;
//                    case KeyEvent.VK_UP :
//                        figure.rotate();
//                        break;
//                    case KeyEvent.VK_DOWN :
//                        figureY += ONE_CUBE_SIZE;
//                        checkCollision();
//                        break;
                }
                reload();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        timer = new Timer(SPEED, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawBorder(graphics2D);
        g.setColor(Color.MAGENTA);
        if (!timer.isRunning()) {
            g.drawString("GAME OVER", FIELD_X + FIELD_WIDTH / 5, FIELD_Y + FIELD_HEIGHT / 2);
        } else {
            for (int y = 1; y < GAME_HEIGHT; y++) {
                for (int x = 1; x < GAME_WIDTH; x++) {
                    if (game[y][x]) {
                        g.fillRect(FIELD_X + x * ONE_CUBE_SIZE, FIELD_Y + y * ONE_CUBE_SIZE, ONE_CUBE_SIZE, ONE_CUBE_SIZE);
                    }
                }
            }
            figure.paint(figureX, figureY, graphics2D);
        }
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
        if (checkCollision()) {
            figureY -= ONE_CUBE_SIZE;

            for (int y = 0; y < 2; y++) {
                for (int x = 0; x < 4; x++) {
                    if (figure.getPosition()[y][x]) {
                        game[(figureY - FIELD_Y) / ONE_CUBE_SIZE + y][(figureX - FIELD_X) / ONE_CUBE_SIZE + x] = true;
                    }
                }
            }
            newFigure();
        }
        reload();
    }

    private void newFigure() {
        figure = new Figure();
        figureX = FIELD_X + FIELD_WIDTH / 2 - ONE_CUBE_SIZE;
        figureY = 0;
    }

    private boolean checkCollision() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 4; x++) {
                int gameY = (figureY - FIELD_Y) / ONE_CUBE_SIZE + y;
                if (figure.getPosition()[y][x] && game[gameY][(figureX - FIELD_X) / ONE_CUBE_SIZE + x]) {
                    if (gameY == 1) {
                        gameOver();
                    } else if (figureY > FIELD_Y + FIELD_HEIGHT - ONE_CUBE_SIZE) {
                        figureY = FIELD_Y + FIELD_HEIGHT - ONE_CUBE_SIZE;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void gameOver() {
        timer.stop();
    }
}
