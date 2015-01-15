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

    public void paint(final int x, final int y, Graphics2D g) {
        this.x = x;
        this.y = y;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
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
        if (x + dx < TetrisPanel.FIELD_WIDTH && x + dx >= 0) {
            x += dx;
        }
//        if (y + dy < TetrisPanel.FIELD_HEIGHT) {
//            y += dy;
//        }
        dx = 0;
        dy = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
