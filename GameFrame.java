import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements KeyListener {
    public static Game g = new Game();
    private JLabel[][] labels;

    public static void main(String[] args) {
        g.addNewNumber();
        g.addNewNumber();
        new GameFrame();
    }

    GameFrame() {
        labels = new JLabel[4][4];
        initializeUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(415, 435);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setTitle("2048");
        updateLabels();
        this.setVisible(true);
    }

    private void initializeUI() {
        ImageIcon image2048 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/2048.png");
        this.setIconImage(image2048.getImage());

        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setBounds(i * 100, j * 100, 100, 100);
                this.add(labels[i][j]);
            }
        }
    }

    private void updateLabels() {
        ImageIcon image2 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/2.png");
        ImageIcon image4 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/4.png");
        ImageIcon image8 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/8.png");
        ImageIcon image16 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/16.png");
        ImageIcon image32 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/32.png");
        ImageIcon image64 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/64.png");
        ImageIcon image128 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/128.png");
        ImageIcon image256 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/256.png");
        ImageIcon image512 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/512.png");
        ImageIcon image1024 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/1024.png");
        ImageIcon image2048 = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/2048.png");
        ImageIcon gameover = new ImageIcon("/Users/jaydenlin/IdeaProjects/ComputerScienceA/src/2048 tiles/gameover.png");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (g.getBoard()[i][j] != 0) {
                    switch (g.getBoard()[i][j]) {
                        case 2:
                            labels[i][j].setIcon(image2);
                            break;
                        case 4:
                            labels[i][j].setIcon(image4);
                            break;
                        case 8:
                            labels[i][j].setIcon(image8);
                            break;
                        case 16:
                            labels[i][j].setIcon(image16);
                            break;
                        case 32:
                            labels[i][j].setIcon(image32);
                            break;
                        case 64:
                            labels[i][j].setIcon(image64);
                            break;
                        case 128:
                            labels[i][j].setIcon(image128);
                            break;
                        case 256:
                            labels[i][j].setIcon(image256);
                            break;
                        case 512:
                            labels[i][j].setIcon(image512);
                            break;
                        case 1024:
                            labels[i][j].setIcon(image1024);
                            break;
                        case 2048:
                            labels[i][j].setIcon(image2048);
                            break;
                    }
                } else {
                    labels[i][j].setIcon(null);
                }
            }
        }

        if (g.gameLost()) {
            labels[0][0].setBounds(0, 0, 400, 400);
            labels[0][0].setIcon(gameover);
            System.out.println("You Lost!!!");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        handleKeyPress(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void handleKeyPress(char keyChar) {
        switch (keyChar) {
            case 'w':
                g.pushLeft(g.getBoard());
                g.addNewNumber();
                updateLabels();
                break;
            case 'a':
                g.pushUp(g.getBoard());
                g.addNewNumber();
                updateLabels();
                break;
            case 'd':
                g.pushDown(g.getBoard());
                g.addNewNumber();
                updateLabels();
                break;
            case 's':
                g.pushRight(g.getBoard());
                g.addNewNumber();
                updateLabels();
                break;
        }
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                g.pushUp(g.getBoard());
                if (!g.noUP()) {
                    g.addNewNumber();
                }
                updateLabels();
                break;
            case KeyEvent.VK_UP:
                g.pushLeft(g.getBoard());
                if (!g.noLEFT()) {
                    g.addNewNumber();
                }
                updateLabels();
                break;
            case KeyEvent.VK_RIGHT:
                g.pushDown(g.getBoard());
                if (!g.noDOWN()) {
                    g.addNewNumber();
                }
                updateLabels();
                break;
            case KeyEvent.VK_DOWN:
                g.pushRight(g.getBoard());
                if (!g.noRIGHT()) {
                    g.addNewNumber();
                }
                updateLabels();
                break;
        }
    }
}
