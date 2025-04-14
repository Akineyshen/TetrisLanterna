package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void inputKey() {
        Model model = new Model();
        View view = new View(null);
        Controller controller = new Controller(model, view);

        model.resetGame();
        model.setScore(1000);
        model.down();
        while (!model.gameOver()) {
            model.down();
        }
        KeyStroke restartKey = new KeyStroke(' ', false, false);
        controller.InputKey(restartKey);
        assertFalse(model.gameOver(), "The game should restart when you press the space bar after the end of the game.");

        KeyStroke leftKey = new KeyStroke(KeyType.ArrowLeft, false, false);
        int initialX = model.getPieceX();
        controller.InputKey(leftKey);
        assertEquals(initialX - 1, model.getPieceX(), "The shape should move to the left when you press the left key.");

        KeyStroke rightKey = new KeyStroke(KeyType.ArrowRight, false, false);
        initialX = model.getPieceX();
        controller.InputKey(rightKey);
        assertEquals(initialX + 1, model.getPieceX(), "The shape should move to the right when you press the right key.");

        KeyStroke downKey = new KeyStroke(KeyType.ArrowDown, false, false);
        int initialY = model.getPieceY();
        controller.InputKey(downKey);
        assertEquals(initialY + 1, model.getPieceY(), "The shape should slide down when the down key is pressed.");

        KeyStroke upKey = new KeyStroke(KeyType.ArrowUp, false, false);
        controller.InputKey(upKey);
    }

    @Test
    void updateView() {
        Model model = new Model();
        viewMock viewMock = new viewMock();
        Controller controller = new Controller(model, viewMock);

        controller.updateView();
        assertTrue(viewMock.wasBoardCalled(), "The UpdateView method should call the Board method of the View.");
    }

    static class viewMock extends View {
        private boolean boardCalled = false;

        public viewMock() {
            super(null);
        }

        @Override
        public void board(Model model) {
            boardCalled = true;
        }

        public boolean wasBoardCalled() {
            return boardCalled;
        }
    }
}
