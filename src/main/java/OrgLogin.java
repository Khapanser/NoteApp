package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

            /*try {

                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);

                String db = "jdbc:odbc:db1";
                con = DriverManager.getConnection(db);
                st = con.createStatement();
            }
            catch (Exception ex){

                System.err.println("Connection to DB refused!");
            }*/


            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/noteapp_1","root","root");
//here sonoo is database name, root is username and password
                //Statement stmt=con.createStatement();
               // ResultSet rs=stmt.executeQuery("select * from login");
               /* while(rs.next())
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                con.close();*/
            }catch(Exception e){ System.out.println(e);}

        }

        public void check(){


        }

        public void go(){
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


            button.addActionListener((ActionEvent event) -> {

                String user = editor1.getText().trim();
                String pass = editor2.getText().trim();
                String sql = "SELECT * FROM login where user = '"+user+"' AND pass='"+pass+"'";
                //String sql = "SELECT * FROM login";
                System.out.println("Query is: "+sql);
                try {
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    //rs = stmt.executeQuery(sql);

                count = 0;
                while(rs.next())
                {
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                    count = count+1;
                    System.out.println("count="+count);
                }
                    System.out.println("count="+count);
                }
                catch (SQLException e){
                    System.err.println("WARN: SQL execution failed!");
                }

                if(count>0) {
                    JOptionPane.showMessageDialog(null,"Your organizer opening!");
                    Organizer org = new Organizer(user);
                    org.go();
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"User not found or password incorrect! Try again.");
                }


            });
        }



}
