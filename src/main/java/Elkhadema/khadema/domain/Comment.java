package Elkhadema.khadema.domain;

import java.util.List;

public class Comment {
	private int id;
    private String content;
    private User user;
    private List<Reaction> reactions;

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

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Comment(int id,String content, User user, List<Reaction> reactions) {
        this.content = content;
        this.id=id;
        this.user = user;
        this.reactions = reactions;
    }
    public Comment(int id) {
    	this.id=id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	};

}
