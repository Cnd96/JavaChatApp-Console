package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class chating implements Runnable{
    private Socket client;
    String clientName;
    ManageClients clientObject;

    chating(Socket client){ this.client=client;
        clientObject=ManageClients.getInstance();
    }
    @Override
    public void run(){
        try {
            InputStreamReader in=new InputStreamReader(client.getInputStream());

            BufferedReader br2 =new BufferedReader(in);


            boolean x=false;
            while(x==false){
                clientName=br2.readLine();
                x=clientObject.clientNameTaken(clientName);
                if(x==false){
                    clientObject.sendEnterNameAgainMessage(client);
                }
            }
            clientObject.addClient(client,clientName);

            String welcome=clientName+" connected";
            System.out.println(welcome);

            clientObject.broadcastMessages(welcome);

            while(true){
                String str=br2.readLine();
                String message=clientName+" :"+str;
                System.out.println(message);

                str=str+",";
                String name=str.substring(0,str.indexOf(","));
                clientObject.Message(message,name,clientName);
            }
        }catch (Exception e){
            System.out.println(clientName+" disconnected");
        }
    }
}
