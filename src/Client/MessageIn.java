package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageIn implements Runnable {
    private Socket server;
    MessageIn(Socket server){
        this.server=server;
    }
    @Override
    public void run() {
        try {
            InputStreamReader in=new InputStreamReader(server.getInputStream());
            BufferedReader br2 =new BufferedReader(in);

            while(true){
                String str=br2.readLine();
                System.out.println(str);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
