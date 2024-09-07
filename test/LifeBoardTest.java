import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LifeBoardTest {

    @Test
    public void testCreation(){
        List<List<Integer>> coord = new ArrayList<>();
        coord.add(Arrays.asList(1, 1));
        coord.add(Arrays.asList(1, 2));
        coord.add(Arrays.asList(1, 3));
        coord.add(Arrays.asList(2, 2));
        coord.add(Arrays.asList(2, 3));
        coord.add(Arrays.asList(2, 4));

        LifeBoard lifeBoard = new LifeBoard(coord);

        List<Cell> liveCells = new ArrayList<Cell>();
        for(List<Integer> coordinates: coord) {
            liveCells.add(new Cell(true, coordinates.get(0), coordinates.get(1)));
        }

        assertEquals(lifeBoard.getLiveCells(), liveCells);
    }

    @Test
    public void testToadTick(){
        List<List<Integer>> coord = new ArrayList<>();
        coord.add(Arrays.asList(1, 1));
        coord.add(Arrays.asList(1, 2));
        coord.add(Arrays.asList(1, 3));
        coord.add(Arrays.asList(2, 2));
        coord.add(Arrays.asList(2, 3));
        coord.add(Arrays.asList(2, 4));

        LifeBoard lifeBoard = new LifeBoard(coord);

        lifeBoard.tick();

        List<Cell> liveCells = lifeBoard.getLiveCells();
        assertTrue(liveCells.contains(new Cell(true, 2, 1)));
        assertTrue(liveCells.contains(new Cell(true, 1, 1)));
        assertTrue(liveCells.contains(new Cell(true, 3, 3)));
        assertTrue(liveCells.contains(new Cell(true, 2, 4)));
        assertTrue(liveCells.contains(new Cell(true, 0, 2)));
        assertTrue(liveCells.contains(new Cell(true, 1, 4)));
    }

    @Test
    public void testBlockTick(){
        List<List<Integer>> coord = new ArrayList<>();
        coord.add(Arrays.asList(1, 1));
        coord.add(Arrays.asList(1, 2));
        coord.add(Arrays.asList(2, 1));
        coord.add(Arrays.asList(2, 2));

        LifeBoard lifeBoard = new LifeBoard(coord);

        lifeBoard.tick();

        List<Cell> liveCells = lifeBoard.getLiveCells();
        assertTrue(liveCells.contains(new Cell(true, 1, 1)));
        assertTrue(liveCells.contains(new Cell(true, 1, 2)));
        assertTrue(liveCells.contains(new Cell(true, 2, 1)));
        assertTrue(liveCells.contains(new Cell(true, 2, 2)));
    }

    @Test
    public void testBlinkTick(){
        List<List<Integer>> coord = new ArrayList<>();
        coord.add(Arrays.asList(1, 1));
        coord.add(Arrays.asList(1, 0));
        coord.add(Arrays.asList(1, 2));

        LifeBoard lifeBoard = new LifeBoard(coord);

        lifeBoard.tick();

        List<Cell> liveCells = lifeBoard.getLiveCells();
        assertTrue(liveCells.contains(new Cell(true, 1, 1)));
        assertTrue(liveCells.contains(new Cell(true, 0, 1)));
        assertTrue(liveCells.contains(new Cell(true, 2, 1)));
    }
}
