package Elkhadema.khadema.domain;

public class Experience {
    private String description;
    private String mission;
    private String type;
    private String technologie;

    public Experience(String description, String mission, String type, String technologie) {
        this.description = description;
        this.mission = mission;
        this.type = type;
        this.technologie = technologie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
