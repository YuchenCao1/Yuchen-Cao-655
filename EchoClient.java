import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter the hostname>");
        Scanner sc = new Scanner(System.in);
        String hostname = sc.next();

        System.out.println("Please enter the port number of Server>");
        int portnumber = Integer.parseInt(sc.next());
        System.out.println("your hostname is: " + hostname);
        System.out.println("your portnumber is:" + portnumber);

        try (
                Socket echoSocket = new Socket(hostname, portnumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            Scanner sc3 = new Scanner(System.in);
            System.out.println("Enter a message to send to the server (type 'exit' to quit): ");
            userInput = sc3.next();
            while (!userInput.equals("exit")) {
                out.println(userInput);
                System.out.println("Echo from server: " + in.readLine());
                System.out.println("Enter another message (or type 'exit' to quit): ");
                userInput = sc3.next();
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostname);
            System.exit(1);
        }
    }
}
