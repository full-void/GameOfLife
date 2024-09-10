import java.util.ArrayList;
import java.util.Arrays;

public class Cell {
    private boolean live;
    private int x;
    private int y;

    public Cell(boolean live, int x, int y) {
        this.live = live;
        this.x = x;
        this.y = y;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean getLive() {
        return this.live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean lifeNextTick(int liveNeighbourCount) {
        // Cell with less than 2 or greater than 3 neighbour dies due to loneliness or overcrowding
        if (liveNeighbourCount<2 || liveNeighbourCount > 3) {
            this.live = false;
            return false;
        }
        // Any dead cell with exactly three live neighbours comes to life
        if (!this.live && liveNeighbourCount == 3){
            this.live = true;
            return true;
        }
        // Live cell with two or three live neighbours lives, unchanged, to the next generation
        // Only this possibility is left
        return this.live;
    }

    @Override
    public int hashCode() {
        // Only take coordinates into account since two cells with same coordinates but different 'life' can't exist
        return (new ArrayList<Integer>(Arrays.asList(this.x, this.y))).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        // Only take coordinates into account since two cells with same coordinates but different 'life' can't exist
        return x == cell.x && y == cell.y;
    }
}
