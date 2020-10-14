import javax.swing.SwingUtilities;

import model.*;
import controller.*;
import view.*;

public class Main implements Runnable {

    // runner thread
    public void run() {
        Controller controller = new Controller();
        Model model = new Model();
        View view = new View(controller, model);
        view.show();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

}
