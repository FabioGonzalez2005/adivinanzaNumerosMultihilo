package guestserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GuestServer {
    public static void main(String[] args) {
        // Puerto del servidor.
        int port = 12346;
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor de adivinanza iniciado en el puerto " + port + ".");
            
            while (true) {
                // Aceptar una conexión.
                Socket connection = server.accept();
                Thread guessThread = new Thread(new GuestThread(connection));
                guessThread.start();
            }
        } catch (IOException ex) {
    }
    }
}
