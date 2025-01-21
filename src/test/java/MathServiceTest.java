
import org.example.MathService;
import org.example.NotFoundAnswerException;
import org.example.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MathServiceTest {

    private final MathService mathService = new MathService();

    @Test
    void testGetAnswer_whenDiscriminantIsNegative_shouldThrowNotFoundAnswerException() {
        int a = 1, b = 2, c = 3; // Дискриминант будет отрицательным

        Executable executable = () -> mathService.getAnswer(a, b, c);

        assertThrows(NotFoundAnswerException.class, executable, "Корни не могут быть найдены");
    }

    @Test
    void testGetAnswer_whenDiscriminantIsZero_shouldReturnEqualRoots() throws NotFoundAnswerException {
        int a = 1, b = -2, c = 1; // Дискриминант равен нулю

        Pair result = mathService.getAnswer(a, b, c);

        assertNotNull(result);
        assertEquals(result.first, result.second, "Корни должны быть равны");
    }

    @ParameterizedTest
    @CsvSource({
            "1, -3, 2, 2.0, 1.0", 
            "1, 2, 1, -1.0, -1.0",
    })
    void testGetAnswer_whenDiscriminantIsPositive_shouldReturnTwoRoots(int a, int b, int c, Double expectedFirstRoot, Double expectedSecondRoot) throws NotFoundAnswerException {
        
        Pair result = mathService.getAnswer(a, b, c);
        
        assertNotNull(result);
        assertEquals(expectedFirstRoot, result.first, "Первый корень должен быть верным");
        assertEquals(expectedSecondRoot, result.second, "Второй корень должен быть верным");
    }
}
