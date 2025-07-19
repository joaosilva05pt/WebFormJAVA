package tests;

import validators.Length;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testValidLength() {
        Length validator = new Length(5);
        assertDoesNotThrow(() -> validator.validate("validString"));
    }

    @Test
    void testInvalidLength() {
        Length validator = new Length(5);
        Exception exception = assertThrows(Exception.class, () -> validator.validate("short"));
        assertEquals("Length must be at least 5 characters", exception.getMessage());
    }

    @Test
    void testNullData() {
        Length validator = new Length(5);
        Exception exception = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Length must be at least 5 characters", exception.getMessage());
    }

    @Test
    void testEmptyString() {
        Length validator = new Length(5);
        Exception exception = assertThrows(Exception.class, () -> validator.validate(""));
        assertEquals("Length must be at least 5 characters", exception.getMessage());
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Length validator = new Length(3);

        
        assertDoesNotThrow(() -> validator.validate("abc"));

       
        Exception exception1 = assertThrows(Exception.class, () -> validator.validate("ab"));
        assertEquals("Length must be at least 3 characters", exception1.getMessage());

       
        Exception exception2 = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Length must be at least 3 characters", exception2.getMessage());

        
        Exception exception3 = assertThrows(Exception.class, () -> validator.validate(""));
        assertEquals("Length must be at least 3 characters", exception3.getMessage());
    }
}
