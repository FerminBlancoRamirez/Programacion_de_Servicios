package tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try {
            Socket cliente = new Socket(host, puerto);
            Scanner sc = new Scanner(System.in);

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Conectado al servidor de Números Perfectos.");

            while (true) {
                System.out.println("Introduce un número entero positivo (0 para salir):");
                int entero = ServicioCliente.pedirEnteroPositivo(sc);

                flujoSalida.writeInt(entero);
                flujoSalida.flush();

                if (entero == 0) {
                    System.out.println("Cerrando comunicación...");
                    break;
                }

                boolean esPerfecto = flujoEntrada.readBoolean();
                if (esPerfecto) {
                    System.out.println("El número " + entero + " ES un número perfecto.");
                } else {
                    System.out.println("El número " + entero + " NO es un número perfecto.");
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            sc.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}