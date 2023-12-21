import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

class  App {
    public static void main(String[] args) throws Exception {
        login();
       //adminmenu.admin_menu();
        //input_admin();
        //adminmenu.admin_menu();
    }

    public static void login() {

        JFrame f = new JFrame("Login");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(2000, 900);

        ImageIcon backgroundImage = new ImageIcon("src/img/llgn.png"); // Replace with your image file

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 2000, 900);

        JLabel l1, l2;
        l1 = new JLabel("Username");
        l1.setBounds(150, 370, 200, 30);

        l2 = new JLabel("Password");
        l2.setBounds(150, 460, 200, 30);

        JTextField F_user = new JTextField();
        F_user.setBounds(137, 398, 315,  55);

        JPasswordField F_pass = new JPasswordField();
        F_pass.setBounds(137, 490, 315, 55);

        JButton login_but = new JButton("Login");
        login_but.setBounds(180, 597, 200, 50);

        login_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = F_user.getText();
                String password = new String(F_pass.getPassword());

                if (username.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username");
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter password");
                } else {
                    Connection connection = Koneksi.connect();
                    if (connection != null) {
                        try {
                            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
                            String st = ("SELECT * FROM admin WHERE nama='" + username + "' AND idadmin='"
                                    + password + "'");
                            ResultSet rs = stmt.executeQuery(st);
                            if (!rs.next()) {
                                System.out.print("No user");
                                JOptionPane.showMessageDialog(null, "Wrong Username/Password!");
                            } else {
                                f.dispose();
                                adminmenu.admin_menu();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        f.add(F_pass);
        f.add(login_but);
        f.add(F_user);
        f.add(l1);
        f.add(l2);
        f.add(backgroundLabel);


        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }};