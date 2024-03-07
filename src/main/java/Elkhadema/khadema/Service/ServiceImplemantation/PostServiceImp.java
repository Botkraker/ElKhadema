package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.PostDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ReactionDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;

public class PostServiceImp implements PostService {
    PostDAO postDAO = new PostDAO();
    ReactionDAO reactionDAO = new ReactionDAO();

    @Override
    public List<Post> getPostsByUser(User user) {
        return postDAO.getPostsById(user.getId());
    }

    @Override
    public List<Post> getPostComments(Post post) {
        return postDAO.getAllPostsUnderParent(post.getId());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePost'");
    }

    @Override
    public void removeReactionFromPost(Post post, Reaction reaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeReactionFromPost'");
    }

}
