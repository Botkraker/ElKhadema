package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.FollowDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PostDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ReactionDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.domain.Follow;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;

public class PostServiceImp implements PostService {
    PostDAO postDAO = new PostDAO();
    ReactionDAO reactionDAO = new ReactionDAO();
    FollowDAO followDAO = new FollowDAO();

    @Override
    public List<Post> getPostsByUser(User user) {
        return postDAO.getPostsById(user.getId());
    }

    public List<Post> feed() {
        List<Follow> followings = followDAO.getfollowingByid(Session.getUser().getId());
        List<User> users = followings.stream().map(Follow::getFollowing).toList();
        List<Post> posts = users.stream().flatMap(user -> getPostsByUser(user).stream())
                .sorted(Comparator.comparing(Post::getCreationDate)).toList();
        return posts;
    }

    @Override
    public List<Post> getPostComments(Post post) {
        List<Post> posts = postDAO.getAllPostsUnderParent(post.getId());
        posts = posts.stream()
                .sorted(Comparator.comparingLong(Post::getCountReactions))
                .toList();
        return posts;
    }

    @Override
    public void makePost(Post post) {
        post.setCreationDate(new Date());
        post.setReactions(null);
        postDAO.save(post);
    }

    @Override
    public void addCommentToPost(Post post, Post comment) {
        Optional<Post> oldPost = postDAO.get(post.getId());
        if (!oldPost.isPresent()) {
            return;
        }
        comment.setParentPostId(post.getId());
        makePost(comment);
    }

    @Override
    public void removeCommentFromPost(Post post, Post comment) {
        Optional<Post> oldPost = postDAO.get(post.getId());
        if (!oldPost.isPresent()) {
            return;
        }
        postDAO.delete(comment);
    }

    @Override
    public List<Reaction> getPostReactions(Post post) {
        return reactionDAO.getAll(post);
    }

    @Override
    public void removePost(Post post) {
        postDAO.delete(post);
    }

    @Override
    public void removeReactionFromPost(Post post, Reaction reaction) {
        reactionDAO.delete(reaction);
    }

}
