package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void inputKey() {
        Model model = new Model();
        View view = new View(null); // Здесь View не используется
        Controller controller = new Controller(model, view);

        // Тест нажатия пробела, когда игра окончена
        model.resetGame();
        model.setScore(1000);
        model.Down(); // Переводим игру в состояние, близкое к завершению
        while (!model.GameOver()) {
            model.Down();
        }
        KeyStroke restartKey = new KeyStroke(' ', false, false); // Используем конструктор для символов
        controller.InputKey(restartKey);
        assertFalse(model.GameOver(), "Игра должна перезапуститься при нажатии пробела после окончания игры.");

        // Тест нажатия клавиши влево
        KeyStroke leftKey = new KeyStroke(KeyType.ArrowLeft, false, false); // Для специальных клавиш
        int initialX = model.getPieceX();
        controller.InputKey(leftKey);
        assertEquals(initialX - 1, model.getPieceX(), "Фигура должна сдвинуться влево при нажатии клавиши влево.");

        // Тест нажатия клавиши вправо
        KeyStroke rightKey = new KeyStroke(KeyType.ArrowRight, false, false);
        initialX = model.getPieceX();
        controller.InputKey(rightKey);
        assertEquals(initialX + 1, model.getPieceX(), "Фигура должна сдвинуться вправо при нажатии клавиши вправо.");

        // Тест нажатия клавиши вниз
        KeyStroke downKey = new KeyStroke(KeyType.ArrowDown, false, false);
        int initialY = model.getPieceY();
        controller.InputKey(downKey);
        assertEquals(initialY + 1, model.getPieceY(), "Фигура должна сдвинуться вниз при нажатии клавиши вниз.");

        // Тест нажатия клавиши вверх (поворот)
        KeyStroke upKey = new KeyStroke(KeyType.ArrowUp, false, false);
        controller.InputKey(upKey); // Эта команда должна поворачивать фигуру
        // Проверка зависит от вашей реализации поворота
    }

    @Test
    void updateView() {
        Model model = new Model();
        ViewMock viewMock = new ViewMock(); // Mock-объект для View
        Controller controller = new Controller(model, viewMock);

        // Проверяем, что метод Board вызван
        controller.updateView();
        assertTrue(viewMock.wasBoardCalled(), "Метод updateView должен вызывать метод Board у View.");
    }

    // Mock-класс для View
    static class ViewMock extends View {
        private boolean boardCalled = false;

        public ViewMock() {
            super(null);
        }

        @Override
        public void Board(Model model) {
            boardCalled = true;
        }

        public boolean wasBoardCalled() {
            return boardCalled;
        }
    }
}
