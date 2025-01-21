
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
        // Arrange
        int a = 1, b = 2, c = 3; // Дискриминант будет отрицательным

        // Act
        Executable executable = () -> mathService.getAnswer(a, b, c);

        // Assert
        assertThrows(NotFoundAnswerException.class, executable, "Корни не могут быть найдены");
    }

    @Test
    void testGetAnswer_whenDiscriminantIsZero_shouldReturnEqualRoots() throws NotFoundAnswerException {
        // Arrange
        int a = 1, b = -2, c = 1; // Дискриминант равен нулю

        // Act
        Pair result = mathService.getAnswer(a, b, c);

        // Assert
        assertNotNull(result);
        assertEquals(result.first, result.second, "Корни должны быть равны");
    }

    @ParameterizedTest
    @CsvSource({
            "1, -3, 2, 2.0, 1.0", // Пример с двумя корнями
            "1, 2, 1, -1.0, -1.0", // Пример с одинаковыми корнями
    })
    void testGetAnswer_whenDiscriminantIsPositive_shouldReturnTwoRoots(int a, int b, int c, Double expectedFirstRoot, Double expectedSecondRoot) throws NotFoundAnswerException {
        // Act
        Pair result = mathService.getAnswer(a, b, c);

        // Assert
        assertNotNull(result);
        assertEquals(expectedFirstRoot, result.first, "Первый корень должен быть верным");
        assertEquals(expectedSecondRoot, result.second, "Второй корень должен быть верным");
    }
}
