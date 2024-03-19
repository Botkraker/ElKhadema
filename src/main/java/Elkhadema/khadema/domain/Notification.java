package Elkhadema.khadema.domain;

import java.util.Date;

public class Notification {
    private String type;
    private String content;
    private User user;
    private Date date;
    
    public Notification(String type, String content, User user, Date date) {
        this.type = type;
        this.content = content;
        this.user = user;
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
