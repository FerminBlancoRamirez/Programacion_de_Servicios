package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Servidor1 {

    public static void main(String[] args) {
        int puertoEscucha = 12346;
        int puertoEnvio = 12345;
        byte[] buffer = new byte[1024];
        String paquete = "";
        String mensaje = "";

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Esperando datagrama en el puerto " + puertoEscucha + "...");
            InetAddress destino = InetAddress.getLocalHost();

            while (!paquete.equals("salir") && !mensaje.equals("salir")) {
                DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibo); // Se bloquea hasta recibir un paquete

                // 1. Obtener la longitud del mensaje recibido
                int bytesRec = recibo.getLength();

                // 2. Convertir solo los bytes útiles recibidos a String usando UTF-8
                paquete = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

                // 3. Imprimir los datos recibidos
                System.out.println("Mensaje recibido: " + paquete);
                System.out.println("Puerto local de recepcion del mensaje: " + socket.getLocalPort());

                System.out.println("Escribe tu mensaje: ");
                mensaje = sc.nextLine();
                byte[] envio = mensaje.getBytes(StandardCharsets.UTF_8);
                DatagramPacket mandarMesnaje = new DatagramPacket(envio, envio.length, destino, puertoEnvio);
                socket.send(mandarMesnaje);
                System.out.println("Mensaje enviado correctamente a " + destino + ":" + puertoEnvio);
            }

            // cerrar el socket
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
