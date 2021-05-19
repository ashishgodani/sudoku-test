package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SudokuGridTest {

    private static final int[][] gridElements = {{7, 7, 2, 1, 5, 4, 3, 8, 6},
            {6, 4, 3, 8, 2, 7, 1, 5, 9},
            {8, 5, 1, 3, 9, 6, 7, 2, 4},
            {2, 6, 5, 9, 7, 3, 8, 4, 1},
            {4, 8, 9, 5, 6, 1, 2, 7, 3},
            {3, 1, 7, 4, 8, 2, 9, 6, 5},
            {1, 3, 6, 7, 4, 8, 5, 9, 2},
            {9, 7, 4, 2, 1, 5, 6, 3, 8},
            {5, 2, 8, 6, 3, 9, 4, 1, 7}};

    @Test
    public void shouldReturnNumberOfRows() {
        SudokuGrid grid = new SudokuGrid(new int[9][9]);
        Assertions.assertEquals(9, grid.getNumberOfRows());
    }

    @Test
    public void shouldReturnNumberOfColumns() {
        SudokuGrid grid = new SudokuGrid(new int[9][9]);
        Assertions.assertEquals(9, grid.getNumberOfColumns());
    }

    @Test
    public void shouldReturnElementsForGivenRow() {
        SudokuGrid grid = new SudokuGrid(gridElements);
        range(0, grid.getNumberOfRows())
                .forEach(index -> assertArrayEquals(gridElements[index], grid.getRowElements(index)));
    }

    @Test
    public void shouldReturnElementsForGivenColumn() {
        SudokuGrid grid = new SudokuGrid(gridElements);
        range(0, grid.getNumberOfColumns()).forEach(index ->
                assertArrayEquals(range(0, gridElements.length).map(i -> gridElements[i][index]).toArray(),
                        grid.getColumnElements(index)));
    }

    @Test
    public void shouldReturnElementsForGivenMiniGrid() {
        SudokuGrid grid = new SudokuGrid(gridElements);
        assertArrayEquals(new int[]{7, 7, 2, 6, 4, 3, 8, 5, 1}, grid.getMiniGrid(0));
    }
}
