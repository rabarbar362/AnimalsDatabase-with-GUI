import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Animals Database");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
}
