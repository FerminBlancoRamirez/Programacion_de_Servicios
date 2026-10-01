package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente1 {
    public static void main(String[] args) {

        // Abrir socket usando try-with-resources para asegurar el cierre
        try {
            String host = "localhost";
            int puerto = 6000; // puerto remoto

            //ABRIR SOCKET
            Socket cliente = new Socket(host, puerto);
            
            InetAddress inetAddress = cliente.getInetAddress();

            DataOutputStream flujoSalida=new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF("Saludos al servidor desde el cliente");

            DataInputStream flujoEntrada=new DataInputStream(cliente.getInputStream());
            System.out.println("Recibiendo un mensaje del servidor "+ flujoEntrada.readUTF());
            
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("Host Remoto: " + inetAddress.getHostName());   
            System.out.println("IP Host Remoto: " + inetAddress.getHostAddress());
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
        // El socket se cierra automáticamente al salir del bloque try
    }

}
