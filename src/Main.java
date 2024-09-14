import java.util.*;

public class Main {
        public static void main(String[] args) {
        LifeBoardSystemConsoleIO lifeBoardSystemConsoleIO = new LifeBoardSystemConsoleIO();

        LifeBoard lifeBoard  = lifeBoardSystemConsoleIO.readLifeBoard();
        lifeBoard.tick();

        lifeBoardSystemConsoleIO.outputLifeBoard(lifeBoard);
    }
}