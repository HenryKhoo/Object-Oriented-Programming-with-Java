
package Gui;


import Main.Run;
import Users.Student;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
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

public class SearchStudent extends JFrame implements ActionListener{
    private final String[][] columnHeader = {{"Name"},{"ID"}, {"Gender"}, {"Age"}, {"Phone Number"}, {"Email"} , {"Level"}};
    private final DefaultTableModel studentmodel = new DefaultTableModel(columnHeader [0],0);
    
   // private JFrame x;
    //private int id,o;
    private JButton back;
    private DefaultTableModel z; 
    private JTable z1;
    private JLabel searchstu;
    private JTextField searchstu1;
    Student current = null;
    ArrayList<Student> stu = new ArrayList<Student>();
    int oo = 0;    
    
    public SearchStudent(){
        
        
        getStudentArray();

        
        setSize(875, 500);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int size = stu.size();
        String[][] data = new String[size][7];
        for(int i=0; i<size; i++){
            Student a = stu.get(i);
            data[i][0] = a.getName();
            data[i][1] = ""+a.getId();
            data[i][2] = a.getGender();
            data[i][3] = ""+a.getAge();
            data[i][4] = a.getPhoneno();
            data[i][5] = ""+a.getEmail();
            data[i][6] = a.getLevel();
        }
        String[] columnNames = { "Student name", "ID", "Gender", "Age" , "Phone Number", "Email" , "Level" };
        z = new DefaultTableModel(data, columnNames);
        
        z1 = new JTable(z);

        z1.setPreferredScrollableViewportSize(z1.getPreferredSize());
        z1.setFillsViewportHeight(true);
        
        
        JScrollPane sp = new JScrollPane(z1);
        sp.setBounds(25, 50, 800, 400);
     
        searchstu = new JLabel("Search:");
        searchstu.setBounds(50, 10, 50, 25);
        searchstu1 = new JTextField();
        searchstu1.setBounds(150, 10, 200, 25);
        
        
        back = new JButton("Back");
        back.setBounds(400, 10, 100, 25);
        back.addActionListener(this);
        
        
        
        add(back);add(searchstu);add(searchstu1); add(sp);
        setLayout(null);
        searchstu1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                searchTFKeyReleased(evt);
            }
        });
        
        
    }
        @Override
    public void actionPerformed(ActionEvent ee){

        if(ee.getSource()==back){
            

            JFrame ah = new Menu();
            setVisible(false);
            ah.setVisible(true);
            ah.dispose();
        }
            
    }
    public void getStudentArray(){
    try{
            
            Scanner input = new Scanner(new File("student.text"));

            while(input.hasNext()){

                String  name= input.nextLine();
                String gender= input.nextLine();
                int id = Integer.parseInt(input.nextLine());
                String age = input.nextLine();
                String phoneno =input.nextLine();
                String email =input.nextLine();
                String level =input.nextLine();
                input.nextLine();
                Student stuone = new Student(name,gender, id, age, phoneno,email, level);
                stu.add(stuone);

            }
        }catch(Exception e){
                System.out.println("Operation cancelled");
            }
    
    }
    public void searchTFKeyReleased(KeyEvent evt){
        DefaultTableModel model = (DefaultTableModel)z1.getModel();
        String search = searchstu1.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        z1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
}
