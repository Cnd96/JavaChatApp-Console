package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(2000);
        System.out.println("listening to client");
        while(true){
            Socket client =server.accept();
            Thread t1=new Thread( new chating(client));
            t1.start();
        }
    }
}
