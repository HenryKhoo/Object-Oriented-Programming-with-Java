
package Users;

public class StudentMarks {
    private String ModuleName, StudentName;
    private int ModuleID, StudentID, quiz, maxquiz,test, maxtest, assignment, maxassignment, total;
    //Constructor used for Lecture assign marks.

    
    public StudentMarks(int StudentID, String StudentName, int ModuleID, String ModuleName, int quiz, int maxquiz, int test, int maxtest, int assignment, int maxassignment, int total) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
        this.StudentName = StudentName;
        this.StudentID = StudentID;
        this.quiz = quiz;
        this.maxquiz = maxquiz;
        this.test = test;
        this.maxtest = maxtest;
        this.assignment = assignment;
        this.maxassignment = maxassignment;
        this.total = total;
    }
    public StudentMarks(int StudentID, String StudentName, int ModuleID, String ModuleName, int quiz, int test, int assignment, int total) {
        this(StudentID,StudentName,ModuleID,ModuleName, quiz, 0, test,0,assignment,0,total);
    }
    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public int getModuleID() {
        return ModuleID;
    }

    public void setModuleID(int ModuleID) {
        this.ModuleID = ModuleID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public int getMaxquiz() {
        return maxquiz;
    }

    public void setMaxquiz(int maxquiz) {
        this.maxquiz = maxquiz;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getMaxtest() {
        return maxtest;
    }

    public void setMaxtest(int maxtest) {
        this.maxtest = maxtest;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public int getMaxassignment() {
        return maxassignment;
    }

    public void setMaxassignment(int maxassignment) {
        this.maxassignment = maxassignment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
