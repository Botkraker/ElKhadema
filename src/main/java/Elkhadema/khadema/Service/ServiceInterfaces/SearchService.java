package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.JobOffre;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;

public interface SearchService {
    List<Object> search(String searchWord);
    List<User> searchByUsers(String searchWord);
    List<Post> searchByPosts(String searchWord);
    List<Company> searchByCompanies(String searchWord);
    List<JobOffre> searchByJobOffres(String searchWord);

}
