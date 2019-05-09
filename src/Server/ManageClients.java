package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManageClients {
    private static ManageClients ourInstance = new ManageClients();

    Map<String,PrintWriter> clientNameOutPutStreamMapping=new HashMap<>();

    public static ManageClients getInstance() {return ourInstance;}

    public boolean clientNameTaken(String clientName){
        if(clientNameOutPutStreamMapping.containsKey(clientName)) {
            return false;
        }
        return true;
    }

    public void addClient(Socket client,String name) throws IOException{
        String clientName=name;

        PrintWriter writer=new PrintWriter(client.getOutputStream());
        clientNameOutPutStreamMapping.put(clientName,writer);
    }

    public void Message(String message, String name, String senderName){
        String clientName=name;
        if(clientNameOutPutStreamMapping.containsKey(clientName)) {
            PrintWriter recieverOutPutStream= clientNameOutPutStreamMapping.get(clientName);
            PrintWriter senderOutputStream= clientNameOutPutStreamMapping.get(senderName);
            privateMessage(message,recieverOutPutStream,senderOutputStream);
        } else {
            broadcastMessages(message);
        }
    }

    public  void privateMessage(String message,PrintWriter recieverOutPutStream,PrintWriter senderOutputStream){
        PrintWriter writer = recieverOutPutStream;
        writer.println(message);
        writer.flush();

        writer = senderOutputStream;
        writer.println(message);
        writer.flush();
    }

    public void broadcastMessages(String message){
        clientNameOutPutStreamMapping.keySet().forEach(client -> {
            PrintWriter writer = clientNameOutPutStreamMapping.get(client);
            writer.println(message);
            writer.flush();
        });
    }

    public void sendEnterNameAgainMessage(Socket client) throws IOException {
        PrintWriter writer=new PrintWriter(client.getOutputStream());
        writer.println("Name taken enter another name");
        writer.flush();
    }
}





//
//
//
//
//
//public class ManageClients {
//    private static ManageClients ourInstance = new ManageClients();
//
//    ArrayList<Clients> clients=new ArrayList<Clients>();
//
//    public static ManageClients getInstance() {
//        return ourInstance;
//    }
//
//    public void addClient(Socket client,String name) throws IOException {
//        String clientName=name;
//        PrintWriter writer=new PrintWriter(client.getOutputStream());
//
//        Clients clientObject=new Clients(clientName,writer);
//
//        clients.add(clientObject);
//    }
//
//    public void Message(String message, String name, String senderName){
//        Iterator it=clients.iterator();
//
//        while (it.hasNext()){
//            Clients client=(Clients) it.next();
//            if (client.name.equals(name)){
////                Clients sender=clients.get()
//                privateMessage(message,client);
//                return;
//            }
//        }
//        broadcastMessages(message);
//    }
//
//    public  void privateMessage(String message,Clients reciever){
//        PrintWriter writer = reciever.writer;
//
//        writer.println(message);
//        writer.flush();
//    }
//
//    public void broadcastMessages(String message){
//        Iterator it=clients.iterator();
//
//        while (it.hasNext()) {
//            try {
//                Clients client=(Clients) it.next();
//                PrintWriter writer = client.writer;
//
//                writer.println(message);
//                writer.flush();
//            }
//            catch (Exception ex)
//            { }
//        }
//    }
//}
//public class Clients {
//    String name;
//    PrintWriter writer;
//
//    Clients(String name,PrintWriter writer){
//        this.name=name;
//        this.writer=writer;
//    }
//}
