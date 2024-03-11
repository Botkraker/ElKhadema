package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.JobOffre;

public interface JobService {
    void addJob(JobOffre job);
    void deleteJob(JobOffre job);
    void editJob(JobOffre job);
    List<JobOffre> Job(JobOffre job);


}
