package tcp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000; // puerto remoto
        // Abrir socket usando try-with-resources para asegurar el cierre
        try {
            // ABRIR SOCKET
            Socket cliente = new Socket(host, puerto);
            Scanner sc = new Scanner(System.in);

            InetAddress inetAddress = cliente.getInetAddress();

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Te has conectado al servidor");

            while (true) {
                System.out.println("Escribe una cadena para mandar al servidor");
                System.out.println("Si quieres terminar la comunicacion escribe -> salir");
                String cad = sc.nextLine();
                flujoSalida.writeUTF(cad);
                flujoSalida.flush();
                if (cad.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando cliente");
                    break;
                }
                // 2. Leer respuesta del servidor
                String respuesta = flujoEntrada.readUTF();
                System.out.println("Recibiendo un mensaje del servidor: " + respuesta);
                if (respuesta.equalsIgnoreCase("salir")) {
                    System.out.println("El servidor ha cerrado la comunicacion");
                    break;
                }
            }

            // System.out.println("Puerto local: " + cliente.getLocalPort());
            // System.out.println("Puerto Remoto: " + cliente.getPort());
            // System.out.println("Host Remoto: " + inetAddress.getHostName());
            // System.out.println("IP Host Remoto: " + inetAddress.getHostAddress());
            // flujoEntrada.close();
            // flujoSalida.close();
            // cliente.close();
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
        // El socket se cierra automáticamente al salir del bloque try
    }

}
