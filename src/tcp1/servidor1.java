package tcp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class servidor1 {
    public static void main(String[] args) {
        int puerto = 6000;
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Escuchando en el puerto " + servidor.getLocalPort());

            // Esperando al primer cliente
            Socket cliente1 = servidor.accept();
            System.out.println("Cliente 1 conectado: " + cliente1.getInetAddress());
            
            DataInputStream flujoEntrada = new DataInputStream(cliente1.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente1.getOutputStream());
            Scanner sc = new Scanner(System.in);


            while (true) {
                // 1. Recibir del cliente
                String mensajeCliente = flujoEntrada.readUTF();
                System.out.println("Recibiendo del cliente: " + mensajeCliente);
                System.out.println("Si quieres terminar la comunicacion escribe -> salir");
                if (mensajeCliente.equalsIgnoreCase("Salir")) {
                    System.out.println("El cliente ha cerrado la conexion");
                    break;
                }

                // 2. Enviar respuesta al cliente
                System.out.print("Escribe una cadena para mandar al cliente: ");
                String cad = sc.nextLine();
                flujoSalida.writeUTF(cad);
                if (cad.equalsIgnoreCase("salir")) {
                    System.out.println("Se cierra conexion");
                    break;
                }
            }

            // Cierre explícito de sockets de los clientes
            //entrada.close();
            //flujoEntrada.close();
            //salida.close();
            //flujoSalida.close();
            //cliente1.close();
            //cliente2.close();
            //servidor.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
        // El ServerSocket se cierra automáticamente al salir del bloque try
    }
}
