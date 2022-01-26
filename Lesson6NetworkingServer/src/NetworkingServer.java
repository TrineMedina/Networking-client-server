import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingServer {

    private static int port = 49152;

    public static void main(String[] args) throws IOException{

        String arg;
        int i = 0;
        while (i < args.length && args[i].equalsIgnoreCase("--port")) {
            arg = args[i];
            port = Integer.parseInt(arg);
            i++;
       }

        System.out.println("Server running on port # " + port);

        try (
            ServerSocket sSocket = new ServerSocket(port)){

            System.out.println("Waiting for connection...");

            try (Socket incoming = sSocket.accept()){
                System.out.println("Connection made.");
                OutputStream out = incoming.getOutputStream();

                try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true)){
                    writer.println("HTTP/1.0 200 OK\n");
                    writer.println("Content-Type: text/html\n");
                    writer.println("<html>\n<head><title>Java Networking</title></head>\n<body>\n<h1>Java Networking</h1>\n</body>\n</html>");
                }
                sSocket.close();
            }
        }
    }
}
