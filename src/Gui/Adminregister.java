
package Gui;

import Main.*;
import Users.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Adminregister extends JFrame implements ActionListener{
    
    private final JTextField name, age, phoneno, username, password;
    private final JLabel namel, agel, phonenol, usernamel, passwordl,title;
    private final JButton submit, cancel;
    private final ButtonGroup gender;
    private final JRadioButton female, male;
    private Admin adminaccn;
    private int flag,e;
    public Adminregister(){
        
        setSize(750,400);
        setLocation(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title = new JLabel("ADMIN REGISTRATION");
        title.setBounds(295,5,250,30);
        
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
        usernamel.setBounds(200,160,150,20);
        username.setBounds(350,160,150,20);
        
        passwordl = new JLabel("Set password: ");
        password = new JTextField(15);
        passwordl.setBounds(200,190,150,20);
        password.setBounds(350,190,150,20);
        
        submit = new JButton("Submit $ Register");
        submit.addActionListener(this);
        submit.setBounds(200,250,145,60);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(400,250,100,60);
        
        add(title);
        add(namel); add(name); 
        gender.add(female); gender.add(male); add(female); add(male);
        add(phonenol); add(phoneno);add(agel); add(age);
        add(usernamel); add(username); add(passwordl);add(password);
        add(submit); add(cancel);
        
        
        setVisible(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ee){
        
        if(ee.getSource()== submit){
            
           
            String usern = username.getText();
            if(duplicateusername(usern)==0){
                
                JOptionPane.showMessageDialog(this, "Username is taken");
                flag =1;
                username.setText(null);
            }else{
                
                register();
                resetText();
                Run.pageadmin2.setVisible(false);
                Run.pagefront.setVisible(true);
            }
            
            }else if(ee.getSource()==cancel){
            
            Run.pageadmin2.setVisible(false);
            Run.pagefront.setVisible(true);
            resetText();
        }
    }
    public int duplicateusername(String usern){
        for(int i = 0; i < Run.admin.size(); i++){
            if(username.equals(Run.admin.get(i).getUsername())){
                flag = 0;
                break;
            }else{
                flag=1;
            }
        }
        return flag;
    }
    
    public void id_gen() {
        if(Run.admin.isEmpty()){
            e = 5001;
        } else{
            int size = Run.admin.size();
            Admin last = Run.admin.get(size - 1);
            e = last.getId()+ 1;
        }
    }
    public void resetText(){
        
        username.setText(null);
        password.setText(null);
        name.setText(null);
        age.setText(null);
        phoneno.setText(null);
        
        female.setSelected(false);
        male.setSelected(false);
        gender.clearSelection();
    }
    
    public void register(){
        
        
        id_gen();
        String a= username.getText();
        String b = password.getText();
        String c = name.getText();

        String f= age.getText();
        String g = phoneno.getText(); 

        if(female.isSelected()){
            String d = female.getText();
            adminaccn = new Admin(a,b,c,d,e,f,g) ;

        }else if(male.isSelected()){

            String d = male.getText();
            adminaccn = new Admin(a,b,c,d,e,f,g) ;

        }

        Run.admin.add(adminaccn);
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

        JOptionPane.showMessageDialog(this, "Account Registered!");
    }
    
}


            
           
            