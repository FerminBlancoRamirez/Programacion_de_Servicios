package tcp0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor0 {
    public static void main(String[] args) {

        try {
            int puerto = 6000;
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Escuchando en el puerto " + servidor.getLocalPort());

            // Esperando al primer cliente
            Socket cliente1 = servidor.accept();
            System.out.println("Cliente 1 conectado: " + cliente1.getInetAddress());
            // Realizar acciones con cliente1
            // Crear IO streams

            // Esperando al segundo cliente
            Socket cliente2 = servidor.accept();
            System.out.println("Cliente 2 conectado: " + cliente2.getInetAddress());
            // Realizar acciones con cliente2
            // Crear IO streams

            // Cierre explícito de sockets de los clientes
            cliente1.close();
            cliente2.close();
            servidor.close();
            
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
        // El ServerSocket se cierra automáticamente al salir del bloque try
    }
}
