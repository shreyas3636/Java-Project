import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Main implements ActionListener{
    JFrame f;
    Panel[] p;
    Panel z;
    Button[] b1;

    JButton[] b2,b3,b4;
    JMenuBar mb;
    JMenu m;
    JMenuItem b;
    TextField t1;
    Label[] l1,l2,l3,l4,l5;
    int i,x,u=0;
    String s="";
    String C="";
    Connection connection;
    Statement st;
    final String JDBC_URL = "jdbc:mysql://localhost:3306/java";
    final String JDBC_USER = "root";
    final String JDBC_PASSWORD = "";
    Main()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            st = connection.createStatement();
        }
        catch(Exception ee)
        {
            System.out.println("driver not register");
        }
        i=0;
        f=new JFrame();
        mb=new JMenuBar();
        t1=new TextField();
        z=new Panel();
        z.setLayout(null);
        m=new JMenu("Employees Only...");
        b=new JMenuItem("Add New Employee");
        f.setJMenuBar(mb);
        mb.add(m);
        m.add(b);
        p=new Panel[8];
        b1=new Button[8];
        b2=new JButton[8];
        b3=new JButton[8];
        b4=new JButton[8];
        l1=new Label[8];
        l2=new Label[8];
        l3=new Label[8];
        l4=new Label[8];
        l5=new Label[8];
        f.setLayout(new FlowLayout(FlowLayout.LEFT,90,60));
        b.addActionListener(this);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        t1.setText("Search Employee..");
        t1.setLocation(1150,0);
        t1.setSize(230,24);

        z.add(t1);
        mb.add(z);
        f.setSize(800,600);
        f.setVisible(true);
        try {
            ResultSet rs=st.executeQuery("select * from employee");
            while(rs.next())
            {
                p[i] = new Panel();
                ImageIcon k = new ImageIcon(rs.getString(6));
                b4[i] = new JButton(k);
                b4[i].setSize(80, 80);
                b4[i].setLocation(50, 50);
                l1[i]=new Label(rs.getString(2));
                l1[i].setSize(350, 50);
                l1[i].setLocation(150, 50);
                l2[i]=new Label(rs.getString(4));
                l2[i].setSize(350, 50);
                l2[i].setLocation(150, 80);
                l3[i]=new Label(rs.getString(3));
                l3[i].setSize(350, 50);
                l3[i].setLocation(50, 150);
                l4[i]=new Label("Phone:-"+rs.getString(5));
                l4[i].setSize(350, 50);
                l4[i].setLocation(50, 180);
                p[i].setLayout(null);
                p[i].setBackground(Color.white);
                p[i].setName(String.valueOf(rs.getString(1)));
                ImageIcon w = new ImageIcon("src/close2.png");
                b2[i] = new JButton(w);
                b2[i].setSize(30, 30);
                b2[i].setLocation(220, 270);
                b2[i].addActionListener(this);
                b2[i].setName(String.valueOf(rs.getString(1)));
                p[i].add(b2[i]);
                ImageIcon q = new ImageIcon("src/edit2.png");
                b3[i] = new JButton(q);
                b3[i].setSize(30, 30);
                b3[i].setLocation(190, 270);
                b3[i].setName(String.valueOf(rs.getString(1)));
                b3[i].addActionListener(e -> {
                    JButton clicked= (JButton) e.getSource();
                     x=Integer.parseInt(clicked.getName());
                    editemp();
                });
                p[i].add(b3[i]);
                p[i].setSize(250, 300);
                p[i].add(b4[i]);
                p[i].add(l1[i]);
                p[i].add(l2[i]);
                p[i].add(l3[i]);
                p[i].add(l4[i]);
                f.add(p[i]);
                i++;
                f.invalidate();
                f.validate();
                f.repaint();
            }
        } catch (Exception v) {
            System.out.println("not connected");
        }
        b.addActionListener(_ -> new registerfrm());

        t1.addKeyListener(new KeyListener() {
            @Override

            public void keyTyped(KeyEvent e) {
                ResultSet rs;
                try{
                    s=s+e.getKeyChar();
                    rs=st.executeQuery("SELECT * FROM employee WHERE name LIKE '"+s+"'");
                    while (rs.next()){
                        C=rs.getString(1);
                        for(int j=0;j<i;j++){
                            p[j].setVisible(false);
                            if(p[j].getName().equals(String.valueOf(C))){
                                u=j;
                            }
                        }
                        p[u].setVisible(true);
                    }
                    if(t1.getText().isEmpty()){
                        for(int j=0;j<i;j++){
                            p[j].setVisible(true);
                            s="";
                        }
                    }

                }catch (Exception v)
                {
                    System.out.println("not connected key listener");
                }
                f.invalidate();
                f.validate();
                f.repaint();
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int x=Integer.parseInt(clickedButton.getName());
        try{

            int xx=JOptionPane.showConfirmDialog(null,"Do You Want to Delete User                       ");
            if(xx==1||xx==2) {
                f.invalidate();
                f.validate();
                f.repaint();
            }
            else {
                PreparedStatement st = connection.prepareStatement("delete from employee where id=?");
                st.setInt(1, x);
                st.executeUpdate();
                new Main();
            }
        }catch (Exception v)
        {
            System.out.println("not connected");
        }
    }
    public void editemp(){
        new editemp(x);
    }
    public static void main(String[] arg)
    {
        new Main();
    }
}
