package main.java;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TestClient{
        BufferedReader reader;
    //byte[] b = new byte[10000];
    //String message;
    DefaultListModel<QCard> list = new DefaultListModel<>();
    public void gooo(){
        try{
            list.addElement(new QCard("title1","descr21"));
            //message = b[1];
            Socket s = new Socket("127.0.0.1",5000);
            InputStreamReader input = new InputStreamReader(s.getInputStream());
            reader = new BufferedReader(input);
            OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());

            /**
             * test is needed
             */
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(list);
            //oos.write(b);
            //writer.println(message);
            //создаём новый поток для считывания данных с сервера
            Thread thread = new Thread(new TestRunnable());
            //запускаем поток
            thread.start();
        } catch(Exception e){e.printStackTrace();}
    }

    /**
     * класс для описания метода run(),
     * стартующего во втором потоке.
     */
    class TestRunnable implements Runnable{
        public void run(){
            String str = null;
            try {
                while ((str = reader.readLine()) != null) {
                    System.out.print("read"+str);
                    // TODO вывести на экран типо JTextArea.append(str + "\n");
                }
            } catch (Exception e){e.printStackTrace();}
        }
    }


    public void goo(){
        try{
            /**
             * Создаём соединение через сокет к приложению,
             * работающему на порту 4242, на том же ПК(localhost)
             */
            Socket s = new Socket("127.0.0.1",4242);
            /**
             * Создаём InputStreamReader и связываем его
             * с низкоуровневым потоком (соединением) через сокет
             * (Фактически это мост, соединяющий низкоуровневый
             * поток байтов из сокета и высокоуровневый символьный поток,
             * предоставляемый BufferedReader-ом.)
             */
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            /**
             * Создаем BufferedReader и считываем данные
             */
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you should"+ advice);
            reader.close();
        }catch (Exception e){e.printStackTrace();}
    }


    public static void main(String[] args){
        TestClient client = new TestClient();
        //client.goo();
        //client.gooo();
    }

}
