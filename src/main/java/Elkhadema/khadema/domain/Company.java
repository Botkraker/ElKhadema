package Elkhadema.khadema.domain;

<<<<<<< HEAD
import java.util.Date;

public class Company  extends User{
    public Company(int id, String password, ContactInfo contactInfo, String firstname, String lastname,
			Date creationDate, Date lastloginDate, boolean is_banned, boolean is_active) {
		super(id, password, contactInfo, firstname, lastname, creationDate, lastloginDate, is_banned, is_active);
		// TODO Auto-generated constructor stub
	}
	private String companyName;

=======
public class Company extends User {
    private String companyName;

    public Company(String firstname, String password, int id, String companyName) {
        super(firstname, password, id);
        this.companyName = companyName;
    }
>>>>>>> branch 'toMain' of https://github.com/Botkraker/ElKhadema.git
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
<<<<<<< HEAD
	@Override
	public String toString() {
		return "Company [companyName=" + companyName + "]";
	}
	public Company(String firstname, String password, int id, String companyName) {
		super(firstname, password, id);
		this.companyName = companyName;
	}

=======
>>>>>>> branch 'toMain' of https://github.com/Botkraker/ElKhadema.git
    
    
}
