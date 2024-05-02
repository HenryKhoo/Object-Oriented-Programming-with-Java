/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;


public class Module {
    

    private String  modname, level, mlecturer;
    private int id, quiz, test, assignment;
    //Constructor used for Lecture assign marks.

    public Module(String modname, int id, String level, int quiz, int test, int assignment, String mlecturer) {
        this.modname = modname;
        this.level = level;
        this.mlecturer = mlecturer;
        this.id = id;
        this.quiz = quiz;
        this.test = test;
        this.assignment = assignment;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMlecturer() {
        return mlecturer;
    }

    public void setMlecturer(String mlecturer) {
        this.mlecturer = mlecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }
    
    
    
    
    
    
}
