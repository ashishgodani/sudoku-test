package in.ash.puzzle.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidationResult {

    private final List<Optional<String>> errors;

    public ValidationResult() {
        errors = new ArrayList<>();
    }

    public String getValidationMessage() {
        return errors.stream().anyMatch(Optional::isPresent) ? "INVALID" : "VALID";
    }

    public String getErrorText() {
        return Optional.of(errors.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining(", ")))
                .orElse("");
    }

    public void add(String error) {
        errors.add(Optional.ofNullable(error));
    }
}
