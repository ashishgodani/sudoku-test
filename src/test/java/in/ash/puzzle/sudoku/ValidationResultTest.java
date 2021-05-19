package in.ash.puzzle.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationResultTest {

    @Test
    public void shouldAddValidationMessagesAndBuildErrorText() {
        ValidationResult result = new ValidationResult();
        result.add("error1");
        result.add("error2");
        assertEquals("error1,error2", result.getErrorText());
    }

    @Test
    public void shouldReturnValidResultWithNoErrors() {
        ValidationResult result = new ValidationResult();
        assertEquals("VALID", result.getMessage());
    }

    @Test
    public void shouldReturnInvalidWhenResultHasErrors() {
        ValidationResult result = new ValidationResult();
        result.add("err");
        assertEquals("INVALID", result.getMessage());
    }
}
