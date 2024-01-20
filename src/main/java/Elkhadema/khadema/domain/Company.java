package Elkhadema.khadema.domain;

public class Company extends User {
    private String companyName;

    public Company(String firstname, String password, int id, String companyName) {
        super(firstname, password, id);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
}
