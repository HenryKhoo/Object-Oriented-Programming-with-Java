
package Gui;

import  Main.*;
import static Main.Run.module;
import static Main.Run.studentmarks;
import Users.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewStudentMarks extends JFrame implements ActionListener {
    private final String[][] columnHeader = {{"StudentID"},{"StudentName"}, {"ModuleID"}, {"ModuleName"}, {"quiz"}, {"test"} , {"assignment"}, {"total"}};
    private final DefaultTableModel modulemodel = new DefaultTableModel(columnHeader [0],0);
    

    private Panel y1,y2;
    private JTextField search;
    private JButton add,back;
    private JLabel search1;
    private DefaultTableModel z; 
    private JTable markstable;
    private JScrollPane studentscroll;
    Student current = null;
    ArrayList<StudentMarks> viewstumarks = new ArrayList<StudentMarks>();

    
    public ViewStudentMarks(){
        
        
        getMarksArray();
        
        setSize(800, 350);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
         
        y1 = new Panel();
        y1.setBounds(700,700,100,90);
        
        y2 = new Panel();
        y2.setLayout(new BorderLayout());
        int size = viewstumarks.size();
        String[][] data = new String[size][8];
        for(int i=0; i<size; i++){
            StudentMarks a = viewstumarks.get(i);
            data[i][0] = ""+a.getStudentID();
            data[i][1] = a.getStudentName();
            data[i][2] = ""+a. getModuleID();
            data[i][3] = a.getModuleName();
            data[i][4] = ""+a.getQuiz();
            data[i][5] = ""+a.getTest();
            data[i][6] = ""+a.getAssignment();
            data[i][7] = ""+a.getTotal();
        }
        String[] columnNames = {"StudentID","StudentName", "ModuleID", "ModuleName", "quiz", "test" , "assignment", "total"};
        z = new DefaultTableModel(data, columnNames);
        
        markstable = new JTable(z);
        
        markstable.setPreferredScrollableViewportSize(markstable.getPreferredSize());
        markstable.setFillsViewportHeight(true);
        
        JScrollPane sp = new JScrollPane(markstable);
        y2.add(sp);
        sp.setBounds(70,50, 400,500);
        add(y2);
        setVisible(false);

        add(y1);
        

        
        add = new JButton("Add");
        add.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        back = new JButton("Back/Refresh");
        back.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        y1.add(add);
        y1.add(back);

        
        
    }
   
    public void getMarksArray(){
        
    try{

            Scanner input = new Scanner(new File("studentmarks.text"));
            while(input.hasNext()){

                int StudentID = Integer.parseInt(input.nextLine()); 
                String StudentName= input.nextLine(); 
                int ModuleID= Integer.parseInt(input.nextLine());
                String ModuleName= input.nextLine(); 
                int quiz= Integer.parseInt(input.nextLine());
                int test= Integer.parseInt(input.nextLine());
                int assignment= Integer.parseInt(input.nextLine());
                int total= Integer.parseInt(input.nextLine());
                
                input.nextLine();
                StudentMarks marksone = new StudentMarks( StudentID, StudentName, ModuleID, ModuleName, quiz, test, assignment, total);
                viewstumarks.add(marksone);

            }
            }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add){
            setVisible(false);  
            AssignMarks assignmarks = new AssignMarks();
            assignmarks.setVisible(true);
            
        
        }else if(ae.getSource()==back){
            setVisible(false);
            LecturePortal lecportal = new LecturePortal();
            lecportal.setVisible(true);
            
        }
            
    }
    



    
}
