import java.util.ArrayList;
import java.util.Random;
public class Game {
    private int[][] gameBoard;
    Random r = new Random();

    public Game() {
        gameBoard = new int[4][4];
    }

    public String format(int n) {
      return String.format("%6s", n > 0 ? "" + n : "");
    }

    public void printArray() {
        System.out.println("╭――――――┬――――――┬――――――┬――――――╮");
        for (int i = 0; i < gameBoard.length; i++) {
            int[] x = gameBoard[i];
            System.out.printf(
                "│%s│%s│%s│%s│%n", 
                format(x[0]),
                format(x[1]), 
                format(x[2]), 
                format(x[3])
            );
            if (i < gameBoard.length - 1) {
                System.out.println("├――――――┼――――――┼――――――┼――――――┤");
            }
        }
        System.out.println("╰――――――┴――――――┴――――――┴――――――╯");
        System.out.println();
    }

    public void addNewNumber() {
        ArrayList<Integer> emptySpacesX = new ArrayList<>();
        ArrayList<Integer> emptySpacesY = new ArrayList<>();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameBoard[x][y] == 0) {
                    emptySpacesX.add(x);
                    emptySpacesY.add(y);

                }
            }
        }
            if (emptySpacesX.isEmpty()) {
                return;
            }
            int choice = r.nextInt(emptySpacesX.size());
            int numberChooser = r.nextInt(10); //value 0-9
            int newNumber = 2;
            if (numberChooser == 0) {
                newNumber = 4;
            }
            int X = emptySpacesX.get(choice);
            int Y = emptySpacesY.get(choice);
            gameBoard[X][Y] = newNumber;
        }

    public void pushUp() {
        System.out.println("Pushing up...");
        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int x = 1; x < 4; x++) {
                if (gameBoard[x][y] != 0) {
                    int value = gameBoard[x][y];
                    int X = x - 1;
                    while ((X >= 0) && (gameBoard[X][y] == 0)) {
                        X--;
                    }
                    if (X == -1) {
                        gameBoard[0][y] = value;
                        gameBoard[x][y] = 0;
                    } else if (gameBoard[X][y] != value) {
                        if (X + 1 != x) { // Check if shifting occurred
                            gameBoard[X + 1][y] = value;
                            gameBoard[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[X]) {
                            gameBoard[X][y] *= 2;
                            gameBoard[x][y] = 0;
                            alreadyCombined[X] = true;
                        } else {
                            if (X + 1 != x) { // Check if shifting occurred
                                gameBoard[X + 1][y] = value;
                                gameBoard[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
    public void pushDown() {
        System.out.println("Pushing down...");
        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int x = 2; x > -1; x--) {
                if (gameBoard[x][y] != 0) {
                    int value = gameBoard[x][y];
                    int X = x + 1;
                    while ((X <= 3) && (gameBoard[X][y] == 0)) {
                        X++;
                    }
                    if (X == 4) {
                        gameBoard[3][y] = value;
                        gameBoard[x][y] = 0;
                    } else if (gameBoard[X][y] != value) {
                        if (X - 1 != x) { // Check if shifting occurred
                            gameBoard[X - 1][y] = value;
                            gameBoard[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[X]) {
                            gameBoard[X][y] *= 2;
                            gameBoard[x][y] = 0;
                            alreadyCombined[X] = true;
                        } else {
                            if (X - 1 != x) { // Check if shifting occurred
                                gameBoard[X - 1][y] = value;
                                gameBoard[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
    public void pushLeft() {
        System.out.println("Pushing left...");
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int y = 1; y < 4; y++) {
                if (gameBoard[x][y] != 0) {
                    int value = gameBoard[x][y];
                    int Y = y - 1;
                    while ((Y >= 0) && (gameBoard[x][Y] == 0)) {
                        Y--;
                    }
                    if (Y == -1) {
                        gameBoard[x][0] = value;
                        gameBoard[x][y] = 0;
                    } else if (gameBoard[x][Y] != value) {
                        if (Y + 1 != y) { // Check if shifting occurred
                            gameBoard[x][Y + 1] = value;
                            gameBoard[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[Y]) {
                            gameBoard[x][Y] *= 2;
                            gameBoard[x][y] = 0;
                            alreadyCombined[Y] = true;
                        } else {
                            if (Y + 1 != y) { // Check if shifting occurred
                                gameBoard[x][Y + 1] = value;
                                gameBoard[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void pushRight() {
        System.out.println("Pushing right...");
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int y = 2; y > -1; y--) {
                if (gameBoard[x][y] != 0) {
                    int value = gameBoard[x][y];
                    int Y = y + 1;
                    while ((Y <= 3) && (gameBoard[x][Y] == 0)) {
                        Y++;
                    }
                    if (Y == 4) {
                        gameBoard[x][3] = value;
                        gameBoard[x][y] = 0;
                    } else if (gameBoard[x][Y] != value) {
                        if (Y - 1 != y) { // Check if shifting occurred
                            gameBoard[x][Y - 1] = value;
                            gameBoard[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[Y]) {
                            gameBoard[x][Y] *= 2;
                            gameBoard[x][y] = 0;
                            alreadyCombined[Y] = true;
                        } else {
                            if (Y - 1 != y) { // Check if shifting occurred
                                gameBoard[x][Y - 1] = value;
                                gameBoard[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
