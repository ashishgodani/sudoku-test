package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SudokuPuzzleAppTest {

    @Test
    public void sudokuPuzzleShouldValidateGridAndReturnInvalidForGridWithErrors() {
        SudokuGrid grid = SudokuPuzzleApp.fromFile(
                SudokuPuzzleAppTest.class.getClassLoader().getResource("invalid.txt").getPath());
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("INVALID", result.getMessage());
        assertEquals("Row 0 has duplicates [7],Column 1 has duplicates [7],Minigrid 0 has duplicates [7]", result.getErrorText());
    }

    @Test
    public void sudokuPuzzleShouldValidateGridAndReturnValidForGridWithoutErrors() {
        SudokuGrid grid = SudokuPuzzleApp.fromFile(
                SudokuPuzzleAppTest.class.getClassLoader().getResource("valid.txt").getPath());
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("VALID", result.getMessage());
        assertEquals("", result.getErrorText());
    }

    @Test
    public void sudokuPuzzleShouldValidateEmptyGridAndReturnValid() {
        SudokuGrid grid = SudokuPuzzleApp.fromFile(
                SudokuPuzzleAppTest.class.getClassLoader().getResource("empty.txt").getPath());
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("VALID", result.getMessage());
        assertEquals("", result.getErrorText());
    }
}
