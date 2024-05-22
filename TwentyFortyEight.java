import java.util.Scanner;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
public class TwentyFortyEight extends JFrame implements KeyListener{
    public static Game g = new Game();
    public static Scanner scanner = new Scanner(System.in);
    static boolean shouldBreak = false;
    //String input = scanner.nextLine();

    public static void main(String[] args) {
        new TwentyFortyEight();
        g.addNewNumber();
        System.out.println("Welcome to 2048! Press WASD to play!");
        System.out.println("To end the game, type \"break\"");
        g.printArray();
        //System.out.println(scanner.nextLine());
        String input = "Y";
        while (!shouldBreak) {
            if(g.gameLost()){
                System.out.println("You lost!");
                System.out.println("Try again? Y/N");
                //String input = scanner.nextLine();
                if(input.equalsIgnoreCase("N")){
                    System.out.println("Thanks for playing!");
                    shouldBreak = true;
                }
                if(input.equalsIgnoreCase("Y")){
                    g.clearBoard();
                    g.addNewNumber();
                    g.printArray();
                }
            }
            if(g.gameWon()){
                System.out.println("You won!");
            }
            //String input = scanner.nextLine();
            if (input.contains("break")) {
                System.out.print("Game Over!");
                shouldBreak = true;

            } else {
                if (input.equals("w")) {
                    g.pushUp(g.getBoard());
                    g.addNewNumber();
                    g.printArray();
                }
                if (input.equals("a")) {
                    g.pushLeft(g.getBoard());
                    g.addNewNumber();
                    g.printArray();
                }
                if (input.equals("s")) {
                    g.pushDown(g.getBoard());
                    g.addNewNumber();
                    g.printArray();
                }
                if (input.equals("d")) {
                    g.pushRight(g.getBoard());
                    g.addNewNumber();
                    g.printArray();
                }
                if (input.equals("")) {
                    //System.out.println("Press WASD to play!");   
                    g.addNewNumber();
                    g.printArray();
                }
            }
        }
    }

    TwentyFortyEight(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){
        System.out.println("You released key char: "+ e.getKeyChar());
        System.out.println("You released key code: "+ e.getKeyCode());
        switch(e.getKeyCode()){
            case 37: {
                g.pushLeft(g.getBoard());
                g.addNewNumber();
                g.printArray();
                if(g.gameLost()){
                    System.out.println("You lost!");
                    System.out.println("Try again? Y/N");
                    String input = scanner.nextLine();
                    if(input.equalsIgnoreCase("N")){
                        System.out.println("Thanks for playing!");
                        shouldBreak = true;
                    }
                    if(input.equalsIgnoreCase("Y")){
                        g.clearBoard();
                        g.addNewNumber();
                        g.printArray();
                    }
                }
                break;
            }
            case 38:{ g.pushUp(g.getBoard());
                g.addNewNumber();
                g.printArray();
                if(g.gameLost()){
                    System.out.println("You lost!");
                    System.out.println("Try again? Y/N");
                    String input = scanner.nextLine();
                    if(input.equalsIgnoreCase("N")){
                        System.out.println("Thanks for playing!");
                        shouldBreak = true;
                    }
                    if(input.equalsIgnoreCase("Y")){
                        g.clearBoard();
                        g.addNewNumber();
                        g.printArray();
                    }
                }
                break;
            }
            case 39:{ g.pushRight(g.getBoard());
                g.addNewNumber();
                g.printArray();
                if(g.gameLost()){
                    System.out.println("You lost!");
                    System.out.println("Try again? Y/N");
                    String input = scanner.nextLine();
                    if(input.equalsIgnoreCase("N")){
                        System.out.println("Thanks for playing!");
                        shouldBreak = true;
                    }
                    if(input.equalsIgnoreCase("Y")){
                        g.clearBoard();
                        g.addNewNumber();
                        g.printArray();
                    }
                }
                break;
            }
            case 40:{ g.pushDown(g.getBoard());
                g.addNewNumber();
                g.printArray();
                if(g.gameLost()){
                    System.out.println("You lost!");
                    System.out.println("Try again? Y/N");
                    String input = scanner.nextLine();
                    if(input.equalsIgnoreCase("N")){
                        System.out.println("Thanks for playing!");
                        shouldBreak = true;
                    }
                    if(input.equalsIgnoreCase("Y")){
                        g.clearBoard();
                        g.addNewNumber();
                        g.printArray();
                    }
                }
                break;
            }
        }
    }
}
