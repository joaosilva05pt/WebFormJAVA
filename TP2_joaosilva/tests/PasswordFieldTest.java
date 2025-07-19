package tests;

import fields.PasswordField;
import validators.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordFieldTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testSetAndGetValidData() {
        PasswordField field = new PasswordField("Password", new Validator[]{});
        field.setData("strongpassword123@A");
        assertEquals("strongpassword123@A", field.getData());
    }

    @Test
    void testInvalidDataType() {
        PasswordField field = new PasswordField("Password", new Validator[]{});
        assertThrows(IllegalArgumentException.class, () -> field.setData(12345)); // Tenta setar um número em um campo String
    }

    @Test
    void testValidationPasses() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 8) {
                    throw new Exception("Password must be at least 8 characters long.");
                }
            }
        };

        PasswordField field = new PasswordField("Password", new Validator[]{mockValidator});
        field.setData("validpassword");
        assertDoesNotThrow(field::validate);
    }

    @Test
    void testValidationFails() {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 8) {
                    throw new Exception("Password must be at least 8 characters long.");
                }
            }
        };

        PasswordField field = new PasswordField("Password", new Validator[]{mockValidator});
        field.setData("short");

        Exception exception = assertThrows(Exception.class, field::validate);
        assertEquals("Password must be at least 8 characters long.", exception.getMessage());
    }

    @Test
    void testRenderHTML() {
        PasswordField field = new PasswordField("Password", new Validator[]{});
        field.setData("password123A@");
        String htmlOutput = field.renderHTML();
        assertTrue(htmlOutput.contains("Password"));
        assertTrue(htmlOutput.contains("password123"));
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).length() < 8) {
                    throw new Exception("Password must be at least 8 characters long.");
                }
            }
        };

        PasswordField field = new PasswordField("Password", new Validator[]{mockValidator});
        field.setData("validpassword");

       
        assertDoesNotThrow(field::validate);

        
        String htmlOutput = field.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Password"));
        assertTrue(htmlOutput.contains("validpassword"));
    }
}
