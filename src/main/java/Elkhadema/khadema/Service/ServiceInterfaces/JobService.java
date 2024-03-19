package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.JobOffre;
import Elkhadema.khadema.domain.User;

public interface JobService {
    void addJob(JobOffre job);
    void deleteJob(JobOffre job);
    void editJob(JobOffre job);
    List<JobOffre> getAllJobOffresByCompany(Company company);
    List<JobOffre> getAllJobRequestByCompany(Company company);
    List<JobOffre> getAllJobRequestByUser(User user);
    List<JobOffre> getAllJobOffresByUser(User user);




}
