package main.java;


import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    String[] adviceList = {"1","2","3","4","5"};

    public void goo() {
        try {
            /**
             * Серверное приложение содаёт сокет указывая конкретный порт.
             * Приложение отслеживает клиентские запросы на порту 4242.
             */
            ServerSocket serverSock = new ServerSocket(4242);
            while (true){
                /**
                 * Создание нового сокета для общения с клиентом!
                 * метод accept() блокирует приложение до тех пор, пока
                 * не поступит запрос о подключении с клиента.
                 * После чего возвращает обычный Socket
                 * (на анонимном порту) для взаимодействия с клиентом
                 */
                Socket sock = serverSock.accept();
                /**
                 *
                 */
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAdvice(){
        int random = (int) (Math.random()*adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args){
        TestServer server = new TestServer();
        server.goo();
    }
}
