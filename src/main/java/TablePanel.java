import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TablePanel extends JPanel implements ActionListener {

    private JButton update_btn, delete_btn;
    private JTable animalsTable;

    public TablePanel() {
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Database");
        this.setBorder(title);

        AnimalsList animals = new AnimalsList();
        ArrayList AnimalsDB = animals.getAnimalList();

        AnimalsTableModel model = new AnimalsTableModel(AnimalsDB);
        animalsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(animalsTable);
        animalsTable.setFillsViewportHeight(true);
        animalsTable.setPreferredScrollableViewportSize(new Dimension(750, 325));
        add(scrollPane);

        update_btn = new JButton("Update table");
        update_btn.addActionListener(this);
        this.add(update_btn);

        delete_btn = new JButton("Delete row");
        delete_btn.addActionListener(this);
        this.add(delete_btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == update_btn) {
            AnimalsTableModel.updateModel(animalsTable);
        } else if (source == delete_btn) {
            int i = animalsTable.getSelectedRow();
            int id = (int) animalsTable.getModel().getValueAt(i,0);
            System.out.println("Selected row: " + i);
            System.out.println("Selected row's id: " + id);
            Database.delete(id);
            AnimalsTableModel.updateModel(animalsTable);
        }
    }
}
