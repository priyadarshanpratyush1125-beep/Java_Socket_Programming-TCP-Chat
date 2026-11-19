
import java.io.*;
import java.net.*;

public class MyClient1 {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader keyboard = new BufferedReader(
                 new InputStreamReader(System.in))) {

            System.out.println("Connected to server!");

            String message;

            while (true) {
                System.out.print("You: ");
                message = keyboard.readLine();

                out.writeUTF(message);
                out.flush();

                System.out.println("Server: " + in.readUTF());

                if (message.equalsIgnoreCase("stop")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
