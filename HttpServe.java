import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

class ServerThread implements Runnable{
    final private Socket client;

    public ServerThread(Socket client) 
    {
        this.client = client;
    }
     
    
    private void handleConnection(Socket client) throws IOException
    {
                InputStreamReader isr = new InputStreamReader(client.getInputStream());
            
                BufferedReader bf = new BufferedReader(isr);
                String line = "_";
                
                while(!"end".equals(line)){
                    line = bf.readLine();  
                   
                    System.out.println("client says:"+line);
                    
                    //while(!(line = bf.readLine()).isEmpty()){
                    //    System.out.println(line);
                    //}

                    String response = "server recieved:" +  line + " " + new Date();
                    
                    //String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + new Date();
                    //client.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                    //client.getOutputStream().write(response.getBytes("UTF-8"));
                    //client.getOutputStream().flush();
                }
                System.out.println("connection closed.");
                client.close();
    }
    public void run()
    {
        try {
            this.handleConnection(this.client);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
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

        System.out.printf("Listening on port %d...\n", PORT);
        
        while (true) { 
                
                Socket client = server.accept();
                ServerThread sv = new ServerThread(client);
                Thread thread = new Thread(sv);

                thread.start();

        }
        
    }
}