package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Tetris {

    public static void test1() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);

            terminal.setForegroundColor(TextColor.ANSI.WHITE);
            terminal.setBackgroundColor(TextColor.ANSI.BLACK);

            terminal.setCursorPosition(10, 10);
            terminal.putCharacter('W');
            terminal.putCharacter('i');
            terminal.putCharacter('t');
            terminal.putCharacter('a');
            terminal.putCharacter('m');
            terminal.putCharacter(' ');
            terminal.putCharacter('s');
            terminal.putCharacter('t');
            terminal.putCharacter('u');
            terminal.putCharacter('d');
            terminal.putCharacter('e');
            terminal.putCharacter('n');
            terminal.putCharacter('t');
            terminal.putCharacter('a');
            terminal.putCharacter('!');

            terminal.flush();

            // Ожидание нажатия клавиши
            while (true) {
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke != null) {
                    break;
                }
                Thread.sleep(100);
            }

            terminal.exitPrivateMode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        try {
            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();

            TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
            textGraphics.putString(4, 4, "Witam studenta ponownie!");

            screen.refresh();

            // Ожидание нажатия клавиши
            while (true) {
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null) {
                    break;
                }
                Thread.sleep(100);
            }

            screen.stopScreen();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        try {
            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();

            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
            BasicWindow window = new BasicWindow("Sample window");
            window.setHints(java.util.Collections.singletonList(Window.Hint.FULL_SCREEN));

            Panel panelHolder = new Panel();
            panelHolder.setLayoutManager(new LinearLayout(Direction.VERTICAL));

            Panel panel = new Panel();
            panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
            panel.addComponent(new Label("Panel with a right-aligned button"));

            Button button = new Button("Button", () -> System.out.println("Button Pressed"));
            panel.addComponent(button);

            // Замена Table на GridLayout для создания таблицы
            Panel tablePanel = new Panel(new GridLayout(6));
            tablePanel.addComponent(new Label("Field-1----"));
            tablePanel.addComponent(new Label("Field-2"));
            tablePanel.addComponent(new Label("Field-3"));
            tablePanel.addComponent(new Label("Field-4"));
            tablePanel.addComponent(new Label("Field-5"));
            tablePanel.addComponent(new Label("Field-6"));
            panel.addComponent(tablePanel);

            panelHolder.addComponent(panel);
            window.setComponent(panelHolder);

            Button quitButton = new Button("Show next window", () -> {
                BasicWindow newWindow = new BasicWindow("This window should be of the same size as the previous one");
                newWindow.setHints(java.util.Collections.singletonList(Window.Hint.FULL_SCREEN));

                Button exitBtn = new Button("Exit", newWindow::close);
                newWindow.setComponent(exitBtn);

                textGUI.addWindowAndWait(newWindow);
            });

            window.setComponent(new Panel().addComponent(quitButton));
            textGUI.addWindowAndWait(window);

            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test1();
        // test2();
        // test3();
    }
}
