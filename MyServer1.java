
import java.io.*;
import java.net.*;

public class MyServer1 {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {

            System.out.println("Waiting for client...");

            try (Socket socket = server.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 BufferedReader keyboard = new BufferedReader(
                     new InputStreamReader(System.in))) {

                System.out.println("Client connected!");

                String message;

                while (true) {
                    message = in.readUTF();
                    System.out.println("Client: " + message);

                    if (message.equalsIgnoreCase("stop")) {
                        out.writeUTF("Chat ended.");
                        out.flush();
                        break;
                    }

                    System.out.print("Reply: ");
                    String reply = keyboard.readLine();

                    out.writeUTF(reply);
                    out.flush();
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
