package Elkhadema.khadema.Service.ServiceInterfaces;

import java.util.List;

import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.JobOffre;

public interface CompanyService {
    List<JobOffre> getJobOffres(Company Company);

    Company getCompanyInfo(Company company);

}
