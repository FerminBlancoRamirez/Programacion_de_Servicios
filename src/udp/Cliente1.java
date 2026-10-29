package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) {

        int puerto = 12346;
        int puertoEscucha = 12345;
        String saludo = "";
        byte[] bufferRecibo = new byte[1024];
        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {

            InetAddress destino = InetAddress.getLocalHost();
            Scanner sc = new Scanner(System.in);
            String paquete = "";

            while (!saludo.equals("salir") && !paquete.equals("salir")) do{
                System.out.println("Escribe tu mensaje");
                saludo = sc.nextLine();
                byte[] mensaje = saludo.getBytes(StandardCharsets.UTF_8);

                DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);

                socket.send(envio);
                System.out.println("Mensaje enviado correctamente a " + destino + ":" + puerto);

                DatagramPacket recibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
                socket.receive(recibo);

                int bytesRec = recibo.getLength();

                paquete = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

                System.out.println("Mensaje recibido: " + paquete);
                System.out.println("Puerto local de recepcion del mensaje: " + socket.getLocalPort());
            }

        } catch (IOException e) {
            System.err.println("Error al enviar el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
