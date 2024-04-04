import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {
    @Mock
    private CalculatorRepository calculatorRepository;

    @InjectMocks
    Calculator calculator;

    @Test
    public void testAddUsingRepository() {
        // Given
        int[] operands = { 1, 2 };
        when(calculatorRepository.getOperands()).thenReturn(operands);
        // When
        int result = calculator.addUsingRepository(calculatorRepository);
        // Then
        assertTrue(result == 3);
        verify(calculatorRepository, times(1)).getOperands();
    }

    @Test
    public void testAdd() {
        // Given
        int a = 1;
        int b = 11;
        // When
        int result = new Calculator().add(a, b);
        // Then
        assertTrue(result == 12);
    }

}