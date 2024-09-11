import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Game of Life! ⊂(◉‿◉)つ");
        System.out.println("Please input coordinates in integer of live cells in separate line in format `x, y`");
        System.out.println("Any input not in this form would be ignored");
        System.out.println("When you're finished, please enter END");
        System.out.println("Have Fun! (ง •̀◡•́)ง");

        List<Cell> liveCells = getLiveCellsByUserInput();

        LifeBoard lifeBoard = new LifeBoard(liveCells);
        lifeBoard.tick();

        System.out.println("This is the state at next tick:");
        lifeBoard.printState();
        System.out.println("That's all. Thank you for playing! (✿◠‿◠)");
    }

    private static List<Cell> getLiveCellsByUserInput() {
        List<Cell> liveCells = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String enteredValue = userInput.nextLine();
        while (!Objects.equals(enteredValue, "END")) {
            try {
                String[] values = enteredValue.split(",");
                if (values.length != 2) {
                    System.out.println("Please enter only 2 numbers");
                    enteredValue = userInput.nextLine();
                    continue;
                }
                // Will also handle input validation by throwing error
                int xCoordinate = Integer.parseInt(values[0].trim());
                int yCoordinate = Integer.parseInt(values[1].trim());

                liveCells.add(new Cell(true, xCoordinate, yCoordinate));
            }
            catch (NumberFormatException _) {
                System.out.println("Please enter integers only");
            }
            enteredValue = userInput.nextLine();
        }
        userInput.close();
        return liveCells;
    }
}