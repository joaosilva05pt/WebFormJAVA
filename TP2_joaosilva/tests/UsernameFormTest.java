package tests;

import forms.UsernameForm;
import fields.StringField;
import fields.NumberField;
import validators.Length;
import validators.Required;
import validators.NumberRange;
import validators.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class UsernameFormTest {

    // -----------------------------
    // Testes Unitários
    // -----------------------------

    @Test
    void testValidateNoErrors() {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("johndoe");
        form.get("email").setData("john.doe@example.com");
        form.get("age").setData(30);

        form.validate();
        assertTrue(form.errors.isEmpty());
    }

    @Test
    void testValidateWithErrors() {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("jd");
        form.get("email").setData("");
        form.get("age").setData(15);

        form.validate();
        assertFalse(form.errors.isEmpty());
        assertEquals(3, form.errors.size());
        assertTrue(form.errors.contains("Error in field username: Length must be at least 3"));
        assertTrue(form.errors.contains("Error in field email: This field is required."));
        assertTrue(form.errors.contains("Error in field age: Number must be between 16 and 99"));
    }

    @Test
    void testRenderHTML() {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("johndoe");
        form.get("email").setData("john.doe@example.com");
        form.get("age").setData(30);

        String htmlOutput = form.renderHTML();
        assertTrue(htmlOutput.contains("Username"));
        assertTrue(htmlOutput.contains("johndoe"));
        assertTrue(htmlOutput.contains("Email"));
        assertTrue(htmlOutput.contains("john.doe@example.com"));
        assertTrue(htmlOutput.contains("Age"));
        assertTrue(htmlOutput.contains("30"));
    }

    @Test
    void testSaveAsHTML() {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("johndoe");
        form.get("email").setData("john.doe@example.com");
        form.get("age").setData(30);

        String filename = "username_form_output.html";
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
            assertTrue(htmlContent.contains("Username"));
            assertTrue(htmlContent.contains("johndoe"));
            assertTrue(htmlContent.contains("Email"));
            assertTrue(htmlContent.contains("john.doe@example.com"));
            assertTrue(htmlContent.contains("Age"));
            assertTrue(htmlContent.contains("30"));
        } catch (IOException e) {
            fail("Erro ao ler o arquivo HTML: " + e.getMessage());
        }
    }

    @Test
    void testJson() {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("johndoe");
        form.get("email").setData("john.doe@example.com");
        form.get("age").setData(30);

        String jsonOutput = form.json();
        assertTrue(jsonOutput.contains("\"username\": \"johndoe\""));
        assertTrue(jsonOutput.contains("\"email\": \"john.doe@example.com\""));
        assertTrue(jsonOutput.contains("\"age\": 30"));
    }

    // -----------------------------
    // Teste de Cenário
    // -----------------------------
    @Test
    void testFullScenario() throws Exception {
        UsernameForm form = new UsernameForm();
        form.get("username").setData("janedoe");
        form.get("email").setData("jane.doe@example.com");
        form.get("age").setData(25);

       
        assertDoesNotThrow(form::validate);
        assertTrue(form.errors.isEmpty());

       
        String htmlOutput = form.renderHTML();
        assertNotNull(htmlOutput);
        assertTrue(htmlOutput.contains("Username"));
        assertTrue(htmlOutput.contains("janedoe"));
        assertTrue(htmlOutput.contains("Email"));
        assertTrue(htmlOutput.contains("jane.doe@example.com"));
        assertTrue(htmlOutput.contains("Age"));
        assertTrue(htmlOutput.contains("25"));

        
        String filename = "username_form_scenario_output.html";
        form.saveAsHTML(filename);

       
        String jsonOutput = form.json();
        assertTrue(jsonOutput.contains("\"username\": \"janedoe\""));
        assertTrue(jsonOutput.contains("\"email\": \"jane.doe@example.com\""));
        assertTrue(jsonOutput.contains("\"age\": 25"));
    }
}
