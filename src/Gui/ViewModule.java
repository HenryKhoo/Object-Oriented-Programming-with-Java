
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

public class ViewModule extends JFrame implements ActionListener {
    
    private final String[][] columnHeader = {{"Module Name"},{"ID"}, {"Level"}, {"Quiz"}, {"Test"}, {"Assignment"} , {"Lecturer"}};
    private final DefaultTableModel modulemodel = new DefaultTableModel(columnHeader [0],0);
    private Panel y1,y2;
    private JButton add, delete,back;
    private DefaultTableModel z; 
    private JTable moduletable;
    private JScrollPane studentscroll;
    Student current = null;
    ArrayList<Module> viewmodule = new ArrayList<Module>();
    
    public ViewModule(){
        
        
        getModuleArray();
        
        setSize(800, 350);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        
       
        y1 = new Panel();
        
        y1.setBounds(700,700,100,90);
        y2 = new Panel();
        y2.setLayout(new BorderLayout());
        
        int size = viewmodule.size();
        String[][] data = new String[size][7];
        for(int i=0; i<size; i++){
            Module a = viewmodule.get(i);
            data[i][0] = a.getModname();
            data[i][1] = ""+a. getId();
            data[i][2] = a.getLevel();
            data[i][3] = ""+a.getQuiz();
            data[i][4] = ""+a.getTest();
            data[i][5] = ""+a.getAssignment();
            data[i][6] = a.getMlecturer();
        }
        String[] columnNames = {"Module Name","ID", "Level", "Quiz", "Test", "Assignment" , "Lecturer"};
        z = new DefaultTableModel(data, columnNames);
        
        moduletable = new JTable(z);

        moduletable.setPreferredScrollableViewportSize(moduletable.getPreferredSize());
        moduletable.setFillsViewportHeight(true);
        
        JScrollPane sp = new JScrollPane(moduletable);
        y2.add(sp);
        sp.setBounds(70,50, 400,500);
        add(y2);
        setVisible(false);
        
        add(y1);
        add = new JButton("Add");
        add.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        delete = new JButton("Delete");
        delete.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        back = new JButton("Back/Refresh table");
        back.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        y1.add(add);
        y1.add(delete);
        //y1s.add(delete1);
        y1.add(back);
        
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
                viewmodule.add(modulelistone);

            }
        }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add){
            CreateModule createmod = new CreateModule();
            createmod.setVisible(true);
            setVisible(false);
            
        }else if(ae.getSource()==delete){
            deleteModule();
            marksOverwrite();
            setVisible(false);
            AdminPortal ad = new AdminPortal();
        }else if(ae.getSource()==back){
            setVisible(false);
            AdminPortal ad = new AdminPortal();
        }
            
    }
    public void deleteModule(){
        try{
            Integer oo = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Module id to delete"));

            boolean flag = false;


            for(int i = 0; i < Run.module.size(); i++){
                Module a = Run.module.get(i);
                if(oo==(Run.module.get(i).getId())){        
                    Run.module.remove(i);


                    try{

                        PrintWriter p =new PrintWriter("module.text");

                        for(int j=0; j<Run.module.size();j++){

                            Module x = Run.module.get(j);
                            p.println(x.getModname());
                            p.println(x.getId());
                            p.println(x.getLevel());
                            p.println(x.getQuiz());
                            p.println(x.getTest());
                            p.println(x.getAssignment());
                            p.println(x.getMlecturer());
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
                JOptionPane.showMessageDialog(this, "Module record has been deleted");
                

            }else{
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        }catch(Exception e){
            System.out.println("Operation cancelled");
        }

        

    
    }
    
    public void marksOverwrite(){
        viewmodule.removeAll(viewmodule);
        for(int i = 0; i < Run.module.size(); i++){
            viewmodule.add(Run.module.get(i));
        }
    }

    
}
