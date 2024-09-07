import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    public void testCreation() {
        Cell cell = new Cell(true, 1, 2);
        assertTrue(cell.getLive());
        assertEquals(cell.getX(), 1);
        assertEquals(cell.getY(), 2);
    }

    @Test
    public void testUpdate() {
        Cell cell = new Cell(true, 1, 2);
        cell.setLive(false);
        cell.setX(3);
        cell.setY(4);
        assertFalse(cell.getLive());
        assertEquals(cell.getX(), 3);
        assertEquals(cell.getY(), 4);
    }

    @Test
    public void testEquality() {
        Cell cell1 = new Cell(true, 1, 1);
        Cell cell2 = new Cell(false, 1, 1);
        assertEquals(cell1, cell2);
    }
}
