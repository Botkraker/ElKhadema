package Elkhadema.khadema.domain;

public class Company {
    private String companyName;
    private String password;

    public Company(String companyName, String password) {
        this.companyName = companyName;
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
