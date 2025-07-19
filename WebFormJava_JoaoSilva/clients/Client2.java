package clients;

import java.util.Scanner;

import forms.UsernameForm;

public class Client2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsernameForm form = new UsernameForm();

        form.get("username").setData("ti");
        form.get("email").setData("");
        form.get("age").setData(13);

        form.validate();

        for (String err : form.errors) {
            System.out.println(err);
        }

        // Exibe no console
        System.out.println(form.renderHTML());
        System.out.println(form.json());

        // Salva em um arquivo HTML
        
         // Salva em um arquivo HTML

         System.out.print("Deseja criar o arquivo Html (1- SIM ; 2- NAO): ");
         int arquivohtml = scanner.nextInt();
         
         if (arquivohtml == 1) {
 
             form.saveAsHTML("client2.html");
         }
         scanner.close();
    }
}
