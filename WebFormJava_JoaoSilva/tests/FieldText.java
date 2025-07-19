package tests;

import fields.Field;
import validators.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

   
    static class TextField extends Field<String> {

        public TextField(String label, Validator[] validators) {
            super(label, validators);
        }

        @Override
        public String renderHTML() {
            return "<label>" + getLabel() + "</label><input type='text' value='" + getData() + "' />";
        }
    }

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testSetAndGetValidData() {
        Field<String> field = new TextField("Username", new Validator[]{});
        field.setData("testuser");
        assertEquals("testuser", field.getData());
    }

    @Test
    void testInvalidDataType() {
        Field<String> field = new TextField("Username", new Validator[]{});
        assertThrows(IllegalArgumentException.class, () -> field.setData(123)); // Tenta setar número em um campo String
    }

    @Test
    void testValidationPasses() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 5) {
                    throw new Exception("Minimum 5 characters required.");
                }
            }
        };

        Field<String> field = new TextField("Username", new Validator[]{mockValidator});
        field.setData("validUser");
        assertDoesNotThrow(field::validate);
    }

    @Test
    void testValidationFails() {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 5) {
                    throw new Exception("Minimum 5 characters required.");
                }
            }
        };

        Field<String> field = new TextField("Username", new Validator[]{mockValidator});
        field.setData("usr");

        Exception exception = assertThrows(Exception.class, field::validate);
        assertEquals("Minimum 5 characters required.", exception.getMessage());
    }

    @Test
    void testRenderHTML() {
        Field<String> field = new TextField("Username", new Validator[]{});
        field.setData("user123");
        String htmlOutput = field.renderHTML();
        assertTrue(htmlOutput.contains("Username"));
        assertTrue(htmlOutput.contains("user123"));
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 5) {
                    throw new Exception("Minimum 5 characters required.");
                }
            }
        };

        Field<String> field = new TextField("Username", new Validator[]{mockValidator});
        field.setData("validUser");

        
        assertDoesNotThrow(field::validate);

        
        String htmlOutput = field.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Username"));
        assertTrue(htmlOutput.contains("validUser"));
    }
}
