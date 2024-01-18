package Elkhadema.khadema.domain;

import java.util.Date;
import java.util.List;

public class Post {
    private User user;
    private String content;
    private List<Comment> comments;
    private List<Reaction> reactions;
    private Date CreationDate;

    public Post(User user, String content, List<Comment> comments, List<Reaction> reactions, Date creationDate) {
        this.user = user;
        this.content = content;
        this.comments = comments;
        this.reactions = reactions;
        this.CreationDate = creationDate;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

}
