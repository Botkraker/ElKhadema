package Elkhadema.khadema.domain;

import java.util.Date;

public class Person extends User {
    private String firstName;
    private String lastName;
    private int age;
    private String job;
    private String sexe;

    public Person(int id, String password, ContactInfo contactInfo, String userName, Date creationDate,
            Date lastloginDate, String photo, boolean is_banned, boolean is_active, String firstName, String lastName,
            int age, String job, String sexe) {
        super(id, password, contactInfo, userName, creationDate, lastloginDate, photo, is_banned, is_active);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.job = job;
        this.sexe = sexe;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(int id, String password, ContactInfo contactInfo, String userName, Date creationDate,
            Date lastloginDate, String photo, boolean is_banned, boolean is_active, String firstName, String lastName) {
        super(id, password, contactInfo, userName, creationDate, lastloginDate, photo, is_banned, is_active);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(int id, String password, String userName) {
        super(id, password, userName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

}
