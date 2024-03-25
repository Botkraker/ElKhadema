package Elkhadema.khadema.domain;

import java.util.Date;

public class Experience {
    private long id;
    private String description;
    private String mission;
    private String type;
    private Date startDate;
    private Date endDate;
    private String technologie;

    public Experience(long id, String description, String mission, String type, Date startDate, Date endDate,
            String technologie) {
        this.id = id;
        this.description = description;
        this.mission = mission;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologie = technologie;
    }

    public Experience(long id, String description, String mission, String type, String technologie) {
        this.description = description;
        this.mission = mission;
        this.type = type;
        this.technologie = technologie;
    }

    public Experience(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
