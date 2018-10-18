package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.*;

public class OrgLogin {

        Connection con;
        Statement st;
        ResultSet rs;

        int count = 0;

        public static void main(String[] args){
            OrgLogin login = new OrgLogin();
            //login.go();
        }

        public OrgLogin(){
            connect();
            go();
        }


        public void connect(){

            try {

                String driver = "sun.jdbc.odbc.Jdbc0dbcDriver";
                Class.forName(driver);

                String db = "jdbc:odbc:db1";
                con = DriverManager.getConnection(db);
                st = con.createStatement();
            }
            catch (Exception ex){

                System.err.println("Connection to DB refused!");
            }
        }



        private void go(){
            Font font = new Font("TimesRoman", Font.BOLD,18);
            Font font2  = new Font("TimesRoman",Font.PLAIN,18);


            JPanel mainPanel = new JPanel();
            JFrame frame = new JFrame(" Login ");
            mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));




            frame.setSize(500,150);

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            //JPanel panel3 = new JPanel();
            mainPanel.add(BorderLayout.EAST, panel1);
            mainPanel.add(BorderLayout.WEST,panel2);

            //mainPanel.add(BorderLayout.SOUTH,panel3);

            JButton button = new JButton("Log in");
            JLabel label1 = new JLabel("e-mail:");
            JLabel label2 = new JLabel("password:");

            JTextField editor1 = new JTextField(10);
            editor1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JTextField editor2 = new JTextField(10);
            editor2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            panel1.add(label1);
            panel1.add(editor1);
            panel1.add(label2);
            panel1.add(editor2);

            panel2.add(button);


            frame.add(mainPanel);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            button.addActionListener(event -> {

                String user = editor1.getText().trim();
                String pass = editor2.getText().trim();
                String sql = "SELECT user, pass FROM tableName where user = '"+user+"' AND pass='"+pass+"';";
                try {
                    rs = st.executeQuery(sql);

                count = 0;
                while(rs.next())
                {
                    count = count++;
                }

                }
                catch (Exception e){
                    System.err.println("SQL execution failed!");
                }

                if(count==1) {
                    JOptionPane.showMessageDialog(null,"User FOUND!");
                    Organizer org = new Organizer(user);
                    org.go();
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"User NOT FOUND!");
                }


            });
        }



}
