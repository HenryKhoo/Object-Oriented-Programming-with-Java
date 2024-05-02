
package Gui;
import Main.Run;
import Users.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class AdminPortal extends JFrame implements ActionListener{
    
    
    private JButton create_lec,back,delete_lec,view_admin,view_lec,mod_lec,adminedit,closeadmin, create_stu, delete_stu, viewmodule,report,back2;
    private JLabel title;
    public JFrame frame = new JFrame("ADMIN PORTAL");
    public JPanel panel = new JPanel();
    public BufferedImage myPicture, repic;
    
    public JTabbedPane tabp = new JTabbedPane();    
  

    public Admin test = Run.login;
    int a;
    public AdminPortal() {
        try{
        
            myPicture = ImageIO.read(new File("apu.png"));
            repic = ImageIO.read(new File("re.jpg"));
        
        }catch(Exception e){System.out.println("No image found");}
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JLabel picLabel2 = new JLabel(new ImageIcon(myPicture));
        JLabel picLabel3 = new JLabel(new ImageIcon(myPicture));
        JLabel picLabel4 = new JLabel(new ImageIcon(repic));
        //picLabel.setPreferredSize(new Dimension(400,300));
        Color c = new Color(89,89,89); 
        Color c2 = new Color(77,255,246); 
        frame.setSize(620,420);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("ADMIN PORTAL");
        title.setBounds(300,20,100,30);
        
        //Modification of lecturer
        create_lec = new JButton("Create Lecturer Account"); //done
        create_lec.addActionListener(this);
        create_lec.setBounds(80,70,200,25);
        
        delete_lec = new JButton("Delete Lecturer Account");//done
        delete_lec.addActionListener(this);
        delete_lec.setBounds(80,120,200,25);
        
        mod_lec = new JButton("Modify Lecturer Account");//done
        mod_lec.addActionListener(this);
        mod_lec.setBounds(80,170,200,25);
        
        //Modification of student
        create_stu = new JButton("Create Student Account"); //done
        create_stu.addActionListener(this);
        create_stu.setBounds(400,70,200,25);
        
        delete_stu = new JButton("Delete Student Account");//done
        delete_stu.addActionListener(this);
        delete_stu.setBounds(400,120,200,25);
        

        adminedit= new JButton("Modify own admin account");//done
        adminedit.addActionListener(this);
        
        closeadmin = new JButton("Close own admin account");//done
        closeadmin.addActionListener(this);
        
        view_admin = new JButton("View Admin Log");//done
        view_admin.addActionListener(this);
        
        view_lec = new JButton("View Lecturer Log");//done
        view_lec.addActionListener(this);
        
        viewmodule = new JButton("View Module");//done
        viewmodule.addActionListener(this);
        
        report = new JButton("Generate Report");//done
        report.addActionListener(this);

        back = new JButton("Logout");
        back.addActionListener(this);
        
        Border bod = new LineBorder(Color.CYAN,4,true);
        Border bod2 = new LineBorder(Color.DARK_GRAY,4,true);
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        
        p.setBorder(bod);
        p.setBackground(c);
        p2.setBorder(bod2);
        p2.setBackground(c2);
        p3.setBorder(bod2);
        p4.setBackground(c);
        p4.setBorder(bod);
        p.add(create_lec); p.add(create_stu);p.add(viewmodule);p.add(back);p.add(picLabel);
        p2.add(mod_lec);p2.add(adminedit);p2.add(picLabel3);
        p3.add(delete_lec); p3.add(delete_stu);p3.add(closeadmin);p3.add(picLabel2);
        p4.add(view_admin);p4.add(view_lec);p4.add(report);p4.add(picLabel4);
        tabp.setPreferredSize(new Dimension(1,200));
        tabp.addTab("Account Registration",p);
        tabp.addTab("Account Modification",p2);
        tabp.addTab("Account Deleting and Closing",p3);
        tabp.addTab("Log and Reports",p4);
        
        tabp.setBackground(Color.CYAN);
        
        frame.getContentPane().add(tabp);
        
        frame.setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==create_lec){
            
            Run.pageadminlec.setVisible(true);
            frame.setVisible(false);
            
        }else if(e.getSource()==create_stu){
            AdminRegisStudent pageadminstu = new AdminRegisStudent();
            pageadminstu.setVisible(true);
            frame.setVisible(false);
            
        }else if(e.getSource()==back){
            Adminpage v = new Adminpage();
            v.createlogout();
            Run.pagefront.setVisible(true);
            frame.dispose();
        }else if(e.getSource()==back2){
            Adminpage v = new Adminpage();
            v.createlogout();
            Run.pagefront.setVisible(true);
            frame.dispose();
                    
        }else if(e.getSource()==delete_lec){
            DeleteLecturer deletelec = new DeleteLecturer();
            deletelec.setVisible(true);
            frame.dispose();
            
        }else if(e.getSource()==delete_stu){
            DeleteStudent deletestu = new DeleteStudent();
            deletestu.setVisible(true);    
            frame.dispose();
            
        }else if(e.getSource()== view_admin){
           AdminLog ada = new AdminLog();
           ada.setVisible(true);
            
            
        }else if(e.getSource()==view_lec){
            LecturerLog leclog = new LecturerLog();
            leclog.setVisible(true);
            
            
        }else if(e.getSource()==mod_lec){
            
            JOptionPane.showMessageDialog(this, "Redirecting to lecturer portal");
            Run.leclogin.setVisible(true);
            frame.dispose();


        }else if(e.getSource()==adminedit){
            
            frame.setVisible(false);
            Run.admin_edit.setVisible(true);
            
        }else if(e.getSource()==closeadmin){
            
            closeaccount();
            frame.setVisible(false);
            Run.pagefront.setVisible(true);
            
        }else if(e.getSource()==viewmodule){
            
            frame.setVisible(false);
            ViewModule viewmod = new ViewModule();
            viewmod.setVisible(true);
            
            
            
        }else if(e.getSource()==report){
        
            Run.adminreport.setVisible(true);
            frame.setVisible(false);
        
        }
    }

    
    public void closeaccount(){
        
        boolean flag = false;
        
        try{
            String o = (JOptionPane.showInputDialog(this,"Enter account password to delete"));
            if(o.equals(Run.login.getPassword())){
            
            
            for(int i = 0; i < Run.admin.size(); i++){
                
                if(Run.login.equals(Run.admin.get(i)));
                Run.admin.remove(Run.login);
                
                try{
                    
                    PrintWriter p =new PrintWriter("admin.text");

                    for(int j=0; j<Run.admin.size();j++){

                        Admin t = Run.admin.get(j);
                        p.println(t.getUsername());
                        p.println(t.getPassword());
                        p.println(t.getName());
                        p.println(t.getGender());
                        p.println(t.getId());
                        p.println(t.getAge());
                        p.println(t.getPhoneno());
                        p.println();

                    }
                    p.close();
                }catch(Exception t){}
                
                flag = true;
                break;
            }
            
        }
        } catch(Exception t){
            System.out.println("Cancelled");
        }
        
        if(flag){
            JOptionPane.showMessageDialog(this, "Admin account has been deleted");
            
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Password or Operation Cancelled");
        }
 
    }
}