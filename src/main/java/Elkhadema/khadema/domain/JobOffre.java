package Elkhadema.khadema.domain;

public class JobOffre {
    private Company company;
    private String description;
    private String postion;

    public JobOffre(Company company, String description, String postion) {
        this.company = company;
        this.description = description;
        this.postion = postion;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

}
