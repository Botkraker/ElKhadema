package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Message;
import Elkhadema.khadema.domain.User;

public interface MessageService {
    void sendMessage(User user,Message message);
    List<Message> chat(User currentUser,User otherUser);
}
