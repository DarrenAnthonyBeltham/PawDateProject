package id.ac.binus.pawdate;

public class Match {
    private int matchId;
    private int petId;
    private String petName;
    private String petBreed;
    private int petAge;
    private boolean vaccinated;
    private int imageResId;

    public Match(int matchId, int petId, String petName, String petBreed, int petAge, boolean vaccinated, int imageResId) {
        this.matchId = matchId;
        this.petId = petId;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petAge = petAge;
        this.vaccinated = vaccinated;
        this.imageResId = imageResId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public int getPetAge() {
        return petAge;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public int getImageResId() {
        return imageResId;
    }
}