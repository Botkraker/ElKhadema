package Elkhadema.khadema;

import java.util.List;

import Elkhadema.khadema.Service.ServiceImplemantation.PostServiceImp;
import Elkhadema.khadema.Service.ServiceImplemantation.UserServiceImp;
import Elkhadema.khadema.Service.ServiceInterfaces.PostService;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.util.Exception.UserNotFoundException;

public class test {
    public static void main(String[] args) {
        UserService userService=new UserServiceImp();
        PostService postService =new PostServiceImp();
        try {
            userService.Login("wassimnefzi", "wassimnefzi110");
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Post> posts=postService.feed();
        posts.forEach(post -> System.out.println(post.getId()+":  " +post.getCreationDate().toString()));

    }
}
