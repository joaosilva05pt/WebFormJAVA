package clients;

import java.util.Scanner;

import forms.UsernameForm;

public class Client1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        UsernameForm form = new UsernameForm();

        form.get("username").setData("tia");
        form.get("email").setData("tia@gmail.com");
        form.get("age").setData(16);

        form.validate();

        for (String err : form.errors) {
            System.out.println(err);
        }

        // Exibe no console
        System.out.println(form.renderHTML());
        System.out.println(form.json());

        // Salva em um arquivo HTML

        System.out.print("Deseja criar o arquivo Html (1- SIM ; 2- NAO): ");
        int arquivohtml = scanner.nextInt();
        
        if (arquivohtml == 1) {

            form.saveAsHTML("client1.html");
        }
        scanner.close();
    }
}
