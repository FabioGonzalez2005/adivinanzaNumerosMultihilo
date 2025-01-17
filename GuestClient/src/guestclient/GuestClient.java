package guestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuestClient {
    public static void main(String[] args) {
        String host = "172.16.3.56";
        int port = 12346;

        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            boolean stop = false;

            while (!stop) {
                // Leer la instrucción del servidor antes de introducir el número
                System.out.println(input.readLine());
                // Solicitar número al usuario
                String userInput = stdInput.readLine();

                try {
                    int myNumber = Integer.parseInt(userInput);
                    // Enviar número al servidor
                    output.println(myNumber);
                    // Leer la respuesta del servidor
                    String answer = input.readLine();
                    System.out.println(answer);

                    if ("Acierto".equals(answer)) {
                        stop = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, introduzca un número válido.");
                }
            }
        } catch (IOException ex) {
            System.err.println("Error de conexión: " + ex.getMessage());
        }
    }
}
