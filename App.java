// imports for UI
import javax.swing.*;
import java.awt.event.*;

// imports for Database
import java.sql.*;

public class Main extends JFrame implements ActionListener {
    static final String url="jdbc:mysql://localhost/yash";
    static final String user="Yash";
    static final String pass="pw00";
    static String query="insert into employee values(1,'yash','limbad','upleta','2020-09-20')";

    static int id;
    static String first_name;
    static String last_name;
    static String city;
    static Date date;

    static void GetConnected() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);

        PreparedStatement preparedStmt = con.prepareStatement("insert into employee values(?,?,?,?,?)");
        preparedStmt.setInt(1, id);
        preparedStmt.setString(2, first_name);
        preparedStmt.setString(3, last_name);
        preparedStmt.setString(4, city);
        preparedStmt.setDate(5, date);

        preparedStmt.executeUpdate();

        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");

        System.out.println("----------------------------");
        System.out.println("Id \tFirst Name \tLast Name \tCity \tDate");
        while (rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getDate(5));
        }

        con.close();
    }

    JTextField t1,t2,t3,t4,t5;
    JButton b;
    JLabel res;
    Main(){
        setTitle(" -- Employee Data -- ");

        JLabel l0=new JLabel("Enter Employee Data");
        add(l0);
        l0.setBounds(150,20,150,30);

        JLabel l1=new JLabel("Employee No. :");
        add(l1);
        l1.setBounds(100,100,150,25);

        t1=new JTextField();
        add(t1);
        t1.setBounds(250,100,120,25);

        JLabel l2=new JLabel("First Name:");
        add(l2);
        l2.setBounds(100,150,150,25);

        t2=new JTextField();
        add(t2);
        t2.setBounds(250,150,120,25);

        JLabel l3=new JLabel("Last Name:");
        add(l3);
        l3.setBounds(100,200,150,25);

        t3=new JTextField();
        add(t3);
        t3.setBounds(250,200,120,25);

        JLabel l4=new JLabel("City");
        add(l4);
        l4.setBounds(100,250,150,25);

        t4=new JTextField();
        add(t4);
        t4.setBounds(250,250,120,25);

        JLabel l5=new JLabel("Joining Date:");
        add(l5);
        l5.setBounds(100,300,150,25);

        t5=new JTextField();
        add(t5);
        t5.setBounds(250,300,120,25);
        t5.setToolTipText("YYYY-MM-DD");

        b=new JButton("SUBMIT");
        add(b);
        b.setBounds(150,350,120,25);
        b.addActionListener(this);

        res=new JLabel();
        add(res);
        res.setBounds(150,400,400,200);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLayout(null);
        setVisible(true);
        setSize(600,600);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b){
            id=Integer.parseInt(t1.getText());
            first_name=t2.getText();
            last_name=t3.getText();
            city=t4.getText();
            date=Date.valueOf(t5.getText());

            try {
                GetConnected();
            }
            catch (SQLException e){
                System.out.println(e);
            }

            res.setText("Record Saved");
        }
    }

    public static void main(String[] args) throws SQLException {
        new Main();
    }
}
