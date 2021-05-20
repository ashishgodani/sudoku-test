package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridReaderTest {

    @Test
    public void shouldReadSudokuGridFromFile() throws SudokuGridIOException {
        SudokuGrid sudokuGrid = SudokuGridReader.readSudokuGridFromFile(
                SudokuGridReaderTest.class.getClassLoader().getResource("valid.txt").getPath());

        assertEquals(9, sudokuGrid.getNumberOfRows());
        assertEquals(9, sudokuGrid.getNumberOfColumns());
        assertArrayEquals(new int[]{7, 9, 2, 1, 5, 4, 3, 8, 6}, sudokuGrid.getRowElements(0));
    }

    @Test
    public void shouldThrowExceptionForANonExistentFile() {
        assertThrows(SudokuGridIOException.class,
                () -> SudokuGridReader.readSudokuGridFromFile("nofile.txt"));
    }

}