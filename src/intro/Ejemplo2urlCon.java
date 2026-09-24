package intro;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Ejemplo2urlCon {
    public static void main(String[] args) {
        try {
            //Instanciamos la clase URL y añadimos un url referenciando el archivo .php previamente creado
            URL url = new URL("http://localhost/vernombre.php");
            URLConnection conexion = url.openConnection();
            //Habilitamos la entrega de datos a la url previamente seleccionada
            conexion.setDoOutput(true);
            //Recogemos los datos de nombre y apellidos
            Scanner sc=new Scanner(System.in);
            System.out.println("Dime tu nombre: ");
            String nombre=sc.nextLine();
            System.out.println("Dime tu apellidos: ");
            String apellidos=sc.nextLine();
            //Creamos un StringBuilder para juntar los resultados de los valores
            StringBuilder cadena = new StringBuilder();
            cadena.append("nombre=").append(nombre);
            cadena.append("&apellidos=").append(apellidos);

            // ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena.toString());
            output.close(); // cerrar flujo

            // LEER DE LA URL
            BufferedReader reader = new BufferedReader
                            (new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close(); // cerrar flujo
            sc.close();
        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    } // main
} // Ejemplo2urlCon