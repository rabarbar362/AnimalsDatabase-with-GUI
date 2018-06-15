public class Animal {

    private int id, birthyear;
    private String name, species;

    public Animal(int id, String name, int birthyear, String species) {
        this.id = id;
        this.name = name;
        this.birthyear = birthyear;
        this.species = species;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public String getSpecies() {
        return species;
    }
}
