import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FormPanel extends JPanel implements ActionListener{

        private JTextField animal_id;
        private JTextField animal_name;
        private JTextField animal_birthyear;
        private JTextField animal_species;
        private JButton add_button;

    public FormPanel() {
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Form");
        this.setBorder(title);

        animal_id = new JTextField(4);
        JLabel animal_id_label = new JLabel("ID:");

        animal_name = new JTextField(8);
        JLabel animal_name_label = new JLabel("Name:");

        animal_birthyear = new JTextField(3);
        JLabel animal_birthyear_label = new JLabel("Birthyear:");

        animal_species = new JTextField(5);
        JLabel animal_species_label = new JLabel("Species:");

        add_button = new JButton("Add");
        add_button.addActionListener(this);

        add(animal_id_label);
        add(animal_id);
        add(animal_name_label);
        add(animal_name);
        add(animal_birthyear_label);
        add(animal_birthyear);
        add(animal_species_label);
        add(animal_species);
        add(add_button);
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        Object source = e.getSource();

        if (source == add_button) {

            try {
                int id = Integer.parseInt(animal_id.getText().trim());
                String name = animal_name.getText().trim();
                int birthyear = Integer.parseInt(animal_birthyear.getText().trim());
                String species = animal_species.getText().trim();

                if (name.matches(".*\\d+.*") || species.matches(".*\\d+.*")) {
                    JOptionPane.showMessageDialog(null,
                            "Name and species cannot contain numbers",
                            "Wrong input format",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Database.insert(id, name, birthyear, species);

                    animal_id.setText("");
                    animal_name.setText("");
                    animal_birthyear.setText("");
                    animal_species.setText("");
                }

            } catch(NumberFormatException NumberFormatEx) {
                JOptionPane.showMessageDialog(null,
                        "ID and birth year must be a number",
                        "Wrong input format",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
