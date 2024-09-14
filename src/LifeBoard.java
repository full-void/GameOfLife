import java.util.*;

public class LifeBoard {
    protected List<Cell> liveCells;

    public LifeBoard(List<Cell> liveCells) {
        this.liveCells = List.copyOf(liveCells);
    }

    public List<Cell> getLiveCells() {
        return this.liveCells;
    }

    public void tick() {
        Set<Cell> newLiveCells = new HashSet<>();

        Set<List<Integer>> liveCoordinates = getLiveCoordinates();
        Set<Cell> possiblyAliveCellsNextTick = getPossiblyAliveCellsNextTick();

        for (Cell cell: possiblyAliveCellsNextTick) {
            int liveNeighbourCount = getLiveNeighbourCount(cell, liveCoordinates);
            boolean isAliveNextTick = cell.lifeNextTick(liveNeighbourCount);
            if (isAliveNextTick) {
                newLiveCells.add(cell);
            }
        }

        this.liveCells = newLiveCells.stream().toList();
    }

    private Set<List<Integer>> getLiveCoordinates() {
        Set<List<Integer>> liveCoordinates = new HashSet<>();

        for (Cell cell : this.liveCells) {
            liveCoordinates.add(Arrays.asList(cell.getX(), cell.getY()));
        }

        return liveCoordinates;
    }

    /**
     * Computes and returns all cells that could possibly be alive in next tick.
     * Given that it's an infinite board, all possibly alive cells must be either currently alive cells or their neighbours
     * @return a Set<Cell> containing all cells possibly alive next tick.
     */
    private Set<Cell> getPossiblyAliveCellsNextTick() {
        Set<Cell> possiblyAliveCellsNextTick = new HashSet<>(this.liveCells);

        // Neighbors
        for (Cell cell : this.liveCells) {
            for (int deltaX = -1; deltaX <= 1; deltaX++) {
                for (int deltaY = -1; deltaY <= 1; deltaY++) {
                    // Docs guarantee that if an element already exists, it's left as is
                    // Live cells are already added and given hash and equal only take coordinates into account, live cell won't be overwritten
                    possiblyAliveCellsNextTick.add(new Cell(false, cell.getX() + deltaX, cell.getY() + deltaY));
                }
            }
        }

        return possiblyAliveCellsNextTick;
    }

    private int getLiveNeighbourCount(Cell cell, Set<List<Integer>> liveCoordinates) {
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
