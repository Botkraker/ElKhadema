package Elkhadema.khadema.domain;

import java.util.Date;

public class Reaction {
    private User user;
    private String type;
    private Date creationDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Reaction(User user, String type, Date creationDate) {
        this.user = user;
        this.type = type;
        this.creationDate = creationDate;
    }

}
