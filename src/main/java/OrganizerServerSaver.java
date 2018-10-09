package main.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class OrganizerServerSaver {
    ServerSocket serverSocket;
    int portNumber = 5001;
    Socket socket;
    byte[] b;

    public static void main(String[] args){
        OrganizerServerSaver server = new OrganizerServerSaver();
        try {
            server.go();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("SERVER: файл уже редактируется другим пользователем");
        }
    }
    public void go(){
        ObjectInputStream  ois;
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException ioe){};

            while(true) {

                try {
                    socket = serverSocket.accept();
                    System.out.println("Клиент подключился.");
                    ois = new ObjectInputStream(socket.getInputStream());
                        System.out.println("в цикле while");
                        this.b = (byte[]) ois.readObject();

                    System.out.println("Выводим записанные байты:");
                    for(byte bo: b){
                        System.out.print(bo);
                    }
                    System.out.println("");

                    try (FileOutputStream fos2 = new FileOutputStream("C:\\Users\\AKhaperskiy\\OrganizerServerFiles\\test.xml")) {
                        System.out.println("Записываем эти байты в файл...");
                        fos2.write(b);
                        fos2.close();
                    }
                    System.out.println("Закрываем сокет...");
                    socket.close();

                }
                catch (Exception eu) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                }
            }
    }
}
