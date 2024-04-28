import java.util.Scanner;
public class TwentyFortyEight {
    public static void main(String[] args) {
        Game g = new Game();
        g.addNewNumber();
        g.printArray();
        Scanner scanner = new Scanner(System.in);
        boolean shouldBreak = false;
        while (!shouldBreak) {
            String input = scanner.nextLine();
            if (input.contains("break")) {
                shouldBreak = true;
            } else {
                if (input.contains("w")) {
                    g.pushUp();
                }
                if (input.contains("a")) {
                    g.pushLeft();
                }
                if (input.contains("s")) {
                    g.pushDown();
                }
                if (input.contains("d")) {
                    g.pushRight();
                }
                g.addNewNumber();
                g.printArray();
            }
        }
    }
}

