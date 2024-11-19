package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void resetGame() {
        Model model = new Model();
        model.resetGame();
        assertFalse(model.GameOver(), "Игра должна быть не окончена после сброса.");
        assertEquals(0, model.getScore(), "Счет должен быть равен 0 после сброса.");
        assertNotNull(model.getPiece(), "Текущая фигура должна быть инициализирована.");
    }

    @Test
    void down() {
        Model model = new Model();
        model.resetGame();
        int initialY = model.getPieceY();
        assertTrue(model.Down(), "Фигура должна двигаться вниз, если возможно.");
        assertEquals(initialY + 1, model.getPieceY(), "Координата Y должна увеличиться на 1.");
    }

    @Test
    void left() {
        Model model = new Model();
        model.resetGame();
        int initialX = model.getPieceX();
        model.Left();
        assertEquals(initialX - 1, model.getPieceX(), "Координата X должна уменьшиться на 1 после движения влево.");
    }

    @Test
    void right() {
        Model model = new Model();
        model.resetGame();
        int initialX = model.getPieceX();
        model.Right();
        assertEquals(initialX + 1, model.getPieceX(), "Координата X должна увеличиться на 1 после движения вправо.");
    }

    @Test
    void rotate() {
        Model model = new Model();
        model.resetGame();
        char[][] initialPiece = model.getPiece();
        model.rotate();
        assertNotEquals(initialPiece, model.getPiece(), "Фигура должна изменить ориентацию после поворота.");
    }

    @Test
    void place() {
        Model model = new Model();
        model.resetGame();
        model.Place();
        assertFalse(model.Move(model.getPieceX(), model.getPieceY()), "Фигура должна быть размещена на игровом поле.");
    }

    @Test
    void clearLines() {
        Model model = new Model();
        model.resetGame();

        // Заполнить строку для проверки очистки
        char[][] board = model.getBoard();
        for (int x = 0; x < Model.WIDTH; x++) {
            board[Model.HEIGHT - 1][x] = Model.BLOCK;
        }

        int initialScore = model.getScore();
        model.ClearLines();

        assertEquals(initialScore + 100, model.getScore(), "Счет должен увеличиться на 100 после очистки строки.");
    }

    @Test
    void spawnPiece() {
        Model model = new Model();
        model.resetGame();
        model.SpawnPiece();
        assertNotNull(model.getPiece(), "Должна быть сгенерирована новая фигура.");
    }

    @Test
    void generateRandomPiece() {
        Model model = new Model();
        char[][] piece = model.GenerateRandomPiece();
        assertNotNull(piece, "Сгенерированная фигура не должна быть null.");
        assertTrue(piece.length > 0, "Сгенерированная фигура должна иметь размер.");
    }

    @Test
    void dropInterval() {
        Model model = new Model();
        int interval = model.DropInterval();
        assertTrue(interval >= 100, "Интервал падения должен быть больше или равен 100.");
    }

    @Test
    void restartTimer() {
        Model model = new Model();
        model.RestartTimer(model::Down);
        assertNotNull(model.getTime(), "Таймер должен быть запущен.");
    }

    @Test
    void getBoard() {
        Model model = new Model();
        assertNotNull(model.getBoard(), "Игровое поле не должно быть null.");
    }

    @Test
    void getPiece() {
        Model model = new Model();
        assertNotNull(model.getPiece(), "Текущая фигура не должна быть null.");
    }

    @Test
    void getPieceX() {
        Model model = new Model();
        assertTrue(model.getPieceX() >= 0, "Координата X текущей фигуры должна быть неотрицательной.");
    }

    @Test
    void getPieceY() {
        Model model = new Model();
        assertTrue(model.getPieceY() >= 0, "Координата Y текущей фигуры должна быть неотрицательной.");
    }

    @Test
    void getScore() {
        Model model = new Model();
        assertEquals(0, model.getScore(), "Счет должен быть равен 0 после сброса игры.");
    }

    @Test
    void getLevel() {
        Model model = new Model();
        assertEquals(1, model.getLevel(), "Уровень должен быть равен 1 после сброса игры.");
    }

    @Test
    void getNextPiece() {
        Model model = new Model();
        assertNotNull(model.getNextPiece(), "Следующая фигура не должна быть null.");
    }

    @Test
    void gameOver() {
        Model model = new Model();
        assertFalse(model.GameOver(), "Игра не должна быть окончена после сброса.");
    }

    @Test
    void messageGameOver() {
        Model model = new Model();
        assertFalse(model.MessageGameOver(), "Сообщение об окончании игры не должно отображаться после сброса.");
    }

    @Test
    void setScore() {
        Model model = new Model();
        model.setScore(500);
        assertEquals(500, model.getScore(), "Счет должен обновиться.");
    }

    @Test
    void startTimer() {
        Model model = new Model();
        model.startTimer();
        assertNotNull(model.getTime(), "Таймер должен быть запущен.");
    }

    @Test
    void getTime() {
        Model model = new Model();
        model.startTimer();
        String time = model.getTime();
        assertNotNull(time, "Время не должно быть null.");
    }

    @Test
    void stopTimer() {
        Model model = new Model();
        model.resetGame();
        model.startTimer();

        // Устанавливаем завершение игры
        model.Down(); // Двигаем фигуру вниз, пока игра не завершится
        while (!model.GameOver()) {
            model.Down();
        }

        model.stopTimer();

        // Проверяем, что время остановлено
        String gameOverTime = model.getTime();
        try {
            Thread.sleep(100); // Небольшая задержка
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(gameOverTime, model.getTime(), "Время должно оставаться неизменным после остановки таймера.");
    }

}
