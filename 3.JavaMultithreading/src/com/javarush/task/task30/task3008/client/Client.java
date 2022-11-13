package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;

public class Client {

    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }



    protected Connection connection;
    private volatile boolean clientConnected = false;


///RUN
    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                this.wait();
                this.notify();
                if(clientConnected){
                    ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
                }
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
            while(clientConnected){
                String message = ConsoleHelper.readString();
                if(message.equals("exit"))return;
                if(shouldSendTextFromConsole()) sendTextMessage(message);
            }
        }
    }
///GET ADRESS
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("give me server adress");
        while(true){
            try {
                String adress = ConsoleHelper.readString();
                return adress;
            }catch(Exception e){
                ConsoleHelper.writeMessage("Wrong server adress");
            }
        }
    }
///GET PORT
    protected int getServerPort() {
        ConsoleHelper.writeMessage("give me server port");
        while (true) {
            try {
                int port = ConsoleHelper.readInt();
                return port;
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Wrong server adress");
            }
        }
    }
///GET USERNAME
    protected String getUserName(){
        ConsoleHelper.writeMessage("Give me Your User Name");
        while(true){
            try {
                String userName = ConsoleHelper.readString();
                return userName;
            }catch(Exception e){
                ConsoleHelper.writeMessage("Wrong User Name");
            }
        }
    }
///CHEK IF THAT TeXT OK
    protected boolean shouldSendTextFromConsole(){return true;}
///SOCKED THREAD
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
///MESSAGE FROM CLIENT
    protected void sendTextMessage(String text){
        try{
            connection.send(new Message(MessageType.TEXT, text));
        }catch (IOException e){
            ConsoleHelper.writeMessage("Can't connect");
            clientConnected = false;
        }
    }
    public class SocketThread extends Thread{

        @Override
        public void run() {
            Socket socket = null;
            try {
                socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " joined");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " exit from the chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){}

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while(true){
                Message message = Client.this.connection.receive();
                if(message.getType() == null) throw new IOException("Unexpected MessageType");
                switch (message.getType()){
                    case NAME_REQUEST:
                        String name = Client.this.getUserName();
                        Client.this.connection.send(new Message(MessageType.USER_NAME, name));
                        break;
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while(true){
                Message message = Client.this.connection.receive();
                if(message.getType() == null) throw new IOException("Unexpected MessageType");
                switch (message.getType()) {
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");

                }
            }
        }
    }
}
