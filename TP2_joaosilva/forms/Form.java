package forms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import fields.Field;

public abstract class Form extends HashMap<String, Field<?>> {
    public List<String> errors = new ArrayList<>();

   
    public void validate() {
        errors.clear(); 
        for (Entry<String, Field<?>> entry : this.entrySet()) {
            Field<?> field = entry.getValue();
            try {
                field.validate(); 
            } catch (Exception e) {
                errors.add("Error in field " + entry.getKey() + ": " + e.getMessage());
            }
        }

    }

    
    public String renderHTML() {
        StringBuilder html = new StringBuilder("<form>\n");
        for (Entry<String, Field<?>> entry : entrySet()) {
            html.append(entry.getValue().renderHTML()).append("<br>\n");
        }
        html.append("</form>");
        return html.toString();
    }

   
    public void saveAsHTML(String filename) {
        String htmlContent = renderHTML();
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n<head>\n<title>Form Output</title>\n</head>\n<body>\n");
            writer.write(htmlContent);
            writer.write("\n</body>\n</html>");
            System.out.println("Arquivo HTML criado: " + filename);
        } catch (IOException e) {
            System.err.println("Erro a escrever o arquivo: " + e.getMessage());
        }
    }

    public String json() {
        StringBuilder jsonBuilder = new StringBuilder("{");
    
        boolean isFirst = true;
        for (Entry<String, Field<?>> entry : entrySet()) {
            if (!isFirst) {
                jsonBuilder.append(", ");
            } else {
                isFirst = false;
            }
    
            String key = entry.getKey();
            Object value = entry.getValue().getData();
    
           
            jsonBuilder.append("\"").append(key).append("\": ");
            if (value instanceof String) {
                jsonBuilder.append("\"").append(value).append("\"");
            } else {
                jsonBuilder.append(value);
            }
        }
    
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
    
}
