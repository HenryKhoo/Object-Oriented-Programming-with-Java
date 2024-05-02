
package Gui;

import Main.*;
import Users.*;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ConsultationTime extends JFrame implements ActionListener{
    
    public JLabel title,datet,name,mod,dura,ve;
    public JTextField title1, name1,mod1,ven;
    public JButton submit,cancel;
    public JFrame fram = new JFrame();
    public JPanel panel = new JPanel();
    public JPanel panel2 = new JPanel(new BorderLayout());
    public JPanel panel3 = new JPanel(new BorderLayout());
    String h,a,b;
    public JComboBox combo = new JComboBox();
    public JComboBox timecombo = new JComboBox();
    public DateTimePicker datetime = new DateTimePicker();
    String nam,time,da,selec,duration;
    ArrayList<Module> modtest = new ArrayList<Module>();
    //public final JComboBox combo = new JComboBox();
    
    
    public  ConsultationTime (){
        getModuleArray();
        title = new JLabel("Create Consultation Slot Session");
        title1 = new JTextField(15);
        title.setBounds(240,35,250,30);
        
        name = new JLabel("Enter Name: ");
        name1 = new JTextField(15);
        name.setBounds(90, 100, 150, 30);
        name1.setBounds(280, 100, 150, 30);
        
        datet = new JLabel("Choose Date and Time: ");
        datet.setBounds(90, 150, 150, 30);
        
        mod = new JLabel("Choose Module: ");
        mod.setBounds(90, 200, 150, 30);
        
        dura = new JLabel("Set Hour Duration: ");
        dura.setBounds(90, 270, 150, 30);
        
        ve = new JLabel("State Venue: ");
        ven = new JTextField(15);
        ven.setBounds(280, 340, 150, 30);
        ve.setBounds(90, 340, 150, 30);
        
        fram.setSize(700, 500);
        fram.setLayout(null);
        fram.setLocationRelativeTo(null);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBounds(155, 150, 500, 50);
        
        
        
    
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(220, 400, 100, 50);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(370, 400, 100, 50);
        
        
        
        
        datetime.setDateTimePermissive(LocalDateTime.now());
        DatePickerSettings dateset = new DatePickerSettings();
        dateset.setFirstDayOfWeek(DayOfWeek.MONDAY);
        

        for(int i = 0; i < modtest.size();i++){
             
            combo.addItem(modtest.get(i).getModname().toString());
            
  
        }
       
       datetime.timePicker.getSettings().use24HourClockFormat();
        
        
        timecombo.addItem(1);timecombo.addItem(2);timecombo.addItem(3);timecombo.addItem(4);
        

        panel.add(datetime);
        panel2.add(combo); panel3.add(timecombo);
        panel2.setBounds(280, 200, 300, 40);panel3.setBounds(280, 270, 60, 40);
        fram.add(title);fram.add(datet);fram.add(name);fram.add(mod);
        fram.add(name1);fram.add(title1);fram.add(dura); fram.add(ve);fram.add(ven);
        fram.add(panel);fram.add(panel2); fram.add(submit);fram.add(panel3);fram.add(cancel);
        
        
        fram.setVisible(true);
        
        a = datetime.getTimePicker().toString();
        b = datetime.getDatePicker().toString();

        
    
    
    
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==submit){
        
        
            String nam = name1.getText();
            String time = datetime.getTimePicker().toString();
            String da = datetime.getDatePicker().toString();
            String selec = combo.getSelectedItem().toString();
            String duration = timecombo.getSelectedItem().toString();
            String venue = ven.getText();
            
        
            
            try{

                FileWriter writer = new FileWriter("consultation.txt",true);
                BufferedWriter bw = new BufferedWriter(writer);
                PrintWriter out = new PrintWriter(bw);

                out.println(nam);
                out.println(da);
                out.println(time);
                out.println(selec);
                out.println(duration);
                out.println(venue);
                out.println();
                out.close();


            }catch (Exception ah){
                System.out.println("Problem encounter!");
            }
            
            JOptionPane.showMessageDialog(this, "Consultation Slot Created!");
            
            
            name1.setText(null); datetime.getTimePicker().setTime(null);combo.setSelectedItem(null);timecombo.setSelectedItem(null);ven.setText(null);datetime.getDatePicker().setDate(null);

        }else if(e.getSource()==cancel){
        
            fram.dispose();
            LecturePortal lecp = new LecturePortal();
            lecp.setVisible(true);
        }
   
    
    }
    
    
    public void addstuff(){
        
        modtest.removeAll(modtest);
        for(int i = 0; i < Run.module.size();i++){
        
            modtest.add(Run.module.get(i));
        
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
                modtest.add(modulelistone);

            }
        }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }
    
}
