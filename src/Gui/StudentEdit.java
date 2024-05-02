
package Gui;
import Main.*;
import Users.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentEdit extends JFrame implements ActionListener{
    
    private final JTextField name, age, phoneno, email, ide, level;
    private final JLabel namel, agel, phonenol,title, email1, ide1, level1;
    private final JButton submit, cancel;
    private final ButtonGroup gender;
    private final JRadioButton female, male;
    
    private Student stu_dat;// int test; String test2;
    private int c;
  
    
    
    public StudentEdit(){
    
       
        
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
        
        title = new JLabel("STUDENT ACCOUNT EDIT");
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
        
        
        ide1 = new JLabel("Student ID: ");
        ide1.setBounds(200,160,60,20);

        ide = new JTextField(15);
        ide.setBounds(350,160,50,20);
        
        
        email1 = new JLabel("Set Email: ");
        email = new JTextField(15);     
        email1.setBounds(200,190,150,20);
        email.setBounds(350,190,150,20);
        
        level1 = new JLabel("Set Level: ");
        level = new JTextField(15);     
        level1.setBounds(200,210,150,20);
        level.setBounds(350,210,150,20);
    
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
        add(email1); add(email); add(level); add(level1);
        add(submit); add(cancel); add(ide);add(ide1);
    }
    
    public void actionPerformed(ActionEvent e){
    
        if(e.getSource()==submit){
           update_stu();
           resetText();
           setVisible(false); 
           DeleteStudent deletestu = new DeleteStudent();
           deletestu.setVisible(false);
           
           AdminPortal ad = new AdminPortal();
        }else if(e.getSource()==cancel){
            setVisible(false);

        }
    
    
    }
    
      
    public void resetText(){
        name.setText(null);
        phoneno.setText(null);
        age.setText(null);
        ide.setText(null);
        email.setText(null);
        level.setText(null);
        

       
    }
    public void update_stu(){
        
        String a = name.getText();
        String b =null;
        int c = Integer.parseInt(ide.getText());
        String d= age.getText();
        String e = phoneno.getText(); 
        String f = email.getText();
        String g = level.getText();
        if(female.isSelected()){
            b = female.getText();
            stu_dat= new Student(a,b,c,d,e,f,g) ;

        }else if(male.isSelected()){

            b = male.getText();
            stu_dat= new Student(a,b,c,d,e,f,g ) ;

        }
        
        boolean flag = false;
        
        ///remove data from array
        for(int i = 0; i < Run.student.size(); i++){
            Student y = Run.student.get(i);
            if(c ==(Run.student.get(i).getId())){
                
                Run.student.remove(i);
                
                try{
                    
                    PrintWriter p =new PrintWriter("student.text");

                    for(int j=0; j<Run.student.size();j++){

                        Student t = Run.student.get(j);
                        p.println(t.getName());
                        p.println(t.getGender());
                        p.println(t.getId());
                        p.println(t.getAge());
                        p.println(t.getPhoneno());
                        p.println(t.getEmail());
                        p.println(t.getLevel());
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
            Run.student.add(stu_dat);
            try{
                
                PrintWriter p =new PrintWriter("student.text");
                for(int k=0; k<Run.student.size();k++){

                    Student x = Run.student.get(k);
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
            }catch(Exception x){}

            JOptionPane.showMessageDialog(this, "Account Updated!");
            setVisible(false);
            
            }
            
        }
    
    
}
