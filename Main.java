import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import main.Window;

public class Main implements Runnable {

    // runner thread
    public void run() {
        Window mainWindow = new Window(640, 480);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

}
