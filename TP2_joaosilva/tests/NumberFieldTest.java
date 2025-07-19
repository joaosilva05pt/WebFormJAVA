package tests;

import fields.NumberField;
import validators.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberFieldTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testSetAndGetValidData() {
        NumberField field = new NumberField("Age", new Validator[]{});
        field.setData(25);
        assertEquals(25, field.getData());
    }

    @Test
    void testInvalidDataType() {
        NumberField field = new NumberField("Age", new Validator[]{});
        assertThrows(IllegalArgumentException.class, () -> field.setData("invalid")); // Tenta setar uma String em um campo Integer
    }

    @Test
    void testValidationPasses() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if ((Integer) data < 18) {
                    throw new Exception("Minimum age is 18.");
                }
            }
        };

        NumberField field = new NumberField("Age", new Validator[]{mockValidator});
        field.setData(21);
        assertDoesNotThrow(field::validate);
    }

    @Test
    void testValidationFails() {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if ((Integer) data < 18) {
                    throw new Exception("Minimum age is 18.");
                }
            }
        };

        NumberField field = new NumberField("Age", new Validator[]{mockValidator});
        field.setData(16);

        Exception exception = assertThrows(Exception.class, field::validate);
        assertEquals("Minimum age is 18.", exception.getMessage());
    }

    @Test
    void testRenderHTML() {
        NumberField field = new NumberField("Age", new Validator[]{});
        field.setData(30);
        String htmlOutput = field.renderHTML();
        assertTrue(htmlOutput.contains("Age"));
        assertTrue(htmlOutput.contains("30"));
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if ((Integer) data < 18) {
                    throw new Exception("Minimum age is 18.");
                }
            }
        };

        NumberField field = new NumberField("Age", new Validator[]{mockValidator});
        field.setData(21);

       
        assertDoesNotThrow(field::validate);

        
        String htmlOutput = field.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Age"));
        assertTrue(htmlOutput.contains("21"));
    }
}
