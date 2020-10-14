package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import model.Model;
import controller.Controller;

public class View implements Model.Observer {
    private final JFrame frame;
    private final Model model;
    private final JPanel panel = new JPanel();

    private final JLabel picture = new JLabel();
    private final JFileChooser chooser = new JFileChooser();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenuItem open = new JMenuItem("Open Image");
    private final JMenuItem close = new JMenuItem("Close");
    private final JMenuItem next = new JMenuItem("Next Image");
    private final JMenuItem prev = new JMenuItem("Previous Image");
    private final JMenu helpMenu = new JMenu("Help");
    private final JMenuItem helpItem = new JMenuItem("Control List");
    private final JDialog help = new JDialog();

    public View(final Controller controller, final Model model) {
        this.model = model;

        model.addObserver(this);

        frame = new JFrame();
        frame.setSize(400, 300);
        frame.setTitle("MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addComponentListener(controller.onScreenResize(model));

        // set up menubar
        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(open);
        fileMenu.add(next);
        fileMenu.add(prev);
        fileMenu.add(close);

        // set up help menu
        menuBar.add(helpMenu);
        helpMenu.add(helpItem);
        helpItem.addActionListener(controller.onToggleHelp(help));
        help.setTitle("Help");
        help.setSize(350, 300);
        help.setAlwaysOnTop(true);

        String helpMessage = "Use File > Open Image to open a new image\n" + "Use File > next to get next image\n"
                + "Use File > prev to get previous image\n" + "Use File > close to close the application\n"
                + "KeyboardShortcuts are listed next to above menu items\n"
                + "Left click and Right click on an image to change images\n";
        JTextArea helpTextArea = new JTextArea(helpMessage);
        helpTextArea.setEnabled(false);
        helpTextArea.setDisabledTextColor(Color.BLACK);
        help.add(helpTextArea);

        panel.setLayout(new BorderLayout());

        panel.addMouseListener(controller.onClick(model));

        // add image to main panel
        panel.add(picture, BorderLayout.CENTER);

        // shortcut for open
        open.addActionListener(controller.onOpenFileChooserListener(model, chooser));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_CONTROL));

        // shortcut for close
        close.addActionListener(controller.onClose(frame));
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.VK_CONTROL));

        // shortcut for open
        next.addActionListener(controller.onNextImage(model));
        next.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_CONTROL));

        // shortcut for open
        prev.addActionListener(controller.onPrevImage(model));
        prev.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_CONTROL));

        // add prev and next button
        JButton next = new JButton("next");
        next.addActionListener(controller.onNextImage(model));
        JButton prev = new JButton("prev");
        prev.addActionListener(controller.onPrevImage(model));

        // add controls
        final JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        controls.add(prev);
        controls.add(next);

        panel.add(controls, BorderLayout.SOUTH);
        frame.setContentPane(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void update() {
        picture.setIcon(model.getPicture(panel, frame));
        frame.setTitle(model.getCurrentFile().getName());
        frame.repaint();
    }

}
