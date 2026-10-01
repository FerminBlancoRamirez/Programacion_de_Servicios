package tcp0;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class servidor1 {
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
            
            //Creamos las entradas
            InputStream entrada=cliente1.getInputStream();
            DataInputStream flujoEntrada= new DataInputStream(entrada);
            System.out.println("Recibiendo del cliente: "+flujoEntrada.readUTF());

            //Creamos el outputStream
            OutputStream salida=cliente1.getOutputStream();
            DataOutputStream flujoSalida=new DataOutputStream(salida);

            flujoSalida.writeUTF("Saludos al cliente desde el servidor");

            
            // Cierre explícito de sockets de los clientes
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            cliente1.close();
            cliente2.close();
            servidor.close();
            
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
        // El ServerSocket se cierra automáticamente al salir del bloque try
    }
}
