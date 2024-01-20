package Elkhadema.khadema.domain;

import java.util.Date;

public class User {
    private int id;
    private String password;
    private ContactInfo contactInfo;
    private String firstname;
    private String lastname;
    private Date creationDate;
    private Date lastloginDate;
    public boolean is_banned=false;
    public boolean is_active=false;


    public String getFirstname() {
		return firstname;
	}

	public boolean isIs_banned() {
		return is_banned;
	}

	public void setIs_banned(boolean is_banned) {
		this.is_banned = is_banned;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", contactInfo=" + contactInfo + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", creationDate=" + creationDate + ", lastloginDate=" + lastloginDate
				+ ", is_banned=" + is_banned + ", is_active=" + is_active + "]";
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public User(int id, String password, ContactInfo contactInfo, String firstname, String lastname, Date creationDate,
			Date lastloginDate, boolean is_banned, boolean is_active) {
		super();
		this.id = id;
		this.password = password;
		this.contactInfo = contactInfo;
		this.firstname = firstname;
		this.lastname = lastname;
		this.creationDate = creationDate;
		this.lastloginDate = lastloginDate;
		this.is_banned = is_banned;
		this.is_active = is_active;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Date getLastLoginDate() {
        return lastloginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastloginDate = lastLoginDate;
    }

    public User(String firstname, String lastname, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
