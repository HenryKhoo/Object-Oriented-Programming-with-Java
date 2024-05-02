
package Main;

import Gui.*;
import Users.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;


public class Run {
    
    //Gui page
   
    public static Menu menu = new Menu();
    public static Adminpage pagefront = new Adminpage();
    public static Adminregister pageadmin2 = new Adminregister();
    public static Admin login = null;
    public static Lecturer lec_login = null;
    public static Lecturer edit_lec = null;
    public static Student stu_login = null;
    public static AdminRegisLecturer pageadminlec = new AdminRegisLecturer();
    public static GenerateReportAdmin adminreport = new GenerateReportAdmin();
    public static GenerateReportLec lecreport = new GenerateReportLec();
    public static LectureLogin leclogin = new LectureLogin();
    public static LectureLogin StudentLogin = new LectureLogin();
    public static LecturerEdit lecturer_edit = new LecturerEdit();
    public static StudentEdit student_edit = new StudentEdit();
    public static AdminEdit admin_edit = new AdminEdit();

    
    //Arraylist
    public static ArrayList<Admin> admin = new ArrayList<Admin>();
    public static ArrayList<Lecturer> lecturer =new ArrayList<Lecturer>();
    public static ArrayList<Student> student =new ArrayList<Student>();
    public static ArrayList<Module> module =new ArrayList<Module>();
    public static ArrayList<Module> module_data = new ArrayList<Module>();
    public static ArrayList<StudentMarks> studentmarks =new ArrayList<StudentMarks>();
    
    public static void main(String[] args) throws FileNotFoundException, Exception {
        
        
        admin_accn();
        lecturer_accn();
        student_accn();
        module_accn();
        marks_accn();
    }
    
    

    public static void admin_accn()throws Exception {
        
        try{
            
            Scanner input = new Scanner(new File("admin.text"));

            while(input.hasNext()){

                String username= input.nextLine();
                String password = input.nextLine();
                String  name= input.nextLine();
                String gender= input.nextLine();
                int id = Integer.parseInt(input.nextLine());
                String age = input.nextLine();
                String phoneno =input.nextLine();
                input.nextLine();
                Admin adminone = new Admin(username, password, name,gender, id, age, phoneno);
                admin.add(adminone);


            }
        }catch (Exception input){}
        
    }
    
    public static void lecturer_accn() throws FileNotFoundException {
        try{
        
            Scanner input1 = new Scanner(new File("lecturer.text"));
            while(input1.hasNext()){


                String username= input1.nextLine();
                String password = input1.nextLine();
                String  name= input1.nextLine();
                String gender= input1.nextLine();
                int id = Integer.parseInt(input1.nextLine());
                String age = input1.nextLine();
                String phoneno = input1.nextLine();
                //String module1 = input1.nextLine();

                input1.nextLine();
                Lecturer lecturerone = new Lecturer(username, password, name,gender, id, age, phoneno);
                lecturer.add(lecturerone);
               

            }
        }catch (Exception input1){}
    }
    public static void student_accn() throws FileNotFoundException {
        
        try{
            Scanner input2 = new Scanner(new File("student.text"));

            while(input2.hasNext()){

                String  name= input2.nextLine();
                String gender= input2.nextLine();
                int id = Integer.parseInt(input2.nextLine());
                String age = input2.nextLine();
                String phoneno = input2.nextLine();
                String email = input2.nextLine();
                String level = input2.nextLine();

                input2.nextLine();
                Student studentone = new Student(name,gender, id, age, phoneno, email, level);
                student.add(studentone);
        

            }
        }catch (Exception input2){}
    }
    public static void module_accn() throws FileNotFoundException {
        try{
        
            Scanner input3 = new Scanner(new File("module.text"));
            while(input3.hasNext()){


                String modname= input3.nextLine();
                int id = Integer.parseInt(input3.nextLine());
                String level = input3.nextLine();
                int quiz= Integer.parseInt(input3.nextLine());
                int test= Integer.parseInt(input3.nextLine());
                int assignment = Integer.parseInt(input3.nextLine());
                String mlecturer = input3.nextLine();
                //String module1 = input1.nextLine();

                input3.nextLine();
                Module moduleone = new Module( modname,  id,  level,  quiz,  test,  assignment,  mlecturer);
                module.add(moduleone);
               

            }
        }catch (Exception input3){}
    }
    public static void marks_accn() throws FileNotFoundException {
        try{
        
            Scanner input4 = new Scanner(new File("studentmarks.text"));
            while(input4.hasNext()){

                int StudentID = Integer.parseInt(input4.nextLine()); 
                String StudentName= input4.nextLine(); 
                int ModuleID= Integer.parseInt(input4.nextLine());
                String ModuleName= input4.nextLine(); 
                int quiz= Integer.parseInt(input4.nextLine());
                int test= Integer.parseInt(input4.nextLine());
                int assignment= Integer.parseInt(input4.nextLine());
                int total= Integer.parseInt(input4.nextLine());
                
                input4.nextLine();
                StudentMarks marksone = new StudentMarks( StudentID, StudentName, ModuleID, ModuleName, quiz, test, assignment, total);
                studentmarks.add(marksone);
               

            }
        }catch (Exception input4){}
    }
    

}
