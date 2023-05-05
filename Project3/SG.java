package Project3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class SG {
    /*
     * The Requirements of this project is to create the ladder game in an asci art
     * EX.
     * |||||| ---- Start
     * | || |
     * | || |
     * | || |
     * | || |
     * | || |
     * | || |
     * | || |
     * | || |
     * |||||| ---- Finish
     * 
     * This ladder should be dynamic in that as the player continues to play the
     * game the ladder updates
     * EX.
     * |||||| ---- Start
     * |1||0|
     * |1||0|
     * |?||?|
     * 
     * You can follow this legend to help with the glass vs tempered glass
     * ---------LEGEND-----------
     * 0: Glass
     * 1: Tempered Glass
     * --------------------------
     * 
     * The game will continue until the player runs out of lives or makes it to the
     * end:
     * 
     * 
     * WIN EX.
     * Congrats You Won!!
     * |||||| ---- Start
     * |1||0|
     * |1||0|
     * |1||0|
     * |1||0|
     * |0||1|
     * |1||0|
     * |0||1|
     * |0||1|
     * |||||| ---- Finish
     * 
     * 
     * LOSE EX.
     * -------------------------------------
     * |||||| ---- Start
     * |1||0|
     * |1||0|
     * |?||?|
     * You Lost 1 life
     * Total Lives: 0
     * YOU DIED
     */

    // 1. Create a player life counter with the following methods:
    // a) getLives - Get total lives
    // b) death - Subtract 1 from total lives

    public static class Player {
        private int lives;

        public Player(int lives) {
            this.lives = lives;
        }

        public int getLives() {
            return lives;
        }

        public void death() {
            lives--;
            System.out.println("You Lost 1 life");
            System.out.println("Total Lives: " + lives);
        }
    }
    // 2. Create a ladder with n panels
    // Hint1: This can be done with a 2D array with the rows as the panels & 2 cols
    // R/L
    // Hint2: The Cols are going to be hard coded 2 so int[panels][2] would work
    // Hint3: To create the ladders Glass and Tempered Glass placements use a double
    // for loop
    // Hint3: If you are really stuck here (I was for a few hours) Email me
    // Hint4: in the double for loop make a random number generator 0-1
    // Hint5: if the random number is 0 then the panel is glass if 1 then tempered
    // glass
    // The numbers should be hidden until guessed

    public static class Ladder {
        private int panels;
        private int[][] ladder;

        public Ladder(int panels) {
            this.panels = panels;
            ladder = new int[panels][2];
            Random random = new Random();
            for (int i = 0; i < panels; i++) {
                for (int j = 0; j < 2; j++) {
                    ladder[i][j] = random.nextInt(2);
                }
            }
        }

        public int[][] getLadder() {
            return ladder;
        }

        public int getPanels() {
            return panels;
        }
    }
    // 3. Create a dynamic printer for a answer ladder and blank ladder and current
    // ladder
    // a) Have a hard coded Start and End
    // b) the ladder should be the length that was decided by the objects
    // initialization
    // Hint: Current Ladder -This is helpful- "|"+ladder[i][j] + "|"
    // the beginning and end should be hard coded with "Start" and "Finish"
    // the numbers to be guessed should be hidden as the ladder dynamically updates
    // with user input

    public static class Printer {
        private int start;
        private int end;
        private int[][] ladder;

        public Printer(int start, int end, int[][] ladder) {
            this.start = start;
            this.end = end;
            this.ladder = ladder;
        }

        public void printLadder() {
            System.out.println("|||Start|||");
            for (int i = 0; i < ladder.length; i++) {
                System.out.print("|");
                for (int j = 0; j < ladder[i].length; j++) {
                    if (i >= start && i <= end) {
                        System.out.print(ladder[i][j]);
                    } else {
                        System.out.print("?");
                    }
                }
                System.out.println("|");
            }
            System.out.println("|||Finish|||");
        }
    }
    // 4. Create a method to get the current user selection
    // a) answer should only be R,r or L,l anything else should reprompt the user
    // b) convert R to 1 and L to 0
    // c) add that value to an ArrayList

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true) {
            System.out.println("Enter R or L");
            answer = scanner.nextLine();
            if (answer.equals("R") || answer.equals("r") || answer.equals("L") || answer.equals("l")) {
                break;
            }
        }
        return answer;
    }

    /*
     * 5. Now you have a 2D array of the answers of panels and an running arrayList
     * of the user
     * selections... compare the current index of the users selection with the
     * answer array
     * if correct continue to next ladder row and print the current ladder
     * incermented by 1
     * if wrong -1 from lives
     * if the lives hit 0 then end the game
     * if the lives are not 0 and the end of the ladder is reached then print the
     * final
     * ladder as well as a congrats
     */
    // Hint: Use a while loop to continue the game until the end or lives == 0
    // Hint: Use a for loop to iterate through the ladder
    public static void main(String[] args) {
        Player player = new Player(3);
        Ladder ladder = new Ladder(8);
        Printer printer = new Printer(0, ladder.getPanels() - 1, ladder.getLadder());
        printer.printLadder();
        List<String> userSelections = new ArrayList<String>();
        while (player.getLives() > 0) {
            String answer = getUserInput();
            userSelections.add(answer);
            if (answer.equals("R") || answer.equals("r")) {
                if (ladder.getLadder()[userSelections.size() - 1][1] == 1) {
                    System.out.println("Correct");
                    printer = new Printer(0, userSelections.size() - 1, ladder.getLadder());
                    printer.printLadder();
                } else {
                    player.death();
                    if (player.getLives() == 0) {
                        System.out.println("YOU DIED");
                        break;
                    }
                }
            } else if (answer.equals("L") || answer.equals("l")) {
                if (ladder.getLadder()[userSelections.size() - 1][0] == 1) {
                    System.out.println("Correct");
                    printer = new Printer(0, userSelections.size() - 1, ladder.getLadder());
                    printer.printLadder();
                } else {
                    player.death();
                    if (player.getLives() == 0) {
                        System.out.println("YOU DIED");
                        break;
                    }
                }
            }
            if (userSelections.size() == ladder.getPanels()) {
                System.out.println("Congrats You Won!!");
                printer = new Printer(0, userSelections.size() - 1, ladder.getLadder());
                printer.printLadder();
                break;
            }
        }
    }
}