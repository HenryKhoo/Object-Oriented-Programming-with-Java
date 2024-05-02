
package Gui;

import  Main.*;
import Users.*;
import Interface.*;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

public class DeleteLecturer extends JFrame implements ActionListener {
    private final String[][] columnHeader = {{"Name"},{"ID"},{"Gender"},{"Age"},{"Phone Number"}};
    private final DefaultTableModel lecturermodel = new DefaultTableModel(columnHeader [0],0);
   
    private Panel y1s, y2;
    private JButton delete,delete1,back;
    private DefaultTableModel z; 
    private JTable z1;

    Lecturer current = null;
    ArrayList<Lecturer> lac = new ArrayList<Lecturer>();
        
    
    public DeleteLecturer(){
        getLecturerArray();
        setSize(500, 400);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        
        y1s = new Panel();
        
        y1s.setBounds(100,300,100,30);
        
        
        y2 = new Panel();
        int size = lac.size();
        String[][] data = new String[size][5];
        for(int i=0; i<size; i++){
            Lecturer a = lac.get(i);
            data[i][0] = a.getUsername();
            data[i][1] = ""+a.getId();
            data[i][2] = a.getGender();
            data[i][3] = a.getAge();
            data[i][4] = a.getPhoneno();
        }
        String[] columnNames = {"Lecture Name","ID","Gender","Age","Phone Number"};
        z = new DefaultTableModel(data, columnNames);
        
        z1 = new JTable(z);
        JScrollPane sp = new JScrollPane(z1);
        y2.add(sp);
        add(y2);
        setVisible(false);
        
        
        delete = new JButton("Delete");
        delete.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        back = new JButton("Back");
        back.addActionListener(this);

        add(y1s);
        y1s.add(delete); y1s.add(back);

        
        
        
    } 
    
    @Override
    public void actionPerformed(ActionEvent ee){
        
        boolean found = false;
        if(ee.getSource() == delete){
            
            deleteaccn();
            lacOverwrite();

        
        }else if(ee.getSource()==back){
            
            setVisible(false);
            AdminPortal ad = new AdminPortal();
        }
            
    }
    
    public void getLecturerArray(){
    
    
    try{
            
            Scanner input = new Scanner(new File("lecturer.text"));

            while(input.hasNext()){

                String username= input.nextLine();
                String password = input.nextLine();
                String  name= input.nextLine();
                String gender= input.nextLine();
                int id = Integer.parseInt(input.nextLine());
                String age = input.nextLine();
                String phoneno =input.nextLine();
                input.nextLine();
                Lecturer adminone = new Lecturer(username, password, name,gender, id, age, phoneno);
                lac.add(adminone);

            }
        }catch (Exception e){}
    
    }
        
    
    
    
    public void deleteaccn(){
        try{

            Integer o = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter lecturer id to delete"));
            boolean flag = false;


            for(int i = 0; i < Run.lecturer.size(); i++){
                Lecturer a = Run.lecturer.get(i);
                if(o==(Run.lecturer.get(i).getId())){
                    current = Run.lecturer.get(i);
                    a = Run.lec_login;
                    Run.lecturer.remove(i);
                    try{

                        PrintWriter p =new PrintWriter("lecturer.text");

                        for(int j=0; j<Run.lecturer.size();j++){

                            Lecturer t = Run.lecturer.get(j);
                            p.println(t.getUsername());
                            p.println(t.getPassword());
                            p.println(t.getName());
                            p.println(t.getGender());
                            p.println(t.getId());
                            p.println(t.getAge());
                            p.println(t.getPhoneno());
                            //p.println(x.getModule1());
                            p.println();

                        }
                        p.close();
                    }catch(Exception t){}

                    flag = true;
                    break;
                }else if(o==null){

                JOptionPane.showInputDialog("Action cancelled");
                }
            }

            if(flag){
                JOptionPane.showMessageDialog(this, "Lecturer account has been deleted");
                setVisible(false);
                AdminPortal ad = new AdminPortal();
            }else{
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        }catch(Exception e){
            System.out.println("Operation cancelled");
        }
    }
    
    public void lacOverwrite(){  
        lac.removeAll(lac);

        for(int i = 0; i < Run.lecturer.size(); i++){    
            lac.add(Run.lecturer.get(i));
     
        }

    }
        
}

   
   
    
        
        
        

    
    

   
        
   













/*
            password = lecturer.get(i).getPassword();
            name = lecturer.get(i).getName();
            gender= lecturer.get(i).getGender();
            
            age = lecturer.get(i).getAge();
            phoneno = lecturer.get(i).getPhoneno();
            module = lecturer.get(i).getModule1();*/


/*
        y1 = new Panel();
        y1.setLayout(new BorderLayout());
        y1c = new Panel();
        y1c.setLayout(new GridLayout(2,2));
        namel = new Label("Name", Label.CENTER);
        pin = new Label("Password", Label.CENTER);
        n = new TextField(20);
        p = new TextField(20);
        yn = new Panel();
        yp = new Panel();
        //y1c.add(namel);
       // y1c.add(pin);
        yn.add(n);
        y1c.add(yn);
        yp.add(p);
        y1c.add(yp);
        y1.add(y1c, BorderLayout.CENTER);
        y1s = new Panel();
        a = new Button("Add");
        e = new Button("Edit");
        d = new Button("Delete");
        q = new Button("Quit");
        a.addActionListener(this);
        e.addActionListener(this);
        d.addActionListener(this);
        q.addActionListener(this);
        y1s.add(a);
        y1s.add(e);
        y1s.add(d);
        y1s.add(q);
        y1.add(y1s,BorderLayout.SOUTH);
        add(y1);
        add(lecturerscroll);
        y2 = new Panel();
        
        int size = Run.admin.size();
        String[][] data = new String[size][2];
        for(int i=0; i<size; i++){
            Admin a = Run.admin.get(i);
            data[i][0] = a.getName();
            data[i][1] = a.getPassword();
        }
        String[] columnNames = { "Name", "Password" };
        z = new DefaultTableModel(data, columnNames);
        z1 = new JTable(z);
        JScrollPane sp = new JScrollPane(z1);
        y2.add(sp);
        add(y2);*/