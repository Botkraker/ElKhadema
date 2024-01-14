package Elkhadema.khadema.Service;

import Elkhadema.khadema.domain.Comment;
import Elkhadema.khadema.domain.User;

public interface AdminService {
    void BanUser(User user);
    void deleteComment(Comment Comment);
}
