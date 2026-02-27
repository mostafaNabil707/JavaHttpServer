import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServe{
    public static void main(String args[]) throws IOException
    {
        final int PORT = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("couldn't create socket, terminating");
            System.exit(1);
        }

        System.out.printf("Listening on port %d...", PORT);
        
        while (true) { 
            @SuppressWarnings("unused")
            
                Socket client = server.accept();
                InputStreamReader isr = new InputStreamReader(client.getInputStream());
            
                BufferedReader bf = new BufferedReader(isr);
                //String line = "";
                //while(!(line = bf.readLine()).isEmpty()){
                //    System.out.println(line);
                //}
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + new Date();
                client.getOutputStream().write(httpResponse.getBytes("UTF-8"));

                client.close();
                server.close();
                break;
            
        }
        
    }
}