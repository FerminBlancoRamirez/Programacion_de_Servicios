package tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Cliente {
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
                int[] numerosCliente = new int[6];
                do {
                    System.out.println("Introduce los numeros de tu primitiva entre 1-49");
                    for (int i = 0; i < numerosCliente.length; i++) {
                        numerosCliente[i] = ServicioCliente2.pedirEnteroPositivo(sc);
                    }
                } while (ServicioCliente2.tieneDuplicados(numerosCliente) == true);
                flujoSalida.writeInt(numerosCliente.length);
                Arrays.sort(numerosCliente);
                for (int num : numerosCliente) {

                    flujoSalida.writeInt(num);
                }
                System.out.println("Número de reintegro generado");
                int reintegro = ServicioCliente2.generarReintegro();
                System.out.println(reintegro);
                flujoSalida.writeInt(reintegro);

                String respuesta = flujoEntrada.readUTF();
                System.out.println("Recibiendo un mensaje del servidor: " + respuesta);

                System.out.println("Si desea salir introduzca 0: ");
                int decision = ServicioCliente2.pedirEnteroPositivo(sc);
                if (decision == 0) {
                    System.out.println("Cerrando comunicacion");
                    break;
                }

                flujoSalida.flush();
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