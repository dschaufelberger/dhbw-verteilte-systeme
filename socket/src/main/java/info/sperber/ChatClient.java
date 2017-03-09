package info.sperber;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ChatClient implements Runnable {
    private Socket socket;
    private String username;
    private Scanner in;
    private PrintWriter out;

    public ChatClient(Socket client) {
        this.socket = client;
        try {
            this.in = new Scanner(this.socket.getInputStream());
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (in != null && out != null) {
            boolean sessionActive = true;
            out.println("Enter your username:");
            this.username = in.nextLine();

            while(sessionActive) {
                String input = in.nextLine();
                if ("close".equals(input)) {
                    sessionActive = false;
                    out.println("Goodbye!");
                    App.clients.remove(this);
                    break;
                }

                String[] storageCommand = input.split(":");
                if (storageCommand.length == 2) {
                    try {
                        App.UpdateStorage(storageCommand[0].trim(), Integer.parseInt(storageCommand[1]));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                out.println(String.format("You entered: %s", input));

                for (ChatClient client : App.clients) {
                    if (!client.equals(this)) {
                        client.send(this.username, input);
                    }
                }
            }
        }

        if (socket != null)
            try {
                socket.close();
            } catch (IOException e) {
        }
    }

    public void send(String user, String text) {
        if (out != null) {
            out.println(String.format("%s: %s", user,text));
        }
    }
}
