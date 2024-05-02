
package Gui;

import Main.*;
import Users.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.PrintWriter;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AdminRegisStudent extends JFrame implements ActionListener{
    

    private final JTextField name, age, phoneno, email, level; 
    private final JLabel namel, agel, phonenol,email1, title, level1;
    private final JButton submit, cancel;
    private final ButtonGroup gender;
    private final JRadioButton female, male;
    private Student stu_data; 
    private int c; 
    private boolean flag= false;
    
    public AdminRegisStudent() {
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
        
        title = new JLabel("STUDENT ACCOUNT REGISTRATION");
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
        
        email1 = new JLabel("Email: ");
        email = new JTextField(15);
        email1.setBounds(200,160,100,20);
        email.setBounds(350,160,150,20);
        
        //checkbox
        
        level1 = new JLabel("Level: ");
        level = new JTextField(7);
        level1.setBounds(200,190,100,20);
        level.setBounds(350,190,150,20);
        
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
        add(email);add(email1);
        add(level); add(level1);
        
        add(submit); add(cancel);
        
        
        
        
    }
    

    
    @Override
    public void actionPerformed(ActionEvent ea){
        
        
        if(ea.getSource()==submit){
            register();
            resetText();
            AdminPortal adminportal = new AdminPortal();
            setVisible(false);
            

        }else if(ea.getSource()==cancel){
            AdminPortal adminportal = new AdminPortal();
            setVisible(false);
            resetText();
        }
          
        
    }
    
      
    
    //@Override
    public void resetText(){
        
        name.setText(null);
        age.setText(null);
        phoneno.setText(null);

        female.setSelected(false);
        male.setSelected(false);
        email.setText(null);
        level.setText(null);

    }
    
    
    
   // @Override
    public void register(){
        id_gen();
        String a = name.getText();
        String d = age.getText();
        String e = phoneno.getText(); 
        String f = email.getText();
        String g = level.getText();
        if(a.isEmpty() || d.isEmpty() || e.isEmpty()|| f.isEmpty()|| g.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter all the fields!");
        }
        if(female.isSelected()){
            String b = female.getText();
            stu_data= new Student(a,b,c,d,e,f,g) ;

        }else if(male.isSelected()){
            String b = male.getText();
            stu_data= new Student(a,b,c,d,e,f,g) ;

        }
        
        Run.student.add(stu_data);
        try{
            PrintWriter p =new PrintWriter("student.text");
            for(int i=0; i<Run.student.size();i++){

                Student x = Run.student.get(i);
                //p.println(x.getUsername());
                //p.println(x.getPassword());
                p.println(x.getName());
                p.println(x.getGender());
                p.println(x.getId());
                p.println(x.getAge());
                p.println(x.getPhoneno());
                p.println(x.getEmail());
                p.println(x.getLevel());
                p.println();

            }
            p.close();
        }catch(Exception t){}

        JOptionPane.showMessageDialog(this, "Account Registered!");
            
    }

   // @Override
    public void id_gen() {
        if(Run.student.isEmpty()){
            c = 1001;
        } else{
            int size = Run.student.size();
            Student last = Run.student.get(size - 1);
            c = last.getId()+ 1;
        }//To change body of generated methods, choose Tools | Templates.
    }


    
    
}

