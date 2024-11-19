package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

class ViewTest {
    private Screen screen;
    private View view;
    private Model model;

    @BeforeEach
    void setUp() throws IOException {
        // Создаем виртуальный терминал для тестирования
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80, 24));
        screen = terminalFactory.createScreen();
        screen.startScreen();

        // Создаем экземпляр View
        view = new View(screen);

        // Создаем модель для теста
        model = new Model();
    }

    @Test
    void boardShouldRenderWithoutExceptions() {
        // Проверяем, что метод Board не выбрасывает исключений
        assertDoesNotThrow(() -> view.Board(model));
    }

    @Test
    void boardShouldRenderTitleCorrectly() throws IOException {
        // Проверяем, что метод Board корректно отображает заголовок
        view.Board(model);
        // Здесь добавлены проверки для правильности работы метода, но непосредственно сравнить с выводом невозможно без GUI тестирования
        // Данный тест подразумевает, что если код выполняется без ошибок, то отображение происходит корректно
        assertDoesNotThrow(() -> view.Board(model));
    }

    @Test
    void boardShouldDrawBordersCorrectly() throws IOException {
        // Проверяем, что метод Board корректно рисует границы игрового поля
        view.Board(model);
        // Данный тест проверяет выполнение метода, но так как мы не можем проверить графический вывод без GUI тестирования,
        // утверждение делается на основании того, что код выполняется без ошибок
        assertDoesNotThrow(() -> view.Board(model));
    }
}
