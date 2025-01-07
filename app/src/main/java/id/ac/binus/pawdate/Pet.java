package id.ac.binus.pawdate;

public class Pet {
    private int id;
    private String name;
    private String breed;
    private int age;
    private String description;
    private boolean vaccinated;
    private int imageResId;

    public Pet(String name, String breed, int age, String description, boolean vaccinated, int imageResId) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.vaccinated = vaccinated;
        this.imageResId = imageResId;
    }

    public Pet(int id, String name, String breed, int age, boolean vaccinated) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.vaccinated = vaccinated;
        this.description = "";
        this.imageResId = 0;
    }

    public Pet(int id, String name, String breed, int age, String description, boolean vaccinated, int imageResId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.vaccinated = vaccinated;
        this.imageResId = imageResId;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
}