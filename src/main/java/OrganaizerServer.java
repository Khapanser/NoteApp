package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class OrganaizerServer {
    ServerSocket serverSocket;
    int portNumber = 5000;
    Socket socket;

    public static void main(String[] args){
        OrganaizerServer server = new OrganaizerServer();

        try {
            server.go();
        } catch(Exception e){
            e.printStackTrace();
            System.err.println("SERVER_WARN: synchronized");
        }
    }

    public void go(){
        try {
            serverSocket = new ServerSocket(portNumber);
            while(true) {
                socket = serverSocket.accept();

                //Преобразуем file to byte[]
                Path path = Paths.get("C:\\Users\\AKhaperskiy\\OrganizerServerFiles\\test.xml");
                byte[] data = Files.readAllBytes(path);
                //Отправляем файл на клиент:
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(data);

                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                }
            }
        } catch(Exception e){
            System.err.println("SERVER_ERROR: method go() failed");
            e.printStackTrace();
        }
    }
}
