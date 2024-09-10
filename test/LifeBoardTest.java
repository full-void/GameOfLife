
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LifeBoardTest {

    @Test
    public void testCreation(){
        List<Cell> liveCells = new ArrayList<>();
        liveCells.add(new Cell(true, 1, 1));
        liveCells.add(new Cell(true, 1, 2));
        liveCells.add(new Cell(true, 1, 3));
        liveCells.add(new Cell(true, 2, 2));
        liveCells.add(new Cell(true, 2, 3));
        liveCells.add(new Cell(true, 2, 4));

        LifeBoard lifeBoard = new LifeBoard(liveCells);

        assertEquals(lifeBoard.getLiveCells(), liveCells);
    }

    @Test
    public void testToadTick(){
        List<Cell> liveCells = new ArrayList<>();
        liveCells.add(new Cell(true, 1, 1));
        liveCells.add(new Cell(true, 1, 2));
        liveCells.add(new Cell(true, 1, 3));
        liveCells.add(new Cell(true, 2, 2));
        liveCells.add(new Cell(true, 2, 3));
        liveCells.add(new Cell(true, 2, 4));

        LifeBoard lifeBoard = new LifeBoard(liveCells);

        lifeBoard.tick();

        List<Cell> updatedLiveCells = lifeBoard.getLiveCells();
        assertTrue(updatedLiveCells.contains(new Cell(true, 2, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 1, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 3, 3)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 2, 4)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 0, 2)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 1, 4)));
    }

    @Test
    public void testBlockTick(){
        List<Cell> liveCells = new ArrayList<>();
        liveCells.add(new Cell(true, 1, 1));
        liveCells.add(new Cell(true, 1, 2));
        liveCells.add(new Cell(true, 2, 1));
        liveCells.add(new Cell(true, 2, 2));

        LifeBoard lifeBoard = new LifeBoard(liveCells);

        lifeBoard.tick();

        List<Cell> updatedLiveCells = lifeBoard.getLiveCells();
        assertTrue(updatedLiveCells.contains(new Cell(true, 1, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 1, 2)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 2, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 2, 2)));
    }

    @Test
    public void testBlinkTick(){
        List<Cell> liveCells = new ArrayList<>();
        liveCells.add(new Cell(true, 1, 1));
        liveCells.add(new Cell(true, 1, 0));
        liveCells.add(new Cell(true, 1, 2));

        LifeBoard lifeBoard = new LifeBoard(liveCells);

        lifeBoard.tick();

        List<Cell> updatedLiveCells = lifeBoard.getLiveCells();
        assertTrue(updatedLiveCells.contains(new Cell(true, 1, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 0, 1)));
        assertTrue(updatedLiveCells.contains(new Cell(true, 2, 1)));
    }
}

