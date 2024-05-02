
package Gui;

import Main.Run;
import Users.*;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Adminpage extends JFrame implements ActionListener{
    
    private final JTextField username;
    private final JLabel label1, label2;
    private final JPasswordField password;
    private Button register, login, menu;
    private JLabel title;
    public Adminpage(){
        setSize(300,300);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        label1 = new JLabel("Username: ");
        username = new JTextField(15);
        username.addActionListener(this);
        add(label1);
        add(username);

        label2 = new JLabel("Password: ");
        password = new JPasswordField(15);
        password.addActionListener(this);
        add(label2); add(password);
        
        login = new Button("Login");
        login.addActionListener(this);
        add(login);
        
        register = new Button("Register Admin");
        register.addActionListener(this);
        add(register);
        
        menu = new Button("Exit to main menu");
        menu.addActionListener(this);
        add(menu);
        
        setVisible(false);
        setLayout(new FlowLayout());
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == register){
   
            setVisible(false);
            Run.pageadmin2.setVisible(true);
            
        }else if(e.getSource()== menu){
            JFrame ah = new Menu();
            setVisible(false);
            ah.setVisible(true);
            ah.dispose();
            
        }else if(e.getSource()==login){
            
            String a = username.getText();
            String b = password.getText();
            for(int i = 0; i <Run.admin.size();i++){

                Admin c = Run.admin.get(i);
                if(a.equals(c.getUsername())){
                    Run.login = c;
                   
                    
                    resetText();
                    break;    

                }
                
            }
            if(Run.login!=null){
                
                if(b.equals(Run.login.getPassword())){
                    
                    createlog();
                    setVisible(false);
                    AdminPortal ah = new AdminPortal();
                    
                    //login successful
                }else if(b.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please fill in password!");
                    resetText();
                    
                }else{
                    Run.login = null;
                    JOptionPane.showMessageDialog(this, "Invalid Password!");
                    resetText();
                }
            }else if(a.isEmpty()){
                
                JOptionPane.showMessageDialog(this, "Please Key in username or password!");
                resetText();
                
            }else{
                
                JOptionPane.showMessageDialog(this, "Invalid Username!");
                resetText();
            }
            
            
        }
    }
    
    
    public void resetText(){
        
        username.setText(null);
        password.setText(null);
      
    }
    
    public void createlog(){
        
        String name = Run.login.getName();
        int id = Run.login.getId(); 
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        try{
            
            FileWriter writer = new FileWriter("admin_log.txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("Name: "+name);
            out.println("ID: "+id);
            out.println("Admin log in at: ");
            out.println(formatter.format(date));
            out.println();
            out.close();
            
            
        }catch (Exception e){}
    
    
    
    }
    
    public void createlogout(){
        
        String name = Run.login.getName();
        int id = Run.login.getId(); 
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        try{
            
            FileWriter writer = new FileWriter("admin_log.txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("Name: "+name);
            out.println("ID: "+id);
            out.println("Admin logout at: ");
            out.println(formatter.format(date));
            out.println();
            out.close();
            
            
        }catch (Exception e){}
        
    }
     
}
