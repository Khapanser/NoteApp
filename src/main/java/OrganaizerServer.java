package main.java;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * В рамках первой версии, сервер должен:
 * 1. Преобразовать XML в byte[]
 * 2. Отправить byte[] на клиент
 */

/** TODO-list
 * TODO 1. Добавить второй поток
 * TODO 2. Добавить новый метод run() (для Runnable)
 * TODO 3. В методе run описать считывание byte[] с клиента
 * TODO 4. Преобразовать byte[] в XML-файл.
 */
public class OrganaizerServer {
    ServerSocket serverSocket;
    int portNumber = 5000;
    Socket socket;
    //Socket clientSocket;
    byte[] b;

    private static DefaultListModel<QCard> listModel;

    public static void main(String[] args){
        OrganaizerServer server = new OrganaizerServer();

        try {
            server.go();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("SERVER: файл уже редактируется другим пользователем");
        }
    }

        //Делаем synchronize в метод
    public synchronized void go(){
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
                ////////////// ДОБАВИЛ ЗАКРЫТИЕ СОКЕТА ПОСЛЕ ПЕРЕДАЧИ
                //socket.close();
                Thread.sleep(1000);

                //попробуем в том же потоке принимать данные, если оно требуется
                //oos.close();

                // Добавим часть про чтение с клиента

                //clientSocket = serverSocket.accept();
                ///Тот же сокет. Это наверное плохо

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();

            }
        } catch(Exception e){
            System.out.println("SERVER: Ошибка в методе go()");
            e.printStackTrace();
        }
    }

    /**
     * TODO-LIST
     * TODO -- Cоздать новый поток и вложить в него объект класса impl Runnable
     * TODO -- Создать класс ClientHundler implements Runnable
     * TODO -- добавить в класс ClientHundler метод run()
     * TODO -- в классе добавить инициализацию считывания byte[]
     * TODO -- в методе run реализовать считывание
     * TODO -- в классе добавить метод сохранения файла (из byte[] в XML) --> FileOutputStream
     */

    class ClientHandler implements Runnable{
        //BufferedReader reader;
        Socket sock;
        ObjectInputStream ois;
        byte[] bb;
        FileOutputStream fos;
        public ClientHandler(Socket clientSocket) {

            //InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
            try {
                sock = clientSocket;
                ois = new ObjectInputStream(sock.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                //reader = new BufferedReader(isReader);
            public void run () {
            try {
                while (ois.readObject() != null) {
                    this.bb = (byte[]) ois.readObject();
                }
                //ois.close();
            }catch (Exception e){e.printStackTrace();}

            System.out.println("Прочитанный 10ыйй байт = "+bb[10]);
            //Добавим запись в файл XML:
                /*
                try {
                    this.fos = new FileOutputStream("C:\\Users\\Александра\\OrganizerServerFiles\\ClientToServer.xml");
                    fos.write(b);
                    fos.close();
                } catch (Exception y){y.printStackTrace();}
*/

            }


    }


}
