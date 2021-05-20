package in.ash.puzzle.sudoku;

import java.util.stream.Stream;

import static in.ash.puzzle.sudoku.SudokuValidator.*;

public class SudokuPuzzleApp {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("INVALID");
            System.out.println("File path (argument) of csv sudoku grid file missing.");
            throw new IllegalArgumentException("File path (argument) of csv sudoku grid file missing.");
        }
        ValidationResult validate = SudokuPuzzleApp.validate(fromFile(args[0]));
        System.out.println(validate.getValidationMessage());
        System.out.println(validate.getErrorText());
    }

    public static SudokuGrid fromFile(String file) {
        return SudokuGridReader.readSudokuGridFromFile(file);
    }

    public static ValidationResult validate(SudokuGrid grid) {
        ValidationResult result = new ValidationResult();
        Stream.of(validateGridStructure(),
                validateGridElements(),
                validateRowsForDuplicates(),
                validateColumnsForDuplicates(),
                validateMiniGrids())
                .forEach(f -> f.apply(grid, result));
        return result;
    }

}
