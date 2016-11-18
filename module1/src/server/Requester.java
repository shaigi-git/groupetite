package server;
/**
 * Created by shai on 10/13/16.
 */
import logic.PersonalInfo;
import logic.User;

import java.io.*;
import java.net.*;
public class Requester{
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    public String serverIP = "192.168.0.12";
    //public String serverIP = "localhost";
    Requester(){}
    void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket(serverIP, 2004);
            System.out.println("Connected to localhost in port 2004");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("server>" + message);
                    //sendMessage("Hi my server");
                    System.out.print("client message> ");
                    message = br.readLine();/*"bye"*/;
                    System.out.println("Client sends: " + message);
                    //sendMessage(message);
                    sendMessageObj(new User(new PersonalInfo("Shai", "Yo")));
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    void sendMessageObj(Object msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Requester client = new Requester();
        client.run();
    }
}