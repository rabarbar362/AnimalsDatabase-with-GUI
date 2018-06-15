import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnimalsList {

    private ArrayList<Animal> AnimalsDBList;
    private Animal animal;

    public AnimalsList() {
        AnimalsDBList = new ArrayList<>();
        String sql = "SELECT * FROM animals";

        try (Connection conn = Database.connectToDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                animal = new Animal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                AnimalsDBList.add(animal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList getAnimalList() {
        return AnimalsDBList;
    }

}
