package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class Tetris {
    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(100, 35));

            Screen screen = terminalFactory.createScreen();
            screen.startScreen();

            Model model = new Model();
            View view = new View(screen);
            Controller controller = new Controller(model, view);

            model.restartTimer(() -> {
                if (!model.gameOver()) {
                    model.down();
                    controller.updateView();
                }
            });


            while (true) {
                controller.updateView();
                controller.InputKey(screen.pollInput());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
