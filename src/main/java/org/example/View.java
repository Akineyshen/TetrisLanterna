package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TerminalSize;
import java.io.IOException;

public class View {
    private final Screen screen;

    public View(Screen screen) {
        this.screen = screen;
    }

    public void board(Model model) throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.BLACK);
        tg.setForegroundColor(TextColor.ANSI.WHITE);

        TerminalSize terminalSize = screen.getTerminalSize();
        int startX = (terminalSize.getColumns() - Model.WIDTH - 4) / 2;
        int startY = (terminalSize.getRows() - Model.HEIGHT - 2) / 2;

        String[] title = {
                "  _______ ______ _______ _____  _____  _____ ",
                " |__   __|  ____|__   __|  __ \\|_   _|/ ____|",
                "    | |  | |__     | |  | |__) | | | | (___  ",
                "    | |  |  __|    | |  |  _  /  | |  \\___ \\ ",
                "    | |  | |____   | |  | | \\ \\ _| |_ ____) |",
                "    |_|  |______|  |_|  |_|  \\_\\_____|_____/ "
        };

        String[] gameOverArt = {
                "   _____          __  __ ______     ______      ________ _____  ",
                "  / ____|   /\\   |  \\/  |  ____|   / __ \\ \\    / /  ____|  __ \\ ",
                " | |  __   /  \\  | \\  / | |__     | |  | \\ \\  / /| |__  | |__) |",
                " | | |_ | / /\\ \\ | |\\/| |  __|    | |  | |\\ \\/ / |  __| |  _  / ",
                " | |__| |/ ____ \\| |  | | |____   | |__| | \\  /  | |____| | \\ \\ ",
                "  \\_____/_/    \\_\\_|  |_|______|   \\____/   \\/   |______|_|  \\_\\"
        };


        int titleStartX = (terminalSize.getColumns() - title[0].length()) / 2;
        for (int i = 0; i < title.length; i++) {
            tg.putString(titleStartX, i + 1, title[i], SGR.BOLD);
        }

        startY += title.length - 2;

        for (int y = 0; y <= Model.HEIGHT + 1; y++) {
            for (int x = 0; x <= Model.WIDTH + 1; x++) {
                int drawX = startX + x;
                int drawY = startY + y;
                if (y == 0 || y == Model.HEIGHT + 1 || x == 0 || x == Model.WIDTH + 1) {
                    tg.putString(drawX, drawY, "█", SGR.BOLD);
                }
            }
        }

        char[][] board = model.getBoard();
        for (int y = 0; y < Model.HEIGHT; y++) {
            for (int x = 0; x < Model.WIDTH; x++) {
                int drawX = startX + 1 + x;
                int drawY = startY + 1 + y;
                tg.putString(drawX, drawY, String.valueOf(board[y][x]), SGR.BOLD);
            }
        }

        char[][] currentPiece = model.getPiece();
        int pieceX = model.getPieceX();
        int pieceY = model.getPieceY();
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[y].length; x++) {
                if (currentPiece[y][x] == Model.BLOCK) {
                    int drawX = startX + 1 + pieceX + x;
                    int drawY = startY + 1 + pieceY + y;
                    tg.putString(drawX, drawY, String.valueOf(Model.BLOCK), SGR.BOLD);
                }
            }
        }

        int nextPieceHeight = model.getNextPiece().length;
        int nextPieceWidth = model.getNextPiece()[0].length;

        for (int y = 0; y < nextPieceHeight + 4; y++) {
            for (int x = 0; x < nextPieceWidth + 4; x++) {
                tg.putString(startX + Model.WIDTH + 6 + x, startY + 1 + y, " ");
            }
        }

        char[][] nextPiece = model.getNextPiece();
        tg.putString(startX + Model.WIDTH + 5, startY, "Next:", SGR.BOLD);
        for (int y = 0; y < nextPiece.length; y++) {
            for (int x = 0; x < nextPiece[y].length; x++) {
                if (nextPiece[y][x] == Model.BLOCK) {
                    tg.putString(startX + Model.WIDTH + 6 + x, startY + 2 + y, String.valueOf(Model.BLOCK), SGR.BOLD);
                }
            }
        }

        tg.putString(startX - 9, startY, "Score:",  SGR.BOLD);
        tg.putString(startX - 9, startY + 1, "     ");
        tg.putString(startX - 9, startY + 1, String.valueOf(model.getScore()), SGR.BOLD);

        tg.putString(startX - 9, startY + 3, "Level:", SGR.BOLD);
        tg.putString(startX - 9, startY + 4, String.valueOf(model.getLevel()), SGR.BOLD);

        tg.putString(startX - 9, startY + 6, "Time:", SGR.BOLD);
        tg.putString(startX - 9, startY + 7, model.getTime(), SGR.BOLD);


        if (model.gameOver() && model.messageGameOver()) {
            for (int i = 0; i < title.length; i++) {
                tg.putString((terminalSize.getColumns() - title[i].length()) / 2, i + 1, " ".repeat(title[i].length()));
            }

            int artStartX = (terminalSize.getColumns() - gameOverArt[0].length()) / 2;
            int artStartY = 1;
            for (int i = 0; i < gameOverArt.length; i++) {
                tg.putString(artStartX, artStartY + i, gameOverArt[i], SGR.BOLD);
            }

            tg.putString(artStartX + 20, artStartY + gameOverArt.length + 1, "Press SPACE to restart", SGR.BOLD);
        } else {
            int artStartX = (terminalSize.getColumns() - gameOverArt[0].length()) / 2;
            int artStartY = 1;

            for (int i = 0; i < gameOverArt.length + 2; i++) {
                tg.putString(artStartX, artStartY + i, " ".repeat(gameOverArt[0].length()));
            }

            titleStartX = (terminalSize.getColumns() - title[0].length()) / 2;
            for (int i = 0; i < title.length; i++) {
                tg.putString(titleStartX, i + 1, title[i], SGR.BOLD);
            }
        }

        screen.refresh();
    }
}
