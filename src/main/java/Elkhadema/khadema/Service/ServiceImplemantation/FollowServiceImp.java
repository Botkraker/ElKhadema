package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.List;

import Elkhadema.khadema.Service.ServiceInterfaces.FollowService;
import Elkhadema.khadema.domain.User;
//TODO do it later
public class FollowServiceImp implements FollowService{

    @Override
    public List<User> getFollowers(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFollowers'");
    }

    @Override
    public List<User> getfollowing(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getfollowing'");
    }

    @Override
    public boolean isFollowing(User user, User following) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFollowing'");
    }

    @Override
    public void Follow(User user, User following) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Follow'");
    }

    @Override
    public void unFollow(User user, User following) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unFollow'");
    }

}
