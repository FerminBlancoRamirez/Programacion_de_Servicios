package tcp2;

import java.util.Scanner;

public class ServicioCliente {

    public static int pedirEnteroPositivo(Scanner sc) {
        int numero;
        do {
            while (!sc.hasNextInt()) {
                sc.next();
            }
            numero = sc.nextInt();
        } while (numero < 0);
        return numero;
    }
}