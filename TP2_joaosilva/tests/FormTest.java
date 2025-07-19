package tests;

import fields.Field;
import fields.StringField;
import forms.Form;
import validators.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class FormTest {

    static class TestForm extends Form {
        public TestForm() {
            this.put("name", new StringField("Name", new Validator[]{}));
            this.put("email", new StringField("Email", new Validator[]{}));
        }
    }

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testValidateNoErrors() {
        Form form = new TestForm();
        form.get("name").setData("John Doe");
        form.get("email").setData("john.doe@example.com");

        form.validate();
        assertTrue(form.errors.isEmpty());
    }

    @Test
    void testValidateWithErrors() {
        Validator mockValidator = new Validator() {
            @Override
            public void validate(Object data) throws Exception {
                if (((String) data).isEmpty()) {
                    throw new Exception("Field cannot be empty.");
                }
            }
        };

        Form form = new TestForm();
        form.get("name").setData("");
        form.get("email").setData("john.doe@example.com");
        
        form.validate();
        assertFalse(form.errors.isEmpty());
        assertEquals(1, form.errors.size());
        assertEquals("Error in field name: Field cannot be empty.", form.errors.get(0));
    }

    @Test
    void testRenderHTML() {
        Form form = new TestForm();
        form.get("name").setData("John Doe");
        form.get("email").setData("john.doe@example.com");

        String htmlOutput = form.renderHTML();
        assertTrue(htmlOutput.contains("Name"));
        assertTrue(htmlOutput.contains("John Doe"));
        assertTrue(htmlOutput.contains("Email"));
        assertTrue(htmlOutput.contains("john.doe@example.com"));
    }

    @Test
    void testSaveAsHTML() {
        Form form = new TestForm();
        form.get("name").setData("John Doe");
        form.get("email").setData("john.doe@example.com");

        String filename = "form_output.html";
        form.saveAsHTML(filename);

        
        try {
            StringBuilder content = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            String htmlContent = content.toString();
            assertTrue(htmlContent.contains("Name"));
            assertTrue(htmlContent.contains("John Doe"));
            assertTrue(htmlContent.contains("Email"));
            assertTrue(htmlContent.contains("john.doe@example.com"));
        } catch (IOException e) {
            fail("Erro ao ler o arquivo HTML: " + e.getMessage());
        }
    }

    @Test
    void testJson() {
        Form form = new TestForm();
        form.get("name").setData("John Doe");
        form.get("email").setData("john.doe@example.com");

        String jsonOutput = form.json();
        assertTrue(jsonOutput.contains("\"name\": \"John Doe\""));
        assertTrue(jsonOutput.contains("\"email\": \"john.doe@example.com\""));
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

        Form form = new TestForm();
        form.get("name").setData("Jane Doe");
        form.get("email").setData("jane.doe@example.com");
       

        
        assertDoesNotThrow(form::validate);

        
        String htmlOutput = form.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Name"));
        assertTrue(htmlOutput.contains("Jane Doe"));
        assertTrue(htmlOutput.contains("Email"));
        assertTrue(htmlOutput.contains("jane.doe@example.com"));

        
        String filename = "form_scenario_output.html";
        form.saveAsHTML(filename);

       
        String jsonOutput = form.json();
        assertTrue(jsonOutput.contains("\"name\": \"Jane Doe\""));
        assertTrue(jsonOutput.contains("\"email\": \"jane.doe@example.com\""));
    }
}
