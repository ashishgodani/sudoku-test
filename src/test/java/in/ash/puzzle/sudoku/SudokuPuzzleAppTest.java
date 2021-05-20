package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SudokuPuzzleAppTest {

    @Test
    public void sudokuPuzzleShouldValidateGridAndReturnInvalidForGridWithErrors() {
        SudokuGrid grid = getGrid("invalid.txt");
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Row 0 has duplicates [7], Column 1 has duplicates [7], Minigrid 0 has duplicates [7]", result.getErrorText());
    }

    @Test
    public void sudokuPuzzleShouldValidateGridAndReturnValidForGridWithoutErrors() {
        SudokuGrid grid = getGrid("valid.txt");
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("VALID", result.getValidationMessage());
        assertEquals("", result.getErrorText());
    }

    @Test
    public void sudokuPuzzleShouldValidateEmptyGridAndReturnValid() {
        SudokuGrid grid = getGrid("empty.txt");
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("VALID", result.getValidationMessage());
        assertEquals("", result.getErrorText());
    }

    @Test
    public void unfinishedValidSolutionShouldBeValid() {
        SudokuGrid grid = getGrid("sample.txt");
        ValidationResult result = SudokuPuzzleApp.validate(grid);
        assertEquals("VALID", result.getValidationMessage());
    }

    @Test
    public void shouldPrintsValidOutputOnConsoleForValidInputGrid() throws Exception {
        String gridFile = getPath("sample.txt");
        String output = tapSystemOutNormalized(() -> SudokuPuzzleApp.main(new String[]{gridFile}));
        assertEquals("VALID\n\n", output);
    }

    @Test
    public void shouldPrintsInvalidOutputOnConsoleForInvalidInputGrid() throws Exception {
        String gridFile = getPath("invalid.txt");
        String actualOutput = tapSystemOutNormalized(() -> SudokuPuzzleApp.main(new String[]{gridFile}));
        String expectedOutput =
                "INVALID\nRow 0 has duplicates [7], Column 1 has duplicates [7], Minigrid 0 has duplicates [7]\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNoFileArgumentPassed() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                        SudokuPuzzleApp.main(new String[]{}),
                "");
    }

    public String getPath(String s) {
        return this.getClass().getClassLoader().getResource(s).getPath();
    }

    public SudokuGrid getGrid(String s) {
        return SudokuPuzzleApp.fromFile(getPath(s));
    }
}
