
package Gui;

import Main.Run;
import Users.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class AdminEdit  extends JFrame implements ActionListener{
    
    
    private final JTextField name, age, phoneno, username, password;
    private final JLabel namel, agel, phonenol, usernamel, passwordl,title;
    private final JButton submit, cancel;
    private final ButtonGroup gender;
    private final JRadioButton female, male;
    
    private Admin admin_dat;
    
    
    public AdminEdit(){
        
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
        
        title = new JLabel("ADMIN ACCOUNT EDIT");
        title.setBounds(250,4,400,30);
        
        namel = new JLabel("Name: ");
        name = new JTextField(15);
        namel.setBounds(200,35,60,20);
        name.setBounds(350,35,150,20);
        
        gender = new ButtonGroup();
        female = new JRadioButton("Female");
        male = new JRadioButton("Male");
        
        female.setBounds(450,70,100,20);
        male.setBounds(350,70,100,20);
        

        phonenol = new JLabel("Phone Number: ");
        phoneno = new JTextField(15);
        phonenol.setBounds(200,100,100,20);
        phoneno.setBounds(350,100,150,20);
        
        agel = new JLabel("Age: ");
        age = new JTextField(7);
        agel.setBounds(200,130,60,20);
        age.setBounds(350,130,50,20);
        
        usernamel = new JLabel("Set username: ");
        
        username = new JTextField(15);
        
        usernamel.setBounds(200,190,150,20);
        username.setBounds(350,190,150,20);
        
        passwordl = new JLabel("Set password: ");
        password = new JTextField(15);
        passwordl.setBounds(200,220,150,20);
        password.setBounds(350,220,150,20);
    
    
        submit = new JButton("Update");
        submit.addActionListener(this);
        submit.setBounds(200,300,145,60);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(400,300,100,60);
        
        add(title);
        add(namel); add(name); 
        gender.add(female); gender.add(male); add(female); add(male);
        add(phonenol); add(phoneno);add(agel); add(age);
        add(usernamel); add(username); add(passwordl);add(password);
        add(submit); add(cancel);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
    
        if(e.getSource()==submit){
            
            update_admin();
            
        }else if(e.getSource()==cancel){
            
            setVisible(false);
            AdminPortal ad = new AdminPortal();
        }
    
    
    }
    
    
    public void update_admin(){
        
        
        String a= username.getText();
        String b = password.getText();
        String c = name.getText();
        int e = Run.login.getId();
        String f= age.getText();
        String g = phoneno.getText(); 
        String h = null;
        String d =null;
        if(female.isSelected()){
            d = female.getText();
            admin_dat= new Admin(a,b,c,d,e,f,g) ;

        }else if(male.isSelected()){

            d = male.getText();
            admin_dat= new Admin(a,b,c,d,e,f,g) ;

        }
        boolean flag=false;
        
        ///remove data from array
        for(int i = 0; i < Run.admin.size(); i++){
            Admin y = Run.admin.get(i);
            if(Run.login.equals(Run.admin.get(i))){
                
                y = Run.login;
                Run.admin.remove(i);
                
                try{
                    
                    PrintWriter p =new PrintWriter("admin.text");

                    for(int j=0; j<Run.admin.size();j++){

                        Admin t = Run.admin.get(j);
                        p.println(t.getUsername());
                        p.println(t.getPassword());
                        p.println(t.getName());
                        p.println(t.getGender());
                        p.println(t.getId());
                        p.println(t.getAge());
                        p.println(t.getPhoneno());
                        p.println();

                    }
                    p.close();
                }catch(Exception t){}
                flag = true;
                break;
            }
        }
        
        if(flag =true){
            //add back the data to file
            Run.admin.add(admin_dat);
            try{
                
                PrintWriter p =new PrintWriter("admin.text");
                for(int i=0; i<Run.admin.size();i++){

                    Admin x = Run.admin.get(i);
                    p.println(x.getUsername());
                    p.println(x.getPassword());
                    p.println(x.getName());
                    p.println(x.getGender());
                    p.println(x.getId());
                    p.println(x.getAge());
                    p.println(x.getPhoneno());
                    p.println();

                }
                p.close();
            }catch(Exception t){}

            JOptionPane.showMessageDialog(this, "Account Updated!");
            setVisible(false);
            AdminPortal ad = new AdminPortal();
            
        }     
            
    }
    
    
}
