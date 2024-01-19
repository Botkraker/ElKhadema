package Elkhadema.khadema.domain;

import java.util.Date;

public class Admin extends User{

	public Admin(` id, String password, ContactInfo contactInfo, String firstname, String lastname, Date creationDate,
			Date lastloginDate, boolean is_banned, boolean is_active) {
		super(id, password, contactInfo, firstname, lastname, creationDate, lastloginDate, is_banned, is_active);
		// TODO Auto-generated constructor stub
	}


}
