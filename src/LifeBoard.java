import java.util.*;

public class LifeBoard {
    protected List<Cell> LiveCells;

    public LifeBoard(List<List<Integer>> liveCoordinates) {
        List<Cell> LiveCells = new ArrayList<Cell>();
        for(List<Integer> coordinates: liveCoordinates) {
            LiveCells.add(new Cell(true, coordinates.get(0), coordinates.get(1)));
        }
        this.LiveCells = LiveCells;
    }

    public List<Cell> getLiveCells() {
        return this.LiveCells;
    }

    public void printState() {
        for (Cell cell: LiveCells){
            System.out.printf("%d, %d%n", cell.getX(), cell.getY());
        }
    }

    public void tick() {
        Set<Cell> pertinentCells = new HashSet<>(this.LiveCells);
        Set<List<Integer>> liveCoordinates = new HashSet<>();
        Set<Cell> newLiveCells = new HashSet<>();

        // Propagate all cells need to be considered and live cells
        for (Cell cell : this.LiveCells) {
            liveCoordinates.add(Arrays.asList(cell.getX(), cell.getY()));

            // Neighbours
            for (int deltaX = -1; deltaX <= 1; deltaX++) {
                for (int deltaY = -1; deltaY <= 1; deltaY++) {
                    // Docs guarantee that if an element already exists, it's left as is
                    // Live cells are already added and given hash and equal only take coordinates into account, live cell won't be overwritten
                    pertinentCells.add(new Cell(false, cell.getX() + deltaX, cell.getY() + deltaY));
                }
            }
        }

        for (Cell cell: pertinentCells) {
            int liveNeighbourCount = getLiveNeighbourCount(cell, liveCoordinates);
            // Cell with less than 2 or greater than 3 neighbour dies due to loneliness or overcrowding
            if (liveNeighbourCount<2 || liveNeighbourCount > 3) {
                continue;
            }
            // Live cell with two or three live neighbours lives, unchanged, to the next generation
            if (cell.getLive() && (liveNeighbourCount == 3 || liveNeighbourCount == 2)){
                newLiveCells.add(cell);
            }
            // Any dead cell with exactly three live neighbours comes to life
            if (!cell.getLive() && liveNeighbourCount == 3){
                newLiveCells.add(new Cell(true, cell.getX(), cell.getY()));
            }
        }

        this.LiveCells = newLiveCells.stream().toList();
    }

    private static int getLiveNeighbourCount(Cell cell, Set<List<Integer>> liveCoordinates) {
        int liveNeighbourCount = 0;
        for (int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                if (deltaX == 0 && deltaY == 0) {
                    continue; // Cell itself is not neighbour
                }
                if (liveCoordinates.contains(Arrays.asList(cell.getX() + deltaX, cell.getY() + deltaY))){
                    liveNeighbourCount = liveNeighbourCount + 1;
                }
            }
        }
        return liveNeighbourCount;
    }
}
