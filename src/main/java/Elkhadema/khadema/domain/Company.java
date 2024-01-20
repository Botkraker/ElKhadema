package Elkhadema.khadema.domain;

import java.util.Date;

public class Company  extends User{
    public Company(int id, String password, ContactInfo contactInfo, String firstname, String lastname,
			Date creationDate, Date lastloginDate, boolean is_banned, boolean is_active) {
		super(id, password, contactInfo, firstname, lastname, creationDate, lastloginDate, is_banned, is_active);
		// TODO Auto-generated constructor stub
	}
	private String companyName;

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
	public Company(String firstname, String password, int id, String companyName) {
		super(firstname, password, id);
		this.companyName = companyName;
	}

    
    
}
