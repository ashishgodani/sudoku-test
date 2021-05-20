package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SudokuValidatorTest {

    @Test
    public void gridWithNineRowsAndNineColumnsShouldBeValid() {
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridStructure().apply(new SudokuGrid(new int[9][9]), result);

        assertEquals("VALID", result.getValidationMessage());
    }

    @Test
    public void gridWithMoreThanNineColumnsShouldBeInvalid() {
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridStructure().apply(new SudokuGrid(new int[9][10]), result);

        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Grid should be 9 rows and 9 columns.", result.getErrorText());
    }

    @Test
    public void gridWithMoreThanNineRowsShouldBeInvalid() {
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridStructure().apply(new SudokuGrid(new int[10][9]), result);

        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Grid should be 9 rows and 9 columns.", result.getErrorText());
    }

    @Test
    public void gridWithLessThanNineColumnsShouldBeInvalid() {
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridStructure().apply(new SudokuGrid(new int[10][]), result);

        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Grid should be 9 rows and 9 columns.", result.getErrorText());
    }

    @Test
    public void gridWithDuplicateElementsInRowsShouldBeInvalid() {

        SudokuGrid gridWithDuplicates = new SudokuGrid(new int[][]{
                {7, 7, 2, 1, 5, 4, 3, 8, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 9}
        });
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateRowsForDuplicates().apply(gridWithDuplicates, result);

        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Row 0 has duplicates [7]", result.getErrorText());
    }

    @Test
    public void gridWithDuplicateElementsInColumnsShouldBeInvalid() {
        ValidationResult result = new ValidationResult();
        SudokuGrid gridWithDuplicates = new SudokuGrid(new int[][]{
                {7, 7, 2, 1, 5, 4, 3, 5, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 6}
        });
        SudokuValidator.validateColumnsForDuplicates().apply(gridWithDuplicates, result);

        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Column 7 has duplicates [5], Column 8 has duplicates [6]", result.getErrorText());
    }

    @Test
    public void validGridShouldHaveOnlySingleDigitElements() {
        SudokuGrid validGrid = new SudokuGrid(new int[][]{
                {7, 9, 2, 1, 5, 4, 3, 8, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 9}
        });
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridElements().apply(validGrid, result);
        assertEquals("VALID", result.getValidationMessage());
    }

    @Test
    public void gridWithElementsGreaterThanNineShouldBeInvalid() {
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateGridElements().apply(new SudokuGrid(new int[][]{{10, 11, 12, 12, 13, 15, 18, 9, 1}}), result);
        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Row 0 has invalid elements [10, 11, 12, 12, 13, 15, 18]", result.getErrorText());
    }

    @Test
    public void mini3X3GridsWithoutAnyDuplicatesShouldBeValid() {
        SudokuGrid validGrid = new SudokuGrid(new int[][]{{7, 9, 2, 1, 5, 4, 3, 8, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 9},
                {8, 5, 1, 3, 9, 6, 7, 2, 4},
                {2, 6, 5, 9, 7, 3, 8, 4, 1},
                {4, 8, 9, 5, 6, 1, 2, 7, 3},
                {3, 1, 7, 4, 8, 2, 9, 6, 5},
                {1, 3, 6, 7, 4, 8, 5, 9, 2},
                {9, 7, 4, 2, 1, 5, 6, 3, 8},
                {5, 2, 8, 6, 3, 9, 4, 1, 7}});
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateMiniGrids().apply(validGrid, result);
        assertEquals("VALID", result.getValidationMessage());
    }

    @Test
    public void mini3X3GridsWithDuplicatesShouldBeInvalid() {
        SudokuGrid validGrid = new SudokuGrid(new int[][]{{7, 7, 2, 1, 5, 4, 3, 8, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 9},
                {8, 5, 1, 3, 9, 6, 7, 2, 4},
                {2, 6, 5, 9, 7, 3, 8, 4, 1},
                {4, 8, 9, 5, 6, 1, 2, 7, 3},
                {3, 1, 7, 4, 8, 2, 9, 6, 5},
                {1, 3, 6, 7, 4, 8, 5, 9, 2},
                {9, 7, 4, 2, 1, 5, 6, 8, 8},
                {5, 2, 8, 6, 3, 9, 4, 1, 7}});
        ValidationResult result = new ValidationResult();
        SudokuValidator.validateMiniGrids().apply(validGrid, result);
        assertEquals("INVALID", result.getValidationMessage());
        assertEquals("Minigrid 0 has duplicates [7], Minigrid 8 has duplicates [8]", result.getErrorText());
    }
}