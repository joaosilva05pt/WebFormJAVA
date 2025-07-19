package tests;

import fields.StringField;
import validators.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFieldTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testSetAndGetValidData() {
        StringField field = new StringField("Name", new Validator[]{});
        field.setData("John Doe");
        assertEquals("John Doe", field.getData());
    }

    @Test
    void testInvalidDataType() {
        StringField field = new StringField("Name", new Validator[]{});
        assertThrows(IllegalArgumentException.class, () -> field.setData(123)); // Tenta setar um número em um campo String
    }

    @Test
    void testValidationPasses() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).isEmpty()) {
                    throw new Exception("Field cannot be empty.");
                }
            }
        };

        StringField field = new StringField("Name", new Validator[]{mockValidator});
        field.setData("John Doe");
        assertDoesNotThrow(field::validate);
    }

    @Test
    void testValidationFails() {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).isEmpty()) {
                    throw new Exception("Field cannot be empty.");
                }
            }
        };

        StringField field = new StringField("Name", new Validator[]{mockValidator});
        field.setData("");

        Exception exception = assertThrows(Exception.class, field::validate);
        assertEquals("Field cannot be empty.", exception.getMessage());
    }

    @Test
    void testRenderHTML() {
        StringField field = new StringField("Name", new Validator[]{});
        field.setData("John Doe");
        String htmlOutput = field.renderHTML();
        assertTrue(htmlOutput.contains("Name"));
        assertTrue(htmlOutput.contains("John Doe"));
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).isEmpty()) {
                    throw new Exception("Field cannot be empty.");
                }
            }
        };

        StringField field = new StringField("Name", new Validator[]{mockValidator});
        field.setData("Jane Doe");

        
        assertDoesNotThrow(field::validate);

        
        String htmlOutput = field.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Name"));
        assertTrue(htmlOutput.contains("Jane Doe"));
    }
}
