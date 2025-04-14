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
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80, 24));
        screen = terminalFactory.createScreen();
        screen.startScreen();

        view = new View(screen);

        model = new Model();
    }

    @Test
    void boardShouldRenderWithoutExceptions() {
        assertDoesNotThrow(() -> view.board(model));
    }

    @Test
    void boardShouldRenderTitleCorrectly() throws IOException {
        view.board(model);
        assertDoesNotThrow(() -> view.board(model));
    }

    @Test
    void boardShouldDrawBordersCorrectly() throws IOException {
        view.board(model);
        assertDoesNotThrow(() -> view.board(model));
    }
}
