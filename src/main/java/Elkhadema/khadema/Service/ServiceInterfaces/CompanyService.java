package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.JobOffre;
import Elkhadema.khadema.domain.User;

public interface CompanyService {
    boolean isCompany(User user);

    Company showProfile(User user);

    List<JobOffre> getJobOffres(Company Company);

    Company getCompanyInfo(Company company);

}
