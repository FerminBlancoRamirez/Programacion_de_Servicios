package intro;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Ejemplo2urlCon {
    public static void main(String[] args) {
        try {
            //Instanciamos la clase URL y añadimos un url referenciando el archivo .php previamente creado a la cual le enviaremos la informacion
            URL url = new URL("http://localhost/vernombre.php");
            //Nos conectamos con la url previamente llamada
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

            //Recogemos los datos previamente preparados y se los mandamos a la direccion de url que instanciamos con anterioridad
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena.toString());
            output.close(); // cerrar flujo

            //La url nos devuelve lo que escribimos en un formato que podamos leer de forma local
            BufferedReader reader = new BufferedReader
                            (new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close(); 
            sc.close();
        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    } 

    //En conclusion lo que se busca con este programa es crear una forma de mandarle informacion a la url y recibir una respuesta de ella de manera local 
    //como si fuera un buzon en el que metes cartas con una direccion y esa direccion te responde con otras cartas.
} 