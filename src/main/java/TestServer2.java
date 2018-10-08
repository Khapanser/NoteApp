package main.java;



import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestServer2 {
    public static void main(String[] args) throws IOException {
/*
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }
*/
        // byte[] b;
        byte[] send = null;
        int portNumber = 5000;//Integer.parseInt(args[0]);

        while (true) {
            Path path = Paths.get("C:\\Users\\AKhaperskiy\\OrganizerServerFiles\\test.xml");
            byte[] data = Files.readAllBytes(path);
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                oos.writeObject(data);

                try {
                    while (ois.readObject() != null) {
                        send = (byte[]) ois.readObject();
                    }
                    System.out.println("Прочитанный 10ыйй байт = "+send[10]);

                    //TODO перевести файлик в XML

                    // TODO очистить send.
                    //ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    /*
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));*/
                clientSocket.close();

            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }



       /*     {

                String inputLine, outputLine;

                // Initiate conversation with client
                TestProtocol2 kkp = new TestProtocol2();
                outputLine = kkp.processInput(null);
                out.println(outputLine);

                while ((inputLine = in.readLine()) != null) {
                    outputLine = kkp.processInput(inputLine);
                    out.println(outputLine);
                    if (outputLine.equals("Bye."))
                        //break;
                        clientSocket.close();
                }
            }*/

       /*catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }*/

        }
    }
}
