package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Comment;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;

public interface PostService {
    List<Post> getPostsByUser(User user);
    List<Comment> getPostComments(Post post);
    void makePost(Post post);
    void addCommentToPost(Post post,Comment comment);
    List<Reaction> getPostReactions(Post post);
}
