package tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
    public static void main(String[] args) {
        int puerto = 6000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + servidor.getLocalPort());

            Socket cliente1 = servidor.accept();
            System.out.println("Cliente conectado: " + cliente1.getInetAddress());

            DataInputStream flujoEntrada = new DataInputStream(cliente1.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente1.getOutputStream());

            while (true) {
                int numero = flujoEntrada.readInt();
                System.out.println("Recibido del cliente: " + numero);

                if (numero == 0) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                boolean resultado = ServicioServidor.esNumeroPerfecto(numero);

                flujoSalida.writeBoolean(resultado);
                flujoSalida.flush();
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente1.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}