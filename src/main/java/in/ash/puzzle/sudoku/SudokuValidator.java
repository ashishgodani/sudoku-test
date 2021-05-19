package in.ash.puzzle.sudoku;

import java.util.Arrays;
import java.util.function.BiFunction;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public interface SudokuValidator extends BiFunction<SudokuGrid, ValidationResult, ValidationResult> {

    static SudokuValidator validateGridStructure() {
        return ((sudokuGrid, validationResult) -> {
            if (sudokuGrid.getNumberOfRows() != 9 || sudokuGrid.getNumberOfColumns() != 9) {
                validationResult.add("Grid should be 9 rows and 9 columns.");
            }
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
                                    format("Row %s has invalid elements %s", rowIndex, Arrays.toString(invalidElements)));
                    });
            return validationResult;
        });
    }

    private static int[] checkDuplicates(int[] elements) {
        return range(1, 9)
                .filter(i -> stream(elements).filter(n -> i == n).count() > 1)
                .toArray();
    }

    private static String buildMsg(int[] duplicateElements, int index, String s) {
        return duplicateElements.length > 0 ? format(s, index, Arrays.toString(duplicateElements)) : null;
    }

    static SudokuValidator validateRowsForDuplicates() {
        return ((grid, result) -> {
            range(0, grid.getNumberOfRows())
                    .forEach(index -> result.add(buildMsg(
                            checkDuplicates(grid.getRowElements(index)), index, "Row %s has duplicates %s")));
            return result;
        });
    }

    static SudokuValidator validateColumnsForDuplicates() {
        return ((grid, result) -> {
            range(0, grid.getNumberOfColumns())
                    .forEach(index -> result.add(buildMsg(
                            checkDuplicates(grid.getColumnElements(index)), index, "Column %s has duplicates %s")));
            return result;
        });
    }

    static SudokuValidator validateMiniGrids() {
        return ((grid, result) -> {
            range(0, 9).forEach(index -> result.add(buildMsg(
                    checkDuplicates(grid.getMiniGrid(index)), index, "Minigrid %s has duplicates %s")));
            return result;
        });
    }
}
