/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Main.*;
import Users.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CreateModule extends JFrame implements ActionListener {
    
    private final JTextField modname;
    private final JTextField quiz, test, assignment;
    private final JLabel modname1, level1, title, mlecturer1;
    private final JLabel quiz1, test1, assignment1;
    private final JButton submit, cancel;
    public JPanel panellec = new JPanel(new BorderLayout());
    public JPanel panellvl = new JPanel(new BorderLayout());
    public JComboBox combolec = new JComboBox();
    public JComboBox combolvl = new JComboBox();
    private int b; private boolean flag= false;
    private Module module_data1; 
    ArrayList<Lecturer> lecdata = new ArrayList<Lecturer>();
    
    public CreateModule(){
        
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
        
        title = new JLabel("CREATE MODULE");
        title.setBounds(250,4,400,30);
        
        modname1 = new JLabel("Module: ");
        modname = new JTextField(15);
        modname1.setBounds(200,70,60,20);
        modname.setBounds(350,70,150,20);
        
        level1 = new JLabel("Level: ");
        //level = new JTextField(15);
        level1.setBounds(200,100,60,20);
        //level.setBounds(350,100,150,20);
       
        quiz1 = new JLabel("Quiz: ");
        quiz = new JTextField(15);
        quiz1.setBounds(200,130,60,20);
        quiz.setBounds(350,130,150,20);
        
        test1 = new JLabel("Test: ");
        test = new JTextField(15);
        test1.setBounds(200,160,60,20);
        test.setBounds(350,160,150,20);
        
        assignment1 = new JLabel("Assignment: ");
        assignment = new JTextField(15);
        assignment1.setBounds(200,190,150,20);
        assignment.setBounds(350,190,150,20);
        
        mlecturer1 = new JLabel("Lecturer: ");
        //mlecturer = new JTextField(15);
        mlecturer1.setBounds(200,220,150,20);
        //mlecturer.setBounds(350,220,150,20);
        
        submit = new JButton("Add Module");
        submit.addActionListener(this);
        submit.setBounds(200,300,145,60);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(400,300,100,60);
        
        for(int i = 0; i < Run.lecturer.size();i++){
             
            
            
            combolec.addItem(Run.lecturer.get(i).getUsername().toString());
            

        }
        
        combolvl.addItem(1);combolvl.addItem(2);combolvl.addItem(3);add(modname); 
        panellec.add(combolec);panellec.setBounds(350,220,150,20); add(panellec);
        panellvl.add(combolvl);panellvl.setBounds(350,100,50,20); add(panellvl);
        add(quiz); add(test); add(assignment); 
        add(modname1); add(level1); ;add(mlecturer1);
        add(quiz1); add(test1); add(assignment1);
        add(submit); add(cancel); 
    }
    
    @Override
    public void actionPerformed(ActionEvent ea){
        
        
        if(ea.getSource()==submit){
            
            create();
            resetText();
            setVisible(false);
            ViewModule viewmod = new ViewModule();
            viewmod.setVisible(true);
            

        }else if(ea.getSource()==cancel){
            setVisible(false);
            ViewModule viewmodule = new ViewModule();
            viewmodule.setVisible(true);
            resetText();
        }
          
        
    }
    
    public void resetText(){
        
        modname.setText(null);
        combolvl.setSelectedItem(null);
        quiz.setText(null);
        test.setText(null);
        assignment.setText(null);
        combolec.setSelectedItem(null);
       
    }
    public void create(){
        id_gen();
        String a = modname.getText();
        String c = combolvl.getSelectedItem().toString();

        int d = Integer.parseInt(quiz.getText());
        int e = Integer.parseInt(test.getText());
        int f = Integer.parseInt(assignment.getText());
        String g = combolec.getSelectedItem().toString();
        if(a.isEmpty() || c.isEmpty() || g.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter all the fields!");
            } else if((d + e + f) != 100){
                JOptionPane.showMessageDialog(this, "Total marks cannot be more or less than 100!");
            } else{
                boolean flag = false;
                for(int i = 0; i < Run.lecturer.size(); i++){
                    Lecturer z = Run.lecturer.get(i);
                    if(g.equals(z.getUsername())){
                        module_data1= new Module(a,b,c,d,e,f,g);
                        Run.module.add(module_data1);
                        try{
                            PrintWriter m =new PrintWriter("module.text");
                            for(int j=0; j<Run.module.size();j++){

                                Module x = Run.module.get(j);
                                m.println(x.getModname());
                                m.println(x.getId());
                                m.println(x.getLevel());
                                m.println(x.getQuiz());
                                m.println(x.getTest());
                                m.println(x.getAssignment());
                                m.println(x.getMlecturer());
                                m.println();

                            }m.close();
                            }catch(Exception m){}
                            flag = true;
                            break;

                            }else if(c==null){
                            JOptionPane.showInputDialog("Action cancelled");

                            }
                        }
                        if(flag){
                                JOptionPane.showMessageDialog(this, "Module has been assigned.");
                        }else{
                            JOptionPane.showMessageDialog(this, "Invalid Lecturer Name");
                        }
            }  
        
    }

    
    public void id_gen() {
        if(Run.module.isEmpty()){
            b = 9001;
        } else{
            int size = Run.module.size();
            Module last = Run.module.get(size - 1);
            b = last.getId()+ 1;
        }//To change body of generated methods, choose Tools | Templates.
    }
    

}
