package guestserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GuestThread implements Runnable {
    private Socket socket;

    public GuestThread(Socket socket) {
        this.socket = socket;
    }

    // Método run()
    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Cliente conectado: " + socket.getInetAddress() +
                    " atendido por " + Thread.currentThread().getName());

            int secretNumber = 1 + new Random().nextInt(100);
            boolean stop = false;

            while (!stop) {
                output.println("Introduzca su número:");
                String clientInput = input.readLine();
                int guess = Integer.parseInt(clientInput);
                if (guess < secretNumber) {
                    output.println("Mayor");
                } else if (guess > secretNumber) {
                    output.println("Menor");
                } else {
                    output.println("Acierto");
                    stop = true;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error en la conexión: " + ex.getMessage());
        }
    }
}
