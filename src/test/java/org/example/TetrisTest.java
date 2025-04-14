package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TetrisTest {

    @Test
    void testMainInitialization() {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(100, 35));

            Screen screen = terminalFactory.createScreen();
            assertNotNull(screen, "Screen should be initialized successfully");

            Model model = new Model();
            View view = new View(screen);
            Controller controller = new Controller(model, view);

            assertNotNull(model, "Model should be initialized");
            assertNotNull(view, "View should be initialized");
            assertNotNull(controller, "Controller should be initialized");

        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "An IOException should not occur during main initialization");
        }
    }
}
