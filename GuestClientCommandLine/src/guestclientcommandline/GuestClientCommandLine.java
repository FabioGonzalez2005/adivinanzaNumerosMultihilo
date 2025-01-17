package guestclientcommandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuestClientCommandLine {
    public static void main(String[] args) {
        // Comprobación de argumentos.
        if (args.length < 2) {
            System.err.println("Error, debe indicar el servidor y el puerto.");
            System.exit(1);
        }
        // Dirección y puerto del servidor.
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        
        try {
            // Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle mensajes hasta adivinar el número.
            boolean stop = false;
            int attempts = 0;
            int myNumber;
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
            
            while (!stop) {
                attempts += 1;
                // Leer número desde el teclado-
                System.out.println("Introduzca su número: ");
                myNumber = Integer.parseInt(stdInput.readLine());
                System.out.println("Intento " + attempts + ", número " + myNumber);
                // Enviar número al servidor.
                output.println(myNumber);
                // Leer la respuesta del servidor.
                String answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer);
                
                if(answer.equals("Acierto")) {
                    stop = true;
                }
            }
        } catch (IOException ex) {
        }
    }
    
}
