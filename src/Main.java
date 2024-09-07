import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Game of Life! ⊂(◉‿◉)つ");
        System.out.println("Please input coordinates of live cells in new line in format x, y");
        System.out.println("Any input not in this form would be ignored");
        System.out.println("When you're finished, please enter :END");
        System.out.println("Have Fun! (ง •̀◡•́)ง");

        List<List<Integer>> liveCoordinates = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String enteredValue = userInput.nextLine();
        while (!Objects.equals(enteredValue, "END")) {
            try {
                String[] values = enteredValue.split(",");
                if (values.length > 3) {
                    throw new Exception("More values than needed");
                }
                liveCoordinates.add(Arrays.asList(Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim())));
            }
            catch (Exception _) {}
            enteredValue = userInput.nextLine();
        }
        userInput.close();

        LifeBoard lifeBoard = new LifeBoard(liveCoordinates);
        lifeBoard.tick();

        System.out.println("This is the state at next tick:");
        lifeBoard.printState();
        System.out.println("That's all. Thank you for playing! (✿◠‿◠)");
    }
}