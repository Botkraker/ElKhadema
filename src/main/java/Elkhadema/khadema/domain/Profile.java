package Elkhadema.khadema.domain;

import java.util.List;

public class Profile {
    private List<Competance> competances;
    private List<Experience> experiences;
    private ContactInfo contactInfo;

    public Profile(List<Competance> competances, List<Experience> Experiences,
            ContactInfo ContactInfo) {
        this.competances = competances;
        experiences = Experiences;
        contactInfo = ContactInfo;
    }

    public List<Competance> getCompetances() {
        return competances;
    }

    public void setCompetances(List<Competance> competances) {
        this.competances = competances;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

}
