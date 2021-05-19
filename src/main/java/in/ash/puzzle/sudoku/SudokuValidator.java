package in.ash.puzzle.sudoku;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public interface SudokuValidator extends BiFunction<SudokuGrid, ValidationResult, ValidationResult> {

    Function<int[], int[]> duplicates = elements -> range(1, 9)
            .filter(i -> stream(elements).filter(n -> i == n).count() > 1)
            .toArray();

    static SudokuValidator validateGridStructure() {
        return ((sudokuGrid, validationResult) -> {
            if (sudokuGrid.getNumberOfRows() != 9 || sudokuGrid.getNumberOfColumns() != 9) {
                validationResult.add("Grid should be 9 rows and 9 columns.");
            }
            return validationResult;
        });
    }

    static SudokuValidator validateRowsForDuplicates() {
        return ((sudokuGrid, validationResult) -> {
            range(0, sudokuGrid.getNumberOfRows())
                    .forEach(rowIndex -> {
                        int[] duplicateElements = duplicates.apply(sudokuGrid.getRowElements(rowIndex));
                        if (duplicateElements.length > 0)
                            validationResult.add(
                                    format("Row %s has duplicates %s", rowIndex, Arrays.toString(duplicateElements)));
                    });
            return validationResult;
        });
    }

    static SudokuValidator validateGridElements() {
        return ((sudokuGrid, validationResult) -> {
            range(0, sudokuGrid.getNumberOfRows())
                    .forEach(rowIndex -> {
                        int[] invalidElements = stream(sudokuGrid.getRowElements(rowIndex)).filter(i -> i > 9).toArray();
                        if (invalidElements.length > 0)
                            validationResult.add(
                                    format("Row %s has invalid %s", rowIndex, Arrays.toString(invalidElements)));
                    });
            return validationResult;
        });
    }

    static SudokuValidator validateColumnsForDuplicates() {
        return ((sudokuGrid, validationResult) -> {
            range(0, sudokuGrid.getNumberOfColumns()).forEach(columnIndex -> {
                int[] duplicateElements = duplicates.apply(sudokuGrid.getColumnElements(columnIndex));
                if (duplicateElements.length > 0)
                    validationResult.add(
                            format("Column %s has duplicates %s", columnIndex, Arrays.toString(duplicateElements)));
            });
            return validationResult;
        });
    }

    static SudokuValidator validateMiniGrids() {
        return ((sudokuGrid, validationResult) -> {
            range(0, 9).forEach(miniGridIndex -> {
                int[] duplicateElements = duplicates.apply(sudokuGrid.getMiniGrid(miniGridIndex));
                if (duplicateElements.length > 0)
                    validationResult.add(
                            format("Minigrid %s has duplicates %s", miniGridIndex, Arrays.toString(duplicateElements)));
            });
            return validationResult;
        });
    }
}
