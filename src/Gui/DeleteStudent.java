
package Gui;

import  Main.*;
import Users.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteStudent extends JFrame implements ActionListener {
    
    //private String name,gender,age,phoneno, email, level;
    private final String[][] columnHeader = {{"Name"},{"ID"}, {"Gender"}, {"Age"}, {"Phone Number"}, {"Email"} , {"Level"}};
    private final DefaultTableModel studentmodel = new DefaultTableModel(columnHeader [0],0);
    
   // private JFrame x;
    //private int id,o;
    private Panel  y1s, y2;
    private JButton delete,delete1,back,edit,edit1;
    private DefaultTableModel z; 
    private JTable z1;
    Student current = null;
    ArrayList<Student> stu = new ArrayList<Student>();
    int oo = 0;    
    
    public DeleteStudent(){
        
        
        getStudentArray();

        
        setSize(800, 350);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
       
        y1s = new Panel();
        y1s.setBounds(700,700,100,90);
        
        y2 = new Panel();
        y2.setLayout(new BorderLayout());
        
        int size = stu.size();
        String[][] data = new String[size][7];
        for(int i=0; i<size; i++){
            Student a = stu.get(i);
            data[i][0] = a.getName();
            data[i][1] = ""+a.getId();
            data[i][2] = a.getGender();
            data[i][3] = ""+a.getAge();
            data[i][4] = a.getPhoneno();
            data[i][5] = ""+a.getEmail();
            data[i][6] = a.getLevel();
        }
        String[] columnNames = { "Student name", "ID", "Gender", "Age" , "Phone Number", "Email" , "Level" };
        z = new DefaultTableModel(data, columnNames);
        
        z1 = new JTable(z);

        z1.setPreferredScrollableViewportSize(z1.getPreferredSize());
        z1.setFillsViewportHeight(true);
        
        JScrollPane sp = new JScrollPane(z1);
        y2.add(sp);
        sp.setBounds(70,50, 400,700);
        add(y2);
        setVisible(false);
        
        
        delete = new JButton("Delete");
        delete.addActionListener(this);
        //delete.setBounds(120,70,100,30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        delete1 = new JButton("try");
        delete1.addActionListener(this);
       // delete1.setBounds(120,120,100,30);
        
        edit = new JButton("Edit");
        edit.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        edit1 = new JButton("try");
        edit1.addActionListener(this);
        
        back = new JButton("Back");
        back.addActionListener(this);
        add(y1s);
        y1s.add(delete);
        y1s.add(edit);
        y1s.add(back);
        
        
        
    } 
    
    @Override
    public void actionPerformed(ActionEvent ee){
        if(ee.getSource() == delete){
            
            deleteaccn();
            stuOverwrite();
            
        }else if(ee.getSource()==edit){
            
            Run.student_edit.setVisible(true);
        }else if(ee.getSource()==back){
            
            setVisible(false);
           AdminPortal ad = new AdminPortal();
        }
            
    }
    
    public void getStudentArray(){
    
    
    try{
            
            Scanner input = new Scanner(new File("student.text"));

            while(input.hasNext()){

                String  name= input.nextLine();
                String gender= input.nextLine();
                int id = Integer.parseInt(input.nextLine());
                String age = input.nextLine();
                String phoneno =input.nextLine();
                String email =input.nextLine();
                String level =input.nextLine();
                input.nextLine();
                Student stuone = new Student(name,gender, id, age, phoneno,email, level);
                stu.add(stuone);

            }
        }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }
        
    
    
    
    public void deleteaccn(){
        try{
            Integer oo = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter student id to delete"));

            boolean flag = false;


            for(int i = 0; i < Run.student.size(); i++){
                Student a = Run.student.get(i);
                if(oo==(Run.student.get(i).getId())){
                    current = Run.student.get(i);

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
                            //p.println(x.getModule1());
                            p.println();

                        }
                        p.close();
                    }catch(Exception e){
                    System.out.println("Operation cancelled");
                }
                    flag = true;
                    break;
                }else if( oo == 0){
                    JOptionPane.showMessageDialog(this, "Invalid Input");
                }
            }

            if(flag){
                JOptionPane.showMessageDialog(this, "Student account has been deleted");
                setVisible(false);
                AdminPortal ad = new AdminPortal();
            }else{
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        }catch(Exception e){
            System.out.println("Operation cancelled");
        }
        

    
    }
    
    public void stuOverwrite(){
        
        
        
        stu.removeAll(stu);
        
        
        for(int i = 0; i < Run.student.size(); i++){
        
            stu.add(Run.student.get(i));

        }

    }
        
}

