package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Elkhadema.khadema.DAO.DAOImplemantation.FollowDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.JobsDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.MessageDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PostDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.NotificationService;
import Elkhadema.khadema.domain.Follow;
import Elkhadema.khadema.domain.Message;
import Elkhadema.khadema.domain.Notification;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;

public class NotificationServiceImp implements NotificationService {
    MessageDAO messageDAO = new MessageDAO();
    PostDAO postDAO = new PostDAO();
    FollowDAO followDAO = new FollowDAO();
    JobsDAO jobsDAO = new JobsDAO();

    @Override
    public List<Notification> messageNotifications(User user) {
        return messageDAO.getMessageByUserId(user.getId()).stream()
                .filter(message -> message.getCreationDate().after(user.getLastloginDate()))
                .sorted(Comparator.comparing(Message::getCreationDate))
                .map(message -> new Notification("message", message.getContent(), message.getSender(),message.getCreationDate()))
                .toList();
    }

    @Override
    public List<Notification> postNotifications(User user) {
        List<Follow> followings = followDAO.getfollowingByid(Session.getUser().getId());
        List<User> users = followings.stream().map(Follow::getFollowing).toList();
        return users.stream()
                .flatMap(user2 -> postDAO.getPostsById(user2.getId()).stream())
                .filter(post -> post.getCreationDate().after(user.getLastloginDate()))
                .sorted(Comparator.comparing(Post::getCreationDate))
                .map(post -> new Notification("post", post.getContent(), post.getUser(),post.getCreationDate()))
                .toList();

    }

    @Override
    public List<Notification> adminNotifications(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adminNotifications'");
    }

    @Override
    public List<Notification> jobNotifications(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jobNotifications'");
    }
    @Override
    public List<Notification> allNotifications(User user){
        List<Notification> notifications=new ArrayList<>();
        notifications.addAll(messageNotifications(user));
        notifications.addAll(postNotifications(user));
        return notifications.stream().sorted(Comparator.comparing(Notification::getDate)).toList();

    }
}