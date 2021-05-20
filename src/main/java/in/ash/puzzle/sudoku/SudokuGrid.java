package in.ash.puzzle.sudoku;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;

public class SudokuGrid {

    private final int[][] grid;

    public SudokuGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getNumberOfRows() {
        return grid.length;
    }

    public int getNumberOfColumns() {
        return grid[0].length;
    }

    public int[] getRowElements(int rowIndex) {
        return grid[rowIndex];
    }

    public int[] getColumnElements(int columnIndex) {
        return range(0, grid.length)
                .map(i -> grid[i][columnIndex])
                .toArray();
    }

    public int[] getMiniGrid(int index) {
        int startRow = index >= 0 && index < 3 ? 0 : index >= 3 && index < 6 ? 3 : 6;
        int startCol = of(0, 3, 6).anyMatch(c -> index == c) ? 0 : of(1, 4, 7).anyMatch(c -> index == c) ? 3 : 6;

        return new int[]{grid[startRow][startCol], grid[startRow][startCol + 1], grid[startRow][startCol + 2],
                grid[startRow + 1][startCol], grid[startRow + 1][startCol + 1], grid[startRow + 1][startCol + 2],
                grid[startRow + 2][startCol], grid[startRow + 2][startCol + 1], grid[startRow + 2][startCol + 2]};
    }
}
