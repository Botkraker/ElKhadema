package Elkhadema.khadema.domain;

public class Message {
private long id;
private User sender;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
private User reciver;
private String content;
public Message(long id,User sender, User reciver, String content) {
	this.id=id;
    this.sender = sender;
    this.reciver = reciver;
    this.content = content;
}
public User getSender() {
    return sender;
}
public void setSender(User sender) {
    this.sender = sender;
}
public User getReciver() {
    return reciver;
}
public void setReciver(User reciver) {
    this.reciver = reciver;
}
public String getContent() {
    return content;
}
public void setContent(String content) {
    this.content = content;
}

}
