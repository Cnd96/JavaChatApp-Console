package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 2000);

        Thread t1=new Thread( new MessageIn(server));
        t1.start();
        System.out.println("Enter name:");
        InputStreamReader userin=new InputStreamReader(System.in);
        BufferedReader br =new BufferedReader(userin);
        String name=br.readLine();

        PrintWriter out=new PrintWriter(server.getOutputStream());
        out.println(name);
        out.flush();

        while(true){
            InputStreamReader in=new InputStreamReader(System.in);
            BufferedReader br2 =new BufferedReader(in);
            String message=br.readLine();

            out.println(message);
            out.flush();
        }
    }
}


