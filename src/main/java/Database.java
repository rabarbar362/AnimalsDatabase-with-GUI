import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static Connection connectToDB() {
        String url = "jdbc:sqlite:animals.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createDB() {
        String url = "jdbc:sqlite:animals.db";
        String sql = "CREATE TABLE IF NOT EXISTS animals (\n"
                + "	animal_id integer PRIMARY KEY,\n"
                + "	name varchar,\n"
                + "	birth_year integer,\n"
                + "	species varchar \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(int anID, String anName, int anBirthyear, String anSpecies) {

        Database.connectToDB();

        String sql_insert = "INSERT INTO Animals(animal_id, name, birth_year, species) VALUES(?,?,?,?)";

        try (Connection conn = Database.connectToDB();
             PreparedStatement pstmt = conn.prepareStatement(sql_insert)) {
            pstmt.setInt(1, anID);
            pstmt.setString(2, anName);
            pstmt.setInt(3, anBirthyear);
            pstmt.setString(4, anSpecies);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "The record was added to database.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Please choose different ID number",
                    "The ID is already taken",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM animals WHERE animal_id = ?";
            try (Connection conn = Database.connectToDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                System.out.println("ID w database: " + id);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public static void generateCSV() {
        List<String[]> AnimalsListStrings = new ArrayList<>();
        String sql = "SELECT * FROM animals";

        try (Connection conn = Database.connectToDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int year = rs.getInt(3);
                String species = rs.getString(4);

                String idString = Integer.toString(id);
                String yearString = Integer.toString(year);
                String[] animal = new String[]{idString, name, yearString, species};
                AnimalsListStrings.add(animal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("animals.csv"));
            csvWriter.writeAll(AnimalsListStrings);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
