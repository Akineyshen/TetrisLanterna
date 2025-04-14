package org.example;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Model {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final char CELL = '▪';
    public static final char BLOCK = '▣';

    private char[][] board;
    private char[][] currentPiece;
    private char[][] nextPiece;
    private int currentPieceX;
    private int currentPieceY;
    private int score;
    private int level = 1;
    private int linesCleared = 0;

    private Timer timer;
    private final Random random;
    private boolean GameOver;
    private boolean MessageGameOver;

    private long startTime;
    private long endTime = 0;

    public Model() {
        board = new char[HEIGHT][WIDTH];
        random = new Random();
        resetGame();
    }

    public void resetGame() {
        startTimer();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = CELL;
            }
        }
        level = 1;
        GameOver = false;
        MessageGameOver = false;
        nextPiece = generateRandomPiece();
        spawnPiece();
        setScore(0);
    }

    public boolean down() {
        if (GameOver) {
            return false;
        }
        if (move(currentPieceX, currentPieceY + 1)) {
            currentPieceY++;
            return true;
        } else {
            place();
            clearLines();
            spawnPiece();
            updateLevel();
            return false;
        }
    }

    public void left() {
        if (!GameOver && move(currentPieceX - 1, currentPieceY)) {
            currentPieceX--;
        }
    }

    public void right() {
        if (!GameOver && move(currentPieceX + 1, currentPieceY)) {
            currentPieceX++;
        }
    }

    public void rotate() {
        if (GameOver) {
            return;
        }
        char[][] rotatedPiece = new char[currentPiece[0].length][currentPiece.length];
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[0].length; x++) {
                rotatedPiece[x][currentPiece.length - 1 - y] = currentPiece[y][x];
            }
        }
        if (move(currentPieceX, currentPieceY, rotatedPiece)) {
            currentPiece = rotatedPiece;
        }
    }

    public boolean move(int newX, int newY) {
        return move(newX, newY, currentPiece);
    }

    public boolean move(int newX, int newY, char[][] piece) {
        for (int y = 0; y < piece.length; y++) {
            for (int x = 0; x < piece[y].length; x++) {
                if (piece[y][x] == BLOCK) {
                    int boardX = newX + x;
                    int boardY = newY + y;
                    if (boardY < 0 || boardY >= HEIGHT || boardX < 0 || boardX >= WIDTH || board[boardY][boardX] != CELL) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void place() {
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[y].length; x++) {
                if (currentPiece[y][x] == BLOCK) {
                    board[currentPieceY + y][currentPieceX + x] = BLOCK;
                }
            }
        }
        clearLines();
    }

    public void clearLines() {
        for (int y = 0; y < HEIGHT; y++) {
            boolean isFullLine = true;
            for (int x = 0; x < WIDTH; x++) {
                if (board[y][x] == CELL) {
                    isFullLine = false;
                    break;
                }
            }
            if (isFullLine) {
                score += 100;
                linesCleared++;
                for (int newY = y; newY > 0; newY--) {
                    System.arraycopy(board[newY - 1], 0, board[newY], 0, WIDTH);
                }
                for (int x = 0; x < WIDTH; x++) {
                    board[0][x] = CELL;
                }
            }
        }
    }

    public void spawnPiece() {
        currentPiece = copyPiece(nextPiece);
        nextPiece = generateRandomPiece();
        currentPieceX = WIDTH / 2 - currentPiece[0].length / 2;
        currentPieceY = 0;
        if (!move(currentPieceX, currentPieceY)) {
            GameOver = true;
            MessageGameOver = true;
            stopTimer();
        }
    }

    private char[][] copyPiece(char[][] piece) {
        char[][] copy = new char[piece.length][piece[0].length];
        for (int y = 0; y < piece.length; y++) {
            System.arraycopy(piece[y], 0, copy[y], 0, piece[y].length);
        }
        return copy;
    }

    public char[][] generateRandomPiece() {
        switch (random.nextInt(7)) {
            case 0:
                return new char[][]{{BLOCK, BLOCK}, {BLOCK, BLOCK}};
            case 1:
                return new char[][]{{BLOCK, BLOCK, BLOCK, BLOCK}};
            case 2:
                return new char[][]{{CELL, BLOCK, CELL}, {BLOCK, BLOCK, BLOCK}};
            case 3:
                return new char[][]{{BLOCK, CELL}, {BLOCK, CELL}, {BLOCK, BLOCK}};
            case 4:
                return new char[][]{{CELL, BLOCK}, {CELL, BLOCK}, {BLOCK, BLOCK}};
            case 5:
                return new char[][]{{CELL, BLOCK, BLOCK}, {BLOCK, BLOCK, CELL}};
            case 6:
                return new char[][]{{BLOCK, BLOCK, CELL}, {CELL, BLOCK, BLOCK}};
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    private void updateLevel() {
        level = score / 500 + 1;
        if (timer != null && !GameOver) {
            restartTimer(() -> down());
        }
    }

    public int dropInterval() {
        return Math.max(100, 500 - (level - 1) * 50);
    }

    public void restartTimer(Runnable task) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, 0, dropInterval());
    }

    public char[][] getBoard() {
        return board;
    }

    public char[][] getPiece() {
        return currentPiece;
    }

    public int getPieceX() {
        return currentPieceX;
    }

    public int getPieceY() {
        return currentPieceY;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public char[][] getNextPiece() {
        return nextPiece;
    }

    public boolean gameOver() {
        return GameOver;
    }

    public boolean messageGameOver() {
        return MessageGameOver;
    }

    public void setScore(int score) {
        this.score = score;
        updateLevel();
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        endTime = 0;
    }

    public String getTime() {
        long elapsedMillis;
        if (GameOver) {
            elapsedMillis = endTime - startTime;
        } else {
            elapsedMillis = System.currentTimeMillis() - startTime;
        }

        long totalSeconds = elapsedMillis / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void stopTimer() {
        if (GameOver && endTime == 0) {
            endTime = System.currentTimeMillis();
        }
    }

}
