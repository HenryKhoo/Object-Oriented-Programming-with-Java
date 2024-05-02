
package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class LecturerLog extends JFrame implements ActionListener{
    
    private static final String FILENAME = "lecturer_log.txt";
    private JButton back = new JButton();
    private JLabel log = new JLabel();
    
    
    public LecturerLog(){
    
       setSize(600,800);
       setLocation(300,200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(false);
       setLayout(null);
       BufferedReader br = null;
       FileReader fr = null;
       JTextArea area = new JTextArea();
       area.setBounds(100,30, 300,700);  
       
       try{
   
           fr = new FileReader(FILENAME);
           br = new BufferedReader(fr);
           area.read(fr, null);
           fr.close();
           
       }catch(IOException e){
           
           System.err.println(e);
       }
       log.setText("LECTURER LOG IN/OUT RECORD:");
       log.setBounds(150,5,250,30);
       area.setEditable(false);
       JScrollPane ah = new JScrollPane(area);
       ah.setBounds(70,50, 400,700);
       add(ah);
       
       back.setText("Back");
       back.addActionListener(this);
       back.setBounds(500,350,70,50);
       add(back);
       add(log);
    
    
    
    }
    
    public void actionPerformed(ActionEvent ea){
       
       if(ea.getSource()==back){
           
            setVisible(false);
            
       }

   }
    
}
