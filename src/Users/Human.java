
package Users;


public abstract class Human {
    
    private String username, password, name, gender,age,phoneno, email;
    private int id;

    public Human(String username, String password, String name, String gender, int id, String age, String phoneno) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.age = age;
        this.phoneno = phoneno;
    }
    
    //Constructor used for Student, no login is required.
    public Human(String name, String gender, int id, String age, String phoneno) {
        this("null","null",name,gender,id,age,phoneno);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    
    
}
