package Elkhadema.khadema.domain;

public class Competance {
    private String titre;
    private String technologie;
    private String description;
    private int niveau;

    public Competance(String titre, String technologie, String description, int niveau) {
        this.titre = titre;
        this.technologie = technologie;
        this.description = description;
        this.niveau = niveau;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

}
