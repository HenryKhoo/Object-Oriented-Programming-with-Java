
package Gui;
import Main.*;
import Users.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AssignMarks extends JFrame implements ActionListener {

    private final JTextField stu_id, stu_quiz, stu_test, stu_assignment, mod_id;
    private final JLabel stu_id1,stu_quiz1, stu_test1, stu_assignment1, mod_id1;

    private final JButton submit, cancel;
    private StudentMarks marks; 
    private int a; private boolean flag= false;
    
    public AssignMarks(){
        
        setSize(750,400);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(null);
       
        stu_id1 = new JLabel( "Student ID: ");
        stu_id = new JTextField(15);
        stu_id1.setBounds(200,40,100,20);
        stu_id.setBounds(350,40,150,20);
        
        mod_id1 = new JLabel( "Module ID: ");
        mod_id = new JTextField(15);
        mod_id1.setBounds(200,70,100,20);
        mod_id.setBounds(350,70,150,20);
        
        stu_quiz1 = new JLabel( "Quiz: ");
        stu_quiz = new JTextField(15);
        stu_quiz1.setBounds(200,100,60,20);
        stu_quiz.setBounds(350,100,150,20);
        
        stu_test1 = new JLabel("Test: ");
        stu_test = new JTextField(15);
        stu_test1.setBounds(200,130,60,20);
        stu_test.setBounds(350,130,150,20);
        
        stu_assignment1 = new JLabel("Assignment: ");
        stu_assignment = new JTextField(15);
        stu_assignment1.setBounds(200,160,150,20);
        stu_assignment.setBounds(350,160,150,20);
        
        submit = new JButton("Submit & Register");
        submit.addActionListener(this);
        submit.setBounds(200,300,145,60);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(400,300,100,60);
        


        add(stu_id);add(stu_quiz); add(stu_test); add(stu_assignment); add(mod_id);
        add(stu_id1);add(stu_quiz1); add(stu_test1); ;add(stu_assignment1);add(mod_id1);
        add(submit); add(cancel);
    }
        
        @Override
    public void actionPerformed(ActionEvent ea){
        
        
        if(ea.getSource()==submit){
            
            assignmarks();
           


        }else if(ea.getSource()==cancel){
            ViewStudentMarks viewstumarks1= new ViewStudentMarks();
            viewstumarks1.setVisible(true);
            setVisible(false);
            resetText();
        }
    }  
        public void resetText(){
        stu_id.setText(null);
        mod_id.setText(null);
        stu_quiz.setText(null);
        stu_test.setText(null);
        stu_assignment.setText(null);

       
    }
        

    public void assignmarks(){
        
        
        int a= Integer.parseInt(stu_id.getText());
        int c= Integer.parseInt(mod_id.getText());
        int e = Integer.parseInt(stu_quiz.getText());
        int g = Integer.parseInt(stu_test.getText());
        int i = Integer.parseInt(stu_assignment.getText());
        int k = e+g+i;
        String l = Run.lec_login.getUsername();
        boolean flag = false;
        for(int z = 0; z < Run.student.size(); z++){
            for(int x = 0; x < Run.module.size(); x++){
                
                if(a ==(Run.student.get(z).getId())&&c ==(Run.module.get(x).getId())){
                    if(l.equals(Run.module.get(x).getMlecturer())){
                        if(Run.student.get(z).getLevel().equals(Run.module.get(x).getLevel())){
                            String b = Run.student.get(z).getName();
                            String d = Run.module.get(x).getModname();
                            int f = Run.module.get(x).getQuiz();
                            int h = Run.module.get(x).getTest();
                            int j = Run.module.get(x).getAssignment();
                            if (e<=f && g<=h && i<=j){
                                marks= new StudentMarks(a,b,c,d,e,f,g,h,i,j,k);
                                Run.studentmarks.add(marks);
                                try{
                                    PrintWriter m =new PrintWriter("studentmarks.text");
                                    for(int y = 0; y <Run.studentmarks.size();y++){

                                        StudentMarks w = Run.studentmarks.get(y);
                                        m.println(w.getStudentID());
                                        m.println(w.getStudentName());
                                        m.println(w.getModuleID());
                                        m.println(w.getModuleName());
                                        m.println(w.getQuiz());
                                        m.println(w.getTest());
                                        m.println(w.getAssignment());
                                        m.println(w.getTotal());
                                        //p.println(x.getModule1());
                                        m.println();

                                    }m.close();
                                    }catch(Exception m){}
                                    flag = true;
                                    break;

                            }
                            else if (e>f || g>h || i>j){
                                JOptionPane.showMessageDialog(this, "Marks exceed the limit");
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "Student level not valid to access this course");
                        }
                    }else{
                            JOptionPane.showMessageDialog(this, "Lecturer not allow to access this course");
                    }
                }
                
            }
        
        }if(flag){
            JOptionPane.showMessageDialog(this, "Module marks have been assigned.");
        }else{
            JOptionPane.showMessageDialog(this, "Fail to add marks");
        }
    resetText();
    setVisible(false);
    ViewStudentMarks viewstumarks1= new ViewStudentMarks();
    viewstumarks1.setVisible(true);
    }
    
}
        
    
    

