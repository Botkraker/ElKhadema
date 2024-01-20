package Elkhadema.khadema.domain;

public class JobOffre {
	private long id;
    private Company company;
    private String description;
    private String postion;
    public JobOffre(long id,Company company, String description, String postion) {
    	this.id=id;
        this.company = company;
        this.description = description;
        this.postion = postion;
    }
    public JobOffre(long id) {
    	this.id=id;
    }
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
