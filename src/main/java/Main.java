import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Database.connectToDB();
            Database.createDB();

            MyFrame frame = new MyFrame();
            FormPanel formpanel = new FormPanel();
            TablePanel tablepanel = new TablePanel();

            frame.getContentPane().add(formpanel, BorderLayout.NORTH);
            frame.getContentPane().add(tablepanel, BorderLayout.CENTER);

        });
    }
}
