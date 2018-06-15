import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class AnimalsTableModel extends AbstractTableModel {

    private String[] columnNames = new String[]{"Animal id", "Name", "Birthyear", "Species"};
    private List<Animal> animalsData;

    public AnimalsTableModel(List<Animal> data) {
        super();
        this.animalsData = data;
    }

    @Override
    public int getRowCount() {
        return animalsData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Animal animal = animalsData.get(row);

        switch(col) {
            case 0:
                return animal.getId();
            case 1:
                return animal.getName();
            case 2:
                return animal.getBirthyear();
            case 3:
                return animal.getSpecies();
            default:
                return null;
        }
    }

    public static void updateModel(JTable table) {
        AnimalsList newAnimals = new AnimalsList();
        ArrayList newAnimalsDB = newAnimals.getAnimalList();
        AnimalsTableModel newModel = new AnimalsTableModel(newAnimalsDB);
        table.setModel(newModel);
    }
}
