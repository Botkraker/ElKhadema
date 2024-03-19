package Elkhadema.khadema.domain;

import java.util.Date;

public class JobRequest {
    private User user;
    private JobOffre jobOffre;
    private Date date;
    private String etat;

    public JobRequest(User user, JobOffre jobOffre, String etat) {
        this.user = user;
        this.jobOffre = jobOffre;
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobOffre getJobOffre() {
        return jobOffre;
    }

    public void setJobOffre(JobOffre jobOffre) {
        this.jobOffre = jobOffre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}
