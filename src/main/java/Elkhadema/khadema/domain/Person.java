package Elkhadema.khadema.domain;

import java.util.Date;

public class Person extends User {
    private String firstName;
    private String lastName;

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

}
