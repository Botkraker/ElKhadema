package Elkhadema.khadema.domain;

import java.util.Date;

public class Company extends User {
    private String companyName;

    public Company(int id, String companyName,String secondCompanyName) {
        super(companyName, secondCompanyName, id);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
	@Override
	public String toString() {
		return "Company [companyName=" + companyName + "]";
	}
	public Company(int id, String password, ContactInfo contactInfo, String firstname, String lastname,
			Date creationDate, Date lastloginDate, boolean is_banned, boolean is_active, String companyName) {
		super(id, password, contactInfo, firstname, lastname, creationDate, lastloginDate, is_banned, is_active);
		this.companyName = companyName;
	}

    
    
}
