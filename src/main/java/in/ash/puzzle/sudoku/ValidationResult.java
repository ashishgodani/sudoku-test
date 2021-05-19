package in.ash.puzzle.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidationResult {

    private final List<Optional<String>> results;

    public ValidationResult() {
        results = new ArrayList<>();
    }

    public String getMessage() {
        return results.stream().anyMatch(Optional::isPresent) ? "INVALID" : "VALID";
    }

    public String getErrorText() {
        return Optional.of(results.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining(",")))
                .orElse("");
    }

    public void add(String validation_error) {
        results.add(Optional.ofNullable(validation_error));
    }
}
