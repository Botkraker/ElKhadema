package Elkhadema.khadema.domain;

public class MessageReceiver {
    private Message message;
    private User user;
    private boolean read;

    public MessageReceiver(Message message, User user, boolean read) {
        this.message = message;
        this.user = user;
        this.read = read;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

}
