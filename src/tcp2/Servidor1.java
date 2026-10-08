package tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Pepe esta en 10.101.3.36

public class Servidor1 {
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


            while (true) {
                // 1. Recibir del cliente
                String mensajeCliente = flujoEntrada.readUTF();
                System.out.println("Recibiendo del cliente: " + mensajeCliente);
                if (mensajeCliente.equalsIgnoreCase("Salir")) {
                    System.out.println("El cliente ha cerrado la conexion");
                    break;
                }

                // 2. Enviar respuesta al cliente
                String cadenaDeCliente=pasarAMayuscula(mensajeCliente);
                flujoSalida.writeUTF(cadenaDeCliente);
            }

            // Cierre explícito de sockets de los clientes
            flujoEntrada.close();
            flujoSalida.close();
            cliente1.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
        // El ServerSocket se cierra automáticamente al salir del bloque try
    }

    public static  String pasarAMayuscula(String string){
        String enMayusuclas=string.toUpperCase();
        return enMayusuclas;
    }
}
