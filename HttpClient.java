import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class HttpClient{
    public static void main(String args[]) throws IOException
    {
        final int PORT = 8080;
        final String HOST = "localhost";

        Socket sock = new Socket(HOST, PORT);
        
        BufferedReader from_server = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        BufferedWriter to_server = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        Scanner keyboard = new Scanner(System.in);
        
        String command = "";
        while(!command.equals("end")){
            System.out.println("enter command:");
            command = keyboard.nextLine();
            to_server.write(command + "\n");
            to_server.flush();
            
            //System.out.println("server says:" + from_server.readLine());
            //System.out.println("server says:" + from_server.readLine());
            //System.out.println("server says:" + from_server.readLine());
            //System.out.println("server says:" + from_server.readLine());
        }
        
        sock.close();
    }
}