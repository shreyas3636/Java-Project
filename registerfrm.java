import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class registerfrm implements ActionListener{
    JFrame f;
    Panel p;
    JButton s,c,t;
    TextField t1,t2,t3,t4,t5;
    Label l1,l2,l3,l4,l5,l6;
    final String JDBC_URL = "jdbc:mysql://localhost:3306/java";
    final String JDBC_USER = "root";
    final String JDBC_PASSWORD = "";
    registerfrm(){
        f=new JFrame();
        p=new Panel();
        s=new JButton();
        t1=new TextField();
        t2=new TextField();
        t3=new TextField();
        t4=new TextField();
        t5=new TextField();
        l1=new Label("Name");
        l2=new Label("Email Address");
        l3=new Label("Course Assigned");
        l4=new Label("Phone");
        l5=new Label("Image URL");
        l6=new Label("ADD FACULTY HERE !!!");
        ImageIcon w = new ImageIcon("src/close.png");
        c=new JButton(w);
        c.setSize(20, 20);
        c.setLocation(525, 0);
        c.addActionListener(this);
        f.setLayout(new FlowLayout());
        p.setLayout(null);
        p.setSize(550, 550);
        p.setBackground(Color.WHITE);
        l6.setLocation(200,10);
        l6.setSize(200,30);
        l1.setLocation(50,50);
        l1.setSize(100,30);
        t1.setLocation(50,85);
        t1.setSize(450,30);
        l2.setLocation(50,125);
        l2.setSize(100,30);
        t2.setLocation(50,160);
        t2.setSize(450,30);
        l3.setLocation(50,200);
        l3.setSize(120,30);
        t3.setLocation(50,235);
        t3.setSize(450,30);
        l4.setLocation(50,280);
        l4.setSize(100,30);
        t4.setLocation(50,315);
        t4.setSize(450,30);
        l5.setLocation(50,360);
        l5.setSize(100,30);
        t5.setLocation(50,400);
        t5.setSize(450,30);
        s=new JButton("Save");
        t=new JButton("Close");
        s.setSize(100,50);
        s.setLocation(440,500);
        s.setForeground(Color.red);
        s.addActionListener(this);
        t.setSize(100,50);
        t.setLocation(320,500);
        t.setForeground(Color.blue);
        t.addActionListener(this);
        p.add(c);
        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(l3);
        p.add(t3);
        p.add(l4);
        p.add(t4);
        p.add(l5);
        p.add(t5);
        p.add(l6);
        p.add(s);
        p.add(t);
        f.add(p);

        f.setSize(600,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - f.getWidth()) / 2;
        int centerY = (screenSize.height - f.getHeight()) /4;
        f.setLocation(centerX, centerY);
        f.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==c||e.getSource()==t){
f.dispose();
        }
        if(e.getSource()==s){
            try{
                String name=t1.getText();
                String email=t2.getText();
                String job=t3.getText();
                String phone=t4.getText();
                String img=t5.getText();
                if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||
                        t4.getText().equals("")||t5.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"PLS Fill The Details First");
                }
                else {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                    PreparedStatement st = connection.prepareStatement("insert into employee (name,email,job,phone,img)values(?,?,?,?,?)");
                    st.setString(1, name);
                    st.setString(2, email);
                    st.setString(3, job);
                    st.setString(4, phone);
                    st.setString(5, img);
                    st.executeUpdate();
                    f.dispose();
                    Main obj=new Main();
                }
            }catch (Exception x)

            {
                System.out.println("not connected");
            }
        }
    }
}
