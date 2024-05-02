
package Gui;
import Main.*;
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

public class LectureLogin extends JFrame implements ActionListener{
    
    private final JTextField username;
    private final JLabel label1, label2;
    private final JPasswordField password;
    private Button login, menu;
    private JLabel title;
    public LectureLogin(){
        setSize(200,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        label1 = new JLabel("Lecturer username: ");
        username = new JTextField(15);
        username.addActionListener(this);
        add(label1);
        add(username);

        label2 = new JLabel("Lecturer password: ");
        password = new JPasswordField(15);
        password.addActionListener(this);
        add(label2); add(password);
        
        login = new Button("Login");
        login.addActionListener(this);
        //login.setBounds(450,300,70,50);
        add(login);
    
        menu = new Button("Exit to main menu");
        menu.addActionListener(this);
        //menu.setBounds(450,500,70,50);
        add(menu);
        
        setVisible(false);
        setLayout(new FlowLayout());
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()== menu){
            JFrame ah = new Menu();
            
            setVisible(false);
            ah.setVisible(true);
            ah.dispose();
            
        }else if(e.getSource()==login){
            
            String a = username.getText();
            String b = password.getText();
            for(int i = 0; i <Run.lecturer.size();i++){

                Lecturer c = Run.lecturer.get(i);
                if(a.equals(c.getUsername())){
                    Run.lec_login= c;
                   
                    
                    resetText();
                    break;    

                }
                
            }
            if(Run.lec_login!=null){
                
                if(b.equals(Run.lec_login.getPassword())){
                    createlog();
                    setVisible(false);

                    LecturePortal lecportal = new LecturePortal();
                    lecportal.setVisible(true);
                }else if(b.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please fill in password!");
                    resetText();
                    
                }else{
                    Run.lec_login = null;
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
        
        String name = Run.lec_login.getName();
        int id = Run.lec_login.getId(); 
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        try{
            
            FileWriter writer = new FileWriter("lecturer_log.txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("Name: "+name);
            out.println("ID: "+id);
            out.println("Lecturer log in at: ");
            out.println(formatter.format(date));
            out.println();
            out.close();
            
            
        }catch (Exception e){}
        
    }
    
    public void createlogout(){
        
        String name = Run.lec_login.getName();
        int id = Run.lec_login.getId(); 
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        try{
            
            FileWriter writer = new FileWriter("lecturer_log.txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("Name: "+name);
            out.println("ID: "+id);
            out.println("Lecturer log out at: ");
            out.println(formatter.format(date));
            out.println();
            out.close();
            
            
        }catch (Exception e){}
        
    }
}
