package ru.afomina.tetris.figure;

import ru.afomina.tetris.ui.TetrisPanel;

import java.awt.*;

/**
 * Created by alexandra on 14.01.15.
 */
public class Figure {
    private int x;
    private int y;
    private int width = 4 * TetrisPanel.ONE_CUBE_SIZE;
    private int height = TetrisPanel.ONE_CUBE_SIZE;
    private int dx = 0;
    private int dy = 0;

    public void paint(int x, int y, Graphics2D g) {
        this.x = x;
        this.y = y;
        g.drawRect(x, y, width, height);
    }

    public void right() {
        dx += TetrisPanel.ONE_CUBE_SIZE;
    }

    public void left() {
        dx -= TetrisPanel.ONE_CUBE_SIZE;
    }

    public void down() {
        dy += TetrisPanel.ONE_CUBE_SIZE;
    }

    public void move() {
        System.out.println("move!");
        if (x + dx < TetrisPanel.WINDOW_WIDTH && x + dx >= 0) {
            x += dx;
        }
//        if (y + dy < TetrisPanel.WINDOW_HEIGHT) {
//            y += dy;
//        }
        dx = 0;
        dy = 0;
    }

}
