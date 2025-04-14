package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void resetGame() {
        // Given
        Model model = new Model();

        // When
        model.resetGame();

        // Then
        assertFalse(model.gameOver(), "The game should not be over after the reset.");
        assertEquals(0, model.getScore(), "The score should be 0 after the reset.");
        assertNotNull(model.getPiece(), "The current shape must be initialized.");
    }

    @Test
    void down() {
        Model model = new Model();
        model.resetGame();
        int initialY = model.getPieceY();
        assertTrue(model.down(), "The figure should move downwards if possible.");
        assertEquals(initialY + 1, model.getPieceY(), "The Y coordinate should increase by 1.");
    }

    @Test
    void left() {
        Model model = new Model();
        model.resetGame();
        int initialX = model.getPieceX();
        model.left();
        assertEquals(initialX - 1, model.getPieceX(), "The X coordinate should decrease by 1 after moving to the left.");
    }

    @Test
    void right() {
        Model model = new Model();
        model.resetGame();
        int initialX = model.getPieceX();
        model.right();
        assertEquals(initialX + 1, model.getPieceX(), "The X coordinate should increase by 1 after moving to the right.");
    }

    @Test
    void rotate() {
        Model model = new Model();
        model.resetGame();
        char[][] initialPiece = model.getPiece();
        model.rotate();
        assertNotEquals(initialPiece, model.getPiece(), "The shape should change orientation after rotation.");
    }

    @Test
    void place() {
        Model model = new Model();
        model.resetGame();
        model.place();
        assertFalse(model.move(model.getPieceX(), model.getPieceY()), "The piece must be placed on the playing field.");
    }

    @Test
    void clearLines() {
        Model model = new Model();
        model.resetGame();

        char[][] board = model.getBoard();
        for (int x = 0; x < Model.WIDTH; x++) {
            board[Model.HEIGHT - 1][x] = Model.BLOCK;
        }

        int initialScore = model.getScore();
        model.clearLines();

        assertEquals(initialScore + 100, model.getScore(), "The score should increase by 100 after clearing the row.");
    }

    @Test
    void spawnPiece() {
        Model model = new Model();
        model.resetGame();
        model.spawnPiece();
        assertNotNull(model.getPiece(), "A new shape must be generated.");
    }

    @Test
    void generateRandomPiece() {
        Model model = new Model();
        char[][] piece = model.generateRandomPiece();
        assertNotNull(piece, "The generated shape must not be null.");
        assertTrue(piece.length > 0, "The generated shape must have a size.");
    }

    @Test
    void dropInterval() {
        Model model = new Model();
        int interval = model.dropInterval();
        assertTrue(interval >= 100, "The drop interval must be greater than or equal to 100.");
    }

    @Test
    void restartTimer() {
        Model model = new Model();
        model.restartTimer(model::down);
        assertNotNull(model.getTime(), "The timer should be running.");
    }

    @Test
    void getBoard() {
        Model model = new Model();
        assertNotNull(model.getBoard(), "The playing field must not be null.");
    }

    @Test
    void getPiece() {
        Model model = new Model();
        assertNotNull(model.getPiece(), "The current shape must not be null.");
    }

    @Test
    void getPieceX() {
        Model model = new Model();
        assertTrue(model.getPieceX() >= 0, "The X coordinate of the current shape must be non-negative.");
    }

    @Test
    void getPieceY() {
        Model model = new Model();
        assertTrue(model.getPieceY() >= 0, "The Y coordinate of the current shape must be non-negative.");
    }

    @Test
    void getScore() {
        Model model = new Model();
        assertEquals(0, model.getScore(), "The score should be 0 after resetting the game.");
    }

    @Test
    void getLevel() {
        Model model = new Model();
        assertEquals(1, model.getLevel(), "The level should be equal to 1 after resetting the game.");
    }

    @Test
    void getNextPiece() {
        Model model = new Model();
        assertNotNull(model.getNextPiece(), "The next shape should not be null.");
    }

    @Test
    void gameOver() {
        Model model = new Model();
        assertFalse(model.gameOver(), "The game should not be over after the reset.");
    }

    @Test
    void messageGameOver() {
        Model model = new Model();
        assertFalse(model.messageGameOver(), "The end-of-game message should not be displayed after the reset.");
    }

    @Test
    void setScore() {
        Model model = new Model();
        model.setScore(500);
        assertEquals(500, model.getScore(), "The account must be updated.");
    }

    @Test
    void startTimer() {
        Model model = new Model();
        model.startTimer();
        assertNotNull(model.getTime(), "The timer should be running.");
    }

    @Test
    void getTime() {
        Model model = new Model();
        model.startTimer();
        String time = model.getTime();
        assertNotNull(time, "The time must not be null.");
    }

    @Test
    void stopTimer() {
        Model model = new Model();
        model.resetGame();
        model.startTimer();

        model.down();
        while (!model.gameOver()) {
            model.down();
        }

        model.stopTimer();

        String gameOverTime = model.getTime();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(gameOverTime, model.getTime(), "The time should remain the same after the timer stops.");
    }

}
