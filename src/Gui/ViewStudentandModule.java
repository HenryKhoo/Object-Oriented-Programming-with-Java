
package Gui;

import  Main.*;
import Users.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.*;
import javax.swing.table.*;

public class ViewStudentandModule extends JFrame implements ActionListener {

    //private String name,gender,age,phoneno, email, level;
    private final String[][] columnHeader = {{"Name"},{"ID"}, {"Gender"}, {"Age"}, {"Phone Number"}, {"Email"} , {"Level"}};
    private final DefaultTableModel studentmodel = new DefaultTableModel(columnHeader [0],0);
    
    private final JButton next, back;
    private Panel  y1, y2, y3;
    private DefaultTableModel studenttable,moduletable; 
    private JTable z1, z2;
    Student current = null;
    ArrayList<Student> stu = new ArrayList<Student>();
    ArrayList<Module> viewmodarr = new ArrayList<Module>();
    int oo = 0;    
    
    public ViewStudentandModule() {
        
        
        getStudentArray();
        getModuleArray();

        setSize(800, 550);
        setLocation(300, 100);
        
        setLayout(new GridLayout(2,1));
        y1 = new Panel();
        y1.setBounds(700,1400,100,90);
        
        y2 = new Panel();
        y2.setLayout(new BorderLayout());
        y3 = new Panel();
        y3.setLayout(new BorderLayout());
        
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
        studenttable = new DefaultTableModel(data, columnNames);
        
        z1 = new JTable(studenttable);

        JScrollPane sp = new JScrollPane(z1);
        y2.add(sp);
        sp.setBounds(70,50, 400,700);
        add(y2);
        setVisible(false);
        
        int sizemnod = viewmodarr.size();
        String[][] moddata = new String[sizemnod][7];
        for(int j=0; j<size; j++){
            Module a = viewmodarr.get(j);
            moddata[j][0] = a.getModname();
            moddata[j][1] = ""+a. getId();
            moddata[j][2] = a.getLevel();
            moddata[j][3] = ""+a.getQuiz();
            moddata[j][4] = ""+a.getTest();
            moddata[j][5] = ""+a.getAssignment();
            moddata[j][6] = a.getMlecturer();
        }
        String[] columnNamesmod = {"Module Name","ID", "Level", "Quiz", "Test", "Assignment" , "Lecturer"};
        moduletable = new DefaultTableModel(moddata, columnNamesmod);
        
        z2 = new JTable(moduletable);
        z2.setPreferredScrollableViewportSize(new Dimension(200, 400));
        
        final JScrollPane spmod = new JScrollPane(z2);
        y3.add(spmod);
        add(y3);
        spmod.setBounds(400,200, 400,700);
        
        add(y1);
        next = new JButton("Next");
        next.addActionListener(this);
        //next.setBounds(700,300,145,60);
        y1.add(next);
        
        back = new JButton("Back");
        back.addActionListener(this);
        //back.setBounds(700,300,145,60);
        y1.add(back);
        
        
        
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
    public void getModuleArray(){
    
    
    try{
            
            Scanner input = new Scanner(new File("module.text"));

            while(input.hasNext()){

                String modname= input.nextLine();
                int id = Integer.parseInt(input.nextLine());
                String level = input.nextLine();
                int quiz= Integer.parseInt(input.nextLine());
                int test= Integer.parseInt(input.nextLine());
                int assignment = Integer.parseInt(input.nextLine());
                String mlecturer = input.nextLine();

                input.nextLine();
                Module modulelistone = new Module( modname,  id,  level,  quiz,  test,  assignment,  mlecturer);
                viewmodarr.add(modulelistone);

            }
        }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }
          @Override
    public void actionPerformed(ActionEvent ea){
        
        
        if(ea.getSource()==next){
            setVisible(false);
            AssignMarks assignmarks= new AssignMarks();
            assignmarks.setVisible(true);
        }else if(ea.getSource()==back){
            setVisible(false);
            ViewStudentMarks viewstu= new ViewStudentMarks();
            viewstu.setVisible(true);
        }
    }      
    
    
        
}
