
package Users;

import java.util.ArrayList;

public class Student extends Human{
    
    ArrayList<Module> module;
    private String email;
    private String level;
    
    public Student(String name, String gender, int id, String age, String phoneno, String email, String level) {
        super(name, gender, id, age, phoneno);
        this.email = email;
        this.level = level;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Module> getModule() {
        return module;
    }

    public void addModule(Module module1) {
        module.add(module1);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    
    
    
    
}
