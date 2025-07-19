package tests;

import validators.Required;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequiredTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testValidData() {
        Required validator = new Required();
        assertDoesNotThrow(() -> validator.validate("some data"));
    }

    @Test
    void testNullData() {
        Required validator = new Required();
        Exception exception = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Error: value empty", exception.getMessage());
    }

    @Test
    void testEmptyString() {
        Required validator = new Required();
        Exception exception = assertThrows(Exception.class, () -> validator.validate(""));
        assertEquals("Error: value empty", exception.getMessage());
    }

    @Test
    void testWhitespaceString() {
        Required validator = new Required();
        Exception exception = assertThrows(Exception.class, () -> validator.validate("   "));
        assertEquals("Error: value empty", exception.getMessage());
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Required validator = new Required();

       
        assertDoesNotThrow(() -> validator.validate("valid data"));

       
        Exception exception1 = assertThrows(Exception.class, () -> validator.validate(null));
        assertEquals("Error: value empty", exception1.getMessage());

        
        Exception exception2 = assertThrows(Exception.class, () -> validator.validate(""));
        assertEquals("Error: value empty", exception2.getMessage());

       
        Exception exception3 = assertThrows(Exception.class, () -> validator.validate("   "));
        assertEquals("Error: value empty", exception3.getMessage());
    }
}
