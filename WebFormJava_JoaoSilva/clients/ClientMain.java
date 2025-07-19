package clients;

import java.util.Scanner;
import forms.UsernameForm;
import fields.PasswordField;
import validators.Validator;

public class ClientMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação do formulário
        UsernameForm form = new UsernameForm();

        // Adicionando manualmente o campo de senha
        form.put("password", new PasswordField("Password", new Validator[]{}));

        System.out.println("Preencha os dados do formulário:");

        // Entrada de dados pelo usuário
        System.out.print("Username: ");
        form.get("username").setData(scanner.nextLine());

        System.out.print("Email: ");
        String email = scanner.nextLine();


        System.out.print("Age: ");
        form.get("age").setData(scanner.nextInt());
        scanner.nextLine(); // Consumir o restante da linha após nextInt()

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!email.matches(".*@.*\\..*")) { 
            System.out.println("Erro: O email deve conter pelo menos um '@' e um ponto."); 
            System.exit(1); }
        else {
            form.get("email").setData(email);
        }
        


        // Validação manual da senha
        if (password.length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            System.exit(1);
        } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Erro: A senha deve conter pelo menos uma letra maiúscula.");
            System.exit(1);
        } else if (!password.matches(".*[a-z].*")) {
            System.out.println("Erro: A senha deve conter pelo menos uma letra minúscula.");
            System.exit(1);
        } else if (!password.matches(".*\\d.*")) {
            System.out.println("Erro: A senha deve conter pelo menos um número.");
            System.exit(1);
        } else if (!password.matches(".*[@$!%*?&].*")) {
            System.out.println("Erro: A senha deve conter pelo menos um caractere especial (@$!%*?&).");
            System.exit(1);
        } else {
            // Caso a senha seja válida, adicioná-la ao formulário
            form.get("password").setData(password);
        }

        // Validação do restante do formulário
        form.validate();

        // Exibição de erros
        if (!form.errors.isEmpty()) {
            System.out.println("Erros no formulário:");
            for (String err : form.errors) {
                System.out.println(err);
            }
        } else {
            System.out.println("Formulário válido!");
        }

        // Exibição dos dados inseridos
        System.out.println("HTML Gerado:");
        System.out.println(form.renderHTML());

        System.out.println("JSON Gerado:");
        System.out.println(form.json());

        // Perguntar se o utilizador deseja salvar o arquivo HTML
        System.out.print("Deseja criar o arquivo HTML? (1- SIM; 2- NÃO): ");
        int criarHtml = scanner.nextInt();
        if (criarHtml == 1) {
            form.saveAsHTML("clientMain.html");
        }

        scanner.close();
    }
}
