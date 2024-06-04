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
        //System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public void pushUp(int[][] board) {
        if(board==gameBoard){
            System.out.println("Pushing left...");
        }
        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int x = 1; x < 4; x++) {
                if (board[x][y] != 0) {
                    int value = board[x][y];
                    int X = x - 1;
                    while ((X >= 0) && (board[X][y] == 0)) {
                        X--;
                    }
                    if (X == -1) {
                        board[0][y] = value;
                        board[x][y] = 0;
                    } else if (board[X][y] != value) {
                        if (X + 1 != x) { // Check if shifting occurred
                            board[X + 1][y] = value;
                            board[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[X]) {
                            board[X][y] *= 2;
                            board[x][y] = 0;
                            alreadyCombined[X] = true;
                        } else {
                            if (X + 1 != x) { // Check if shifting occurred
                                board[X + 1][y] = value;
                                board[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void pushDown(int[][] board) {
        if(board==gameBoard){
            System.out.println("Pushing right...");
        }
        for (int y = 0; y < 4; y++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int x = 2; x > -1; x--) {
                if (board[x][y] != 0) {
                    int value = board[x][y];
                    int X = x + 1;
                    while ((X <= 3) && (board[X][y] == 0)) {
                        X++;
                    }
                    if (X == 4) {
                        board[3][y] = value;
                        board[x][y] = 0;
                    } else if (board[X][y] != value) {
                        if (X - 1 != x) { // Check if shifting occurred
                            board[X - 1][y] = value;
                            board[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[X]) {
                            board[X][y] *= 2;
                            board[x][y] = 0;
                            alreadyCombined[X] = true;
                        } else {
                            if (X - 1 != x) { // Check if shifting occurred
                                board[X - 1][y] = value;
                                board[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void pushLeft(int[][] board) {
        if(board==gameBoard){
            System.out.println("Pushing up...");
        }
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int y = 1; y < 4; y++) {
                if (board[x][y] != 0) {
                    int value = board[x][y];
                    int Y = y - 1;
                    while ((Y >= 0) && (board[x][Y] == 0)) {
                        Y--;
                    }
                    if (Y == -1) {
                        board[x][0] = value;
                        board[x][y] = 0;
                    } else if (board[x][Y] != value) {
                        if (Y + 1 != y) { // Check if shifting occurred
                            board[x][Y + 1] = value;
                            board[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[Y]) {
                            board[x][Y] *= 2;
                            board[x][y] = 0;
                            alreadyCombined[Y] = true;
                        } else {
                            if (Y + 1 != y) { // Check if shifting occurred
                                board[x][Y + 1] = value;
                                board[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void pushRight(int[][] board) {
        if(board==gameBoard){
            System.out.println("Pushing down...");
        }
        for (int x = 0; x < 4; x++) {
            boolean[] alreadyCombined = {false, false, false, false};
            for (int y = 2; y > -1; y--) {
                if (board[x][y] != 0) {
                    int value = board[x][y];
                    int Y = y + 1;
                    while ((Y <= 3) && (board[x][Y] == 0)) {
                        Y++;
                    }
                    if (Y == 4) {
                        board[x][3] = value;
                        board[x][y] = 0;
                    } else if (board[x][Y] != value) {
                        if (Y - 1 != y) { // Check if shifting occurred
                            board[x][Y - 1] = value;
                            board[x][y] = 0;
                        }
                    } else {
                        if (!alreadyCombined[Y]) {
                            board[x][Y] *= 2;
                            board[x][y] = 0;
                            alreadyCombined[Y] = true;
                        } else {
                            if (Y - 1 != y) { // Check if shifting occurred
                                board[x][Y - 1] = value;
                                board[x][y] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean gameLost(){
        int count = 0;
        int difference = 0;
        int[][] testBoard = new int[4][4];
        boolean different = true;
        for(int[] x: gameBoard){
            for(int y: x){
                if(y!=0){
                    count++;
                }
            }
        }
        if(count==16){

            for(int i=0; i<gameBoard.length;i++){
                for (int j=0;j<gameBoard[i].length;j++){
                    testBoard[i][j] = gameBoard[i][j];
                }

            }
            pushUp(testBoard);
            pushDown(testBoard);
            pushLeft(testBoard);
            pushRight(testBoard);
        }
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==testBoard[i][j]){
                    difference++;
                } else{
                    difference = difference;

                }
            }
        }
        if(difference==16){
            return true;
        }else{
            return false;
        }
    }
    public boolean noUP(){

        int count = 0;
        int difference = 0;
        int[][] testBoard = new int[4][4];
        boolean different = true;
        for(int[] x: gameBoard){
            for(int y: x){
                if(y!=0){
                    count++;
                }
            }
        }
        if(count==16){

            for(int i=0; i<gameBoard.length;i++){
                for (int j=0;j<gameBoard[i].length;j++){
                    testBoard[i][j] = gameBoard[i][j];
                }

            }
            pushUp(testBoard);
        }
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==testBoard[i][j]){
                    difference++;
                } else{
                    difference = difference;

                }
            }
        }
        if(difference==16){
            return true;
        }else{
            return false;
        }
    }
    public boolean noDOWN(){

        int count = 0;
        int difference = 0;
        int[][] testBoard = new int[4][4];
        boolean different = true;
        for(int[] x: gameBoard){
            for(int y: x){
                if(y!=0){
                    count++;
                }
            }
        }
        if(count==16){

            for(int i=0; i<gameBoard.length;i++){
                for (int j=0;j<gameBoard[i].length;j++){
                    testBoard[i][j] = gameBoard[i][j];
                }

            }
            pushDown(testBoard);
        }
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==testBoard[i][j]){
                    difference++;
                } else{
                    difference = difference;

                }
            }
        }
        if(difference==16){
            return true;
        }else{
            return false;
        }
    }
    public boolean noLEFT(){

        int count = 0;
        int difference = 0;
        int[][] testBoard = new int[4][4];
        boolean different = true;
        for(int[] x: gameBoard){
            for(int y: x){
                if(y!=0){
                    count++;
                }
            }
        }
        if(count==16){

            for(int i=0; i<gameBoard.length;i++){
                for (int j=0;j<gameBoard[i].length;j++){
                    testBoard[i][j] = gameBoard[i][j];
                }

            }
            pushLeft(testBoard);
        }
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==testBoard[i][j]){
                    difference++;
                } else{
                    difference = difference;

                }
            }
        }
        if(difference==16){
            return true;
        }else{
            return false;
        }
    }



    public int[][] getBoard(){
        return gameBoard;
    }

    public boolean gameWon(){
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==2048){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean noRIGHT(){

        int count = 0;
        int difference = 0;
        int[][] testBoard = new int[4][4];
        boolean different = true;
        for(int[] x: gameBoard){
            for(int y: x){
                if(y!=0){
                    count++;
                }
            }
        }
        if(count==16){

            for(int i=0; i<gameBoard.length;i++){
                for (int j=0;j<gameBoard[i].length;j++){
                    testBoard[i][j] = gameBoard[i][j];
                }
            }
            pushRight(testBoard);
        }
        for(int i=0; i<gameBoard.length;i++){
            for (int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j]==testBoard[i][j]){
                    difference++;
                } else{
                    difference = difference;

                }
            }
        }
        if(difference==16){
            return true;
        }else{
            return false;
        }
    }



    public void clearBoard(){
        gameBoard = new int[4][4];
    }
}
