package tests;

import validators.NumberRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testValidNumberInRange() {
        NumberRange validator = new NumberRange(10, 20);
        assertDoesNotThrow(() -> validator.validate(15));
    }

    @Test
    void testNumberBelowRange() {
        NumberRange validator = new NumberRange(10, 20);
        Exception exception = assertThrows(Exception.class, () -> validator.validate(5));
        assertEquals("Error: value not in range", exception.getMessage());
    }

    @Test
    void testNumberAboveRange() {
        NumberRange validator = new NumberRange(10, 20);
        Exception exception = assertThrows(Exception.class, () -> validator.validate(25));
        assertEquals("Error: value not in range", exception.getMessage());
    }

    @Test
    void testNullValue() {
        NumberRange validator = new NumberRange(10, 20);
        Exception exception = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Error: value empty", exception.getMessage());
    }

    @Test
    void testValidNumberAsString() {
        NumberRange validator = new NumberRange(10, 20);
        assertDoesNotThrow(() -> validator.validate("15"));
    }

    @Test
    void testInvalidNumberAsString() {
        NumberRange validator = new NumberRange(10, 20);
        Exception exception = assertThrows(Exception.class, () -> validator.validate("abc"));
        assertTrue(exception instanceof NumberFormatException);
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        NumberRange validator = new NumberRange(10, 20);

       
        assertDoesNotThrow(() -> validator.validate(15));

       
        Exception exception1 = assertThrows(Exception.class, () -> validator.validate(5));
        assertEquals("Error: value not in range", exception1.getMessage());

        
        Exception exception2 = assertThrows(Exception.class, () -> validator.validate(25));
        assertEquals("Error: value not in range", exception2.getMessage());

        
        Exception exception3 = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Error: value empty", exception3.getMessage());

        
        assertDoesNotThrow(() -> validator.validate("15"));

        
        Exception exception4 = assertThrows(Exception.class, () -> validator.validate("abc"));
        assertTrue(exception4 instanceof NumberFormatException);
    }
}
