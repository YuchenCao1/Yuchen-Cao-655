import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        String user_enter;
        System.out.println("Please enter the port number of Server>");
        Scanner sc = new Scanner(System.in);
        user_enter = sc.next();
        int portnumber = Integer.parseInt(user_enter);
        System.out.println("your portnumber is: "+portnumber);

        ServerSocket serverSocket = new ServerSocket(portnumber);
        while (true) {
            try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port " + portnumber);
                System.out.println(e.getMessage());
            }
        }
    }
}
