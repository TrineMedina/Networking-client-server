import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NetworkingClient {

    private static int port = 49152;
    private static String server = "localhost";
    private static String arg = "";
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Please enter one of the following options to specify the server address or port number.");
        System.out.println("--server\n--port");
        System.out.println("To use the default port and server address, please enter X to use the defaults.");
        arg = in.nextLine();

        processUserSelection();


        try (Socket socket = new Socket(server, port)){
            Scanner sc = new Scanner(socket.getInputStream(), "UTF-8");

            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            socket.close();
            sc.close();
        }
        in.close();
    }

    private static void processUserSelection() {

        if(arg.equalsIgnoreCase("--server")){
            System.out.println("Please enter the server address");
            server = in.nextLine();
            System.out.println("Please enter --port to add the port number");
            arg = in.nextLine();
            processUserSelection();
        }
        else if (arg.equalsIgnoreCase("--port")){
            System.out.println("Please enter the port you wish to use");
            port = Integer.parseInt(in.nextLine());
        } else if (arg.equalsIgnoreCase("x")) {
            System.out.println("Using default server address and port.\n");
        }else {
            System.out.println("Invalid entry. Please enter --server, --port or X to use defaults");
            arg = in.nextLine();
            processUserSelection();
        }
    }
}
