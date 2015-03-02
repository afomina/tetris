package ru.afomina.tetris.figure;

import java.awt.*;
import java.util.Random;
import static ru.afomina.tetris.ui.TetrisPanel.*;

/**
 * Created by alexandra on 14.01.15.
 */
public class Figure {

    private enum Type {
        RECTANGLE,
        SQUARE,
        L,
        T,
        Z
    }

    private int x;
    private int y;
    private boolean[][] position = new boolean[2][4];
    private Type type;

    public Figure() {
        Random random = new Random();
        this.type = Type.values()[random.nextInt(Type.values().length)];
        switch (this.type) {
            case RECTANGLE:
                for (int i = 0; i < 4; i++) {
                    position[0][i] = true;
                }
                break;
            case SQUARE:
                for (int j = 0; j < 2; j++) {
                    for (int i = 0; i < 2; i++) {
                        position[j][i] = true;
                    }
                }
                break;
            case L:
                for (int i = 0; i < 3; i++) {
                    position[0][i] = true;
                }
                position[1][0] = true;
                break;
            case T:
                for (int i = 0; i < 3; i++) {
                    position[0][i] = true;
                }
                position[1][1] = true;
                break;
            case Z:
                for (int i = 0; i < 2; i++) {
                    position[0][i] = true;
                }
                for (int i = 1; i < 3; i++) {
                    position[1][i] = true;
                }
                break;
        }
    }

    public void paint(final int x, final int y, Graphics2D g) {
        this.x = x;
        this.y = y;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 2; i ++) {
            for (int j = 0; j < 4; j++) {
                if (position[i][j]) {
                    g.fillRect(x + j * ONE_CUBE_SIZE, y + i * ONE_CUBE_SIZE, ONE_CUBE_SIZE, ONE_CUBE_SIZE);
                }
            }
        }
    }

    public void rotate() {

    }

    public boolean[][] getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            cnt += position[0][i] || position[1][i]? 1 : 0;
        }
        return cnt;
    }
}
