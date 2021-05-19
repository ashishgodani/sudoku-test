package in.ash.puzzle.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public class SudokuGridReader {

    public static SudokuGrid readSudokuGridFromFile(String file) throws SudokuGridIOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            System.out.println("INVALID");
            throw new SudokuGridIOException(format("File %s not found", file));
        }
        List<int[]> grid = new ArrayList<>();
        while (scanner.hasNext()) {
            grid.add(stream(scanner.next().split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray()
            );
        }

        return new SudokuGrid(grid.toArray(new int[grid.size()][9]));
    }
}
