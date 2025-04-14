package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void InputKey(KeyStroke keyStroke) {
        if (keyStroke != null) {
            if (model.gameOver() && keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') {
                model.resetGame();
            } else if (!model.gameOver()) {
                if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                    model.left();
                } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                    model.right();
                } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                    model.down();
                } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                    model.rotate();
                }
            }
        }
    }

    public void updateView() {
        try {
            view.board(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
