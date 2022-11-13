package com.javarush.task.task30.task3008;


import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server was running");


        try{
            while(true){
                Socket socket = serverSocket.accept();
                if(socket!=null){

                    new Handler(socket).start();
                }

            }
        }catch(Exception e){
            System.out.println("Ooops");
        }finally {
            serverSocket.close();
        }

        /*
            Client client = new Client();
            client.run();
    */
    }


    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for(Connection connection:connectionMap.values()){
            try {
                connection.send(message);
            } catch (IOException e) {
                System.out.println("Cannot Send message");
            }
        }
    }
    private static class Handler extends Thread{


        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage(String.valueOf(socket.getRemoteSocketAddress()));
            try {
                Connection connection = new Connection(socket);
                String name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                notifyUsers(connection, name);
                serverMainLoop(connection, name);
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,name));
                ConsoleHelper.writeMessage("Connection with:\n" + socket.getRemoteSocketAddress() + "\nwas closed");

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage(String.valueOf("Something went wrong with:\n" + socket.getRemoteSocketAddress()));
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            String name;

            Message message = new Message(MessageType.NAME_REQUEST);
            while(true){
                connection.send(message);
                Message answer = connection.receive();
                if(answer.getType() == MessageType.USER_NAME){
                    name = answer.getData();
                    if(name!= null && name!="" && !connectionMap.containsKey(name)){
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for(String name: connectionMap.keySet()){
                if(!name.equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                }else{
                    ConsoleHelper.writeMessage("It isn't TEXT");
                }
            }
        }

    }
}
