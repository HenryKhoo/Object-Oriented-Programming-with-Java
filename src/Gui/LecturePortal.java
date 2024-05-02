
package Gui;

import Main.*;
import Users.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LecturePortal extends JFrame implements ActionListener{
    
    private JButton edit,logout, assign, report,time;
    private JLabel title;
    public LecturePortal() {

        setSize(400,320);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        
        title = new JLabel("LECTURER PORTAL");
        title.setBounds(130,20,200,25);
        
        edit = new JButton("Modify Account");
        edit.addActionListener(this);
        edit.setBounds(90,70,200,25);
        
        assign = new JButton("Assign Marks");
        assign.addActionListener(this);
        assign.setBounds(90,110,200,25);
        
        report = new JButton("Create Report");
        report.addActionListener(this);
        report.setBounds(90,150,200,25);
        
        time = new JButton("Create Consultation Slot Session");
        time.addActionListener(this);
        time.setBounds(70,190,250,25);
        
        logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setBounds(140,240,100,25);
        
        add(title); add(edit); add(logout); add(assign); add(report); add(time); 
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==edit){
            setVisible(false);
            Run.lecturer_edit.setVisible(true);
            
        }if(e.getSource()==assign ){
            setVisible(false);
            ViewStudentMarks viewstumarks= new ViewStudentMarks();
            viewstumarks.setVisible(true);

            
            
        }if(e.getSource()==report ){
            

            Run.lecreport.setVisible(true);
            setVisible(false);
            
        }if(e.getSource()==logout){
            
            LectureLogin leclogin = new LectureLogin();
            setVisible(false);
            leclogin.setVisible(true);
           
                    
        }if(e.getSource()==time){
        
            ConsultationTime ti = new ConsultationTime();
            
            setVisible(false);
            
        }
    }
}