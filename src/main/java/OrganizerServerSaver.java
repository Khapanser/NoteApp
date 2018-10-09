package main.java;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


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
public class OrganizerServerSaver {
    ServerSocket serverSocket;
    int portNumber = 5001;
    Socket socket;
    //Socket clientSocket;
    byte[] b;
    byte bb;
    private static DefaultListModel<QCard> listModel;

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
        ArrayList<Byte> list = new ArrayList<>();
       /* try {*/
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException ioe){};

            while(true) {


                try {
                    socket = serverSocket.accept();
                    System.out.println("Клиент подключился.");
                    ois = new ObjectInputStream(socket.getInputStream());
                   // FileOutputStream fos2 = new FileOutputStream("C:\\Users\\AKhaperskiy\\OrganizerClientFiles\\3ttt.xml");
                   // while (ois.readObject() != null) {
                        System.out.println("в цикле while");
                        //if (ois.read() == -1) break;
                        //this.b = (byte[]) ois.readObject();

                        this.b = (byte[]) ois.readObject();

                        //fos2.write(bb);
                        //list.add(bb);

                    //}
                    //System.out.println("Прочитанный 10ыйй байт = "+b[10]);
                    //fos2.close();
                    //System.out.println("list.size = "+ list.size() );
                    //Byte[] bbb = list.toArray(new Byte[list.size()]);
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

                } /*catch (Exception e) {
                    e.printStackTrace();
                }*/

                catch (Exception eu) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                }
                /*catch (Exception se) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                }*/


           //    ---------------------------------------------------------------------
                //Преобразуем file to byte[]
                //Path path = Paths.get("C:\\Users\\AKhaperskiy\\OrganizerServerFiles\\test.xml");
                //byte[] data = Files.readAllBytes(path);
                //Отправляем файл на клиент:
                //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
               // oos.writeObject(data);


                //System.out.println("Дошли до отправки файла");
                //if (ois.readObject().equals("Send")) {

                //}
                ////////////// ДОБАВИЛ ЗАКРЫТИЕ СОКЕТА ПОСЛЕ ПЕРЕДАЧИ
                //socket.close();
                //Thread.sleep(1000);

                    //попробуем в том же потоке принимать данные, если оно требуется
                    //oos.close();

                    // Добавим часть про чтение с клиента

                    //clientSocket = serverSocket.accept();
                    ///Тот же сокет. Это наверное плохо

                    //Thread thread = new Thread(new ClientHandler(socket));
                    // thread.start();

               /* try {
                    socket.close();
                } */

            }
       /* }*/
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
            System.out.println ("method run is started");
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
