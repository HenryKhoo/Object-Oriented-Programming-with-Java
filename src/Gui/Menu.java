//
package Gui;
import Main.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements ActionListener{
    
    private JButton admin, lecturer,student,exit,report,slot;
    private BufferedImage img;
    public JFrame j = new JFrame();
        
    
    public Menu() {
        
        Color color = new Color(70,70,70);
        j.getContentPane().setBackground(color);
        j.setSize(650,700);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(null);
        admin = new JButton("Admin Portal");
        admin.addActionListener(this);
        admin.setBounds(90,270,200,50);
        j.add(admin);
        lecturer = new JButton("Lecturer Portal");
        lecturer.addActionListener(this);
        lecturer.setBounds(360,270,200,50);
        j.add(lecturer);
        
        student = new JButton("View & Search Student Info");
        student.addActionListener(this);
        student.setBounds(60,380,250,50);
        j.add(student);
        
        slot = new JButton("View & Search Consultation Session");
        slot.addActionListener(this);
        slot.setBounds(340,380,250,50);
        j.add(slot);

        exit = new JButton("Exit system");
        exit.addActionListener(this);
        exit.setBounds(200,500,230,50);
        j.add(exit);
        
        j.setVisible(true);
        
        try{
         img = ImageIO.read(new File("apu.png"));
         
         
        }catch (Exception t){}
        
        ImageIcon icon = new ImageIcon(img);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        lbl.setBounds(220,-140,500,550);
        j.add(lbl);
        
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==admin){
            
            j.setVisible(false);
            j.dispose();
            Run.pagefront.setVisible(true);
        } else if(e.getSource()==exit){
            
            System.exit(0);
        } else if(e.getSource()==lecturer){
            
            j.setVisible(false);
            j.dispose();
            Run.leclogin.setVisible(true);
        } else if(e.getSource()==student){
            
            j.setVisible(false);
            j.dispose();
            SearchStudent searchstu = new SearchStudent();
            searchstu.setVisible(true);
        }else if(e.getSource()==slot){
            
            
            j.dispose();
            j.setVisible(false);
            ViewSlot v = new ViewSlot();
            
        
        }
    }
}
