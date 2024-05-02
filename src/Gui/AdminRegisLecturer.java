
package Gui;

import Main.*;
import Users.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AdminRegisLecturer extends JFrame implements ActionListener{
    

    private final JTextField name, age, phoneno, username, password;
    private final JLabel namel, agel, phonenol, usernamel, passwordl,title;
    private final JButton submit, cancel;
    private final ButtonGroup gender;
    private final JRadioButton female, male;
    private Lecturer lec_data; private int e; private boolean flag= false;
    
    public AdminRegisLecturer() {
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
        
        title = new JLabel("LECTURE ACCOUNT REGISTRATION");
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
        usernamel.setBounds(200,160,150,20);
        username.setBounds(350,160,150,20);
        
        passwordl = new JLabel("Set password: ");
        password = new JTextField(15);
        passwordl.setBounds(200,190,150,20);
        password.setBounds(350,190,150,20);
        
        //checkbox
        
        submit = new JButton("Submit & Register");
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
    public void actionPerformed(ActionEvent ea){
        
        
        if(ea.getSource()==submit){
            
            
            String usern = username.getText();
            if(duplicateusername(usern)== true){
                
                JOptionPane.showMessageDialog(this, "Username is taken");
                flag = false;
                username.setText(null);
            }else{
                
                register();
                resetText();
            }

        }else if(ea.getSource()==cancel){
            
            Run.pageadminlec.setVisible(false);
            AdminPortal ad = new AdminPortal();
            resetText();
        }
          
        
    }
    
      
    
    //@Override
    public void resetText(){
        
        username.setText(null);
        password.setText(null);
        name.setText(null);
        age.setText(null);
        phoneno.setText(null);
        
        female.setSelected(false);
        male.setSelected(false);

    }
    
    
    
   // @Override
    public void register(){
        id_gen();
        
        String a= username.getText();
        String b = password.getText();
        String c = name.getText();

        String f= age.getText();
        String g = phoneno.getText(); 


        if(female.isSelected()){
            String d = female.getText();
            lec_data= new Lecturer(a,b,c,d,e,f,g) ;

        }else if(male.isSelected()){

            String d = male.getText();
            lec_data= new Lecturer(a,b,c,d,e,f,g) ;

        }
        
        Run.lecturer.add(lec_data);
        try{
            PrintWriter p =new PrintWriter("lecturer.text");
            for(int i=0; i<Run.lecturer.size();i++){

                Lecturer x = Run.lecturer.get(i);
                p.println(x.getUsername());
                p.println(x.getPassword());
                p.println(x.getName());
                p.println(x.getGender());
                p.println(x.getId());
                p.println(x.getAge());
                p.println(x.getPhoneno());
                //p.println(x.getModule1());
                p.println();

            }
            p.close();
        }catch(Exception t){}

        JOptionPane.showMessageDialog(this, "Account Registered!");
            
    }

   // @Override
    public void id_gen() {
        if(Run.lecturer.isEmpty()){
            e = 7001;
        } else{
            int size = Run.lecturer.size();
            Lecturer last = Run.lecturer.get(size - 1);
            e = last.getId()+ 1;
        }//To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public boolean duplicateusername(String usern) {
       for(int i = 0; i < Run.lecturer.size(); i++){
            if(username.equals(Run.lecturer.get(i).getUsername())){
                flag = true;
                break;
            }else{
                flag = false;
                break;
            }
        }
        return flag; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

/*
*/